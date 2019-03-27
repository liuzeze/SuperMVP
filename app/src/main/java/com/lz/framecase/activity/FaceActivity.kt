package com.lz.framecase.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.lz.fram.base.LpLoadDialog
import com.lz.fram.scope.AttachView
import com.lz.framecase.R
import com.lz.framecase.activity.adapter.FaceAdapter
import com.lz.framecase.activity.presenter.FaceContract
import com.lz.framecase.activity.presenter.FacePresenter
import com.lz.framecase.base.BaseActivity
import com.lz.framecase.bean.FaceListEntity
import com.lz.framecase.logic.MyApplication
import com.lz.inject_annotation.InjectActivity
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vondear.rxtool.RxImageTool
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_face.*
import java.io.File
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class FaceActivity : BaseActivity(), FaceContract.View {


    val CODE_SELECT_IMAGE: Int = 2;//相册RequestCode

    val TYPE_TAKE_PHOTO: Int = 1;//Uri获取类型判断

    val CODE_TAKE_PHOTO: Int = 1;//相机RequestCode
    var photoUri: Uri? = null

    var bitmap: Bitmap? = null
    var mFaces: ArrayList<FaceListEntity> = ArrayList()
    var faceAdapter: FaceAdapter? = null
    @AttachView
    @JvmField
    var mPresenter: FacePresenter? = null

    var dialog: LpLoadDialog ?=null

    override fun getLayout(): Int {
        return R.layout.activity_face
    }

    override fun initViewData() {
        dialog = LpLoadDialog(this)
        face_toolbar.setTitle("人脸检测")
        face_toolbar.setTitleTextColor(Color.WHITE)
        faceAdapter = FaceAdapter(mFaces)
        recyclerview.adapter = faceAdapter

    }

    override fun initLisenter() {
        super.initLisenter()
        faceAdapter?.setOnItemClickListener { adapter, view, position ->
            if (mFaces.get(position).expression != null) {
                val intent = Intent(this, FaceDetailActivity::class.java)
                val image = android.support.v4.util.Pair(iv_photo as View, "image")
                val beauty = android.support.v4.util.Pair(view.findViewById<View>(R.id.beauty), "beauty")
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, image!!, beauty!!)
                intent.putExtra("mFacesBean", mFaces.get(position))
                val bundle = Bundle()
                bundle.putParcelable("bitmap", bitmap)
                intent.putExtras(bundle)
                startActivity(intent, optionsCompat.toBundle())
            }
        }
        bt_album.setOnClickListener({
            var albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(albumIntent, CODE_SELECT_IMAGE);
        })
        bt_change.setOnClickListener( {
            it.isSelected = !it.isSelected
        })
        bt_open_photo.setOnClickListener({
            dialog!!.show()
            mPresenter?.auth(mActivity)
        })
        bt_take_photo.setOnClickListener(View.OnClickListener {

            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                ToastUtils.error("打开相册失败")
                return@OnClickListener
            }

            val rxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(fun(it: Boolean?) {
                if (it!!) {
                    if (Build.VERSION.SDK_INT >= 24) {


                        val takeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        photoUri = get24MediaFileUri(TYPE_TAKE_PHOTO)
                        if (bt_change.isSelected) {
                            takeIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
                        }

                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(takeIntent, CODE_TAKE_PHOTO)
                    } else {
                        val takeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        photoUri = getMediaFileUri(TYPE_TAKE_PHOTO)
                        if (bt_change.isSelected) {
                            takeIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
                        }
                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(takeIntent, CODE_TAKE_PHOTO)
                    }

                } else {
                    ToastUtils.error("权限获取失败")
                }
            })
        })
    }


    /**
     * 版本24以上
     */
    fun get24MediaFileUri(type: Int): Uri? {
        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FaceAlbum")
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        //创建Media File
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val mediaFile: File
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg")
        } else {
            return null
        }
        return FileProvider.getUriForFile(this, "$packageName.fileprovider", mediaFile)
    }

    fun getMediaFileUri(type: Int): Uri? {
        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FaceAlbum");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var mediaFile: File
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null
        }
        return Uri.fromFile(mediaFile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_TAKE_PHOTO ->
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Log.i("URI", "data is not null")
                            bitmap = data.getParcelableExtra<Bitmap>("data")
                            mPresenter?.getNewPicture(bitmap)
                            Glide.with(this).load(bitmap).into(iv_photo)

                        }
                    } else {
                        Log.i("URI", "Data is null")
                        if (Build.VERSION.SDK_INT >= 24) {
                            try {
                                bitmap = RxImageTool.getBitmap(photoUri?.path, 200, 200)
                            } catch (e: FileNotFoundException) {
                                e.printStackTrace()
                            }
                            Glide.with(this).load(bitmap).into(iv_photo)

                            mPresenter?.getNewPicture(bitmap)

                        } else {
                            bitmap = RxImageTool.getBitmap(photoUri?.path, 200, 200)
                            mPresenter?.getNewPicture(bitmap)
                            Glide.with(this).load(bitmap).into(iv_photo)

                        }
                    }
                }
            CODE_SELECT_IMAGE -> {
                if (resultCode == RESULT_OK) {

                    Flowable.fromPublisher<Bitmap> {
                        bitmap = Glide.with(this).asBitmap().load(data?.data).submit(200, 200).get()
                        it.onNext(bitmap)
                    }.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                mPresenter?.getNewPicture(bitmap)
                                Glide.with(this).load(bitmap).into(iv_photo)
                            }

                }
            }


        }
    }

    override fun getNewPictureSuccess(bitmap: Bitmap?, faces: List<FaceListEntity>?) {
        if (bitmap != null) {
            Glide.with(this).load(bitmap).into(iv_photo)
            mFaces.clear()
            mFaces.addAll(faces!!)
            faceAdapter?.bitmap = bitmap
            faceAdapter?.setNewData(mFaces)
        } else {
            ToastUtils.error("没有匹配到")
        }
    }

    override fun authFailed() {
        bt_open_photo.visibility = View.VISIBLE
        bt_open_photo.text = "授权失败"
        dialog!!.dismiss()
    }

    override fun authSuccess() {
        bt_open_photo.visibility = View.GONE
        bt_take_photo.visibility = View.VISIBLE
        dialog!!.dismiss()
    }
}
