package com.lz.framecase.activity

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect
import com.bumptech.glide.Glide
import com.lz.framecase.R
import com.lz.framecase.base.NewsBaseActivity
import com.lz.framecase.widget.imagebrowser.DismissFrameLayout
import com.lz.utilslib.interceptor.utils.LpDialogUtils
import com.lz.utilslib.interceptor.utils.ShareAction
import com.tbruyelle.rxpermissions2.RxPermissions
import com.vondear.rxtool.RxFileTool
import com.vondear.rxtool.RxImageTool
import com.vondear.rxtool.RxTimeTool
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_preview.*
import uk.co.senab.photoview.DefaultOnDoubleTapListener
import uk.co.senab.photoview.PhotoView
import uk.co.senab.photoview.PhotoViewAttacher
import java.io.File
import java.util.*

class ImagePreviewActivity : NewsBaseActivity() {

    var mColorDrawable: ColorDrawable? = null
    override fun getLayout(): Int {
        return R.layout.activity_image_preview
    }

    override fun initViewData() {


        initData()
    }

    private fun initData() {
        mColorDrawable = ColorDrawable(resources.getColor(R.color.common_app_black_000000))
        preview_root.setBackground(mColorDrawable)

        val stringExtra = intent?.getStringExtra("url")
        val stringArrayExtra = intent?.getSerializableExtra("urlList") as java.util.ArrayList<String>

        val arrayList = ArrayList<View>()
        stringArrayExtra?.forEachIndexed { index, s ->
            val inflate = View.inflate(mActivity, R.layout.item_image_preview, null)
            val imageView = inflate.findViewById<PhotoView>(R.id.photoView)
            val attacher = PhotoViewAttacher(imageView)
            attacher.setOnDoubleTapListener(PhotoViewOnDoubleTapListener(attacher))
            attacher.setOnLongClickListener(View.OnLongClickListener {
                showActionDialog(s)
                false
            })
            Glide.with(mActivity).load(s).into(imageView)

            val layout = inflate.findViewById<DismissFrameLayout>(R.id.dismissContainter)
            layout.setDismissListener(onDismissListener)
            arrayList.add(inflate)
        }
        bgabanner.setTransitionEffect(TransitionEffect.Zoom)
        bgabanner.setData(arrayList)
        try {
            bgabanner.currentItem=stringArrayExtra.indexOf(stringExtra)
        } catch (e: Exception) {
        }
        bgabanner.setAutoPlayAble(false)
    }

    private fun showActionDialog(url: String) {
        LpDialogUtils.alertDialog(mActivity, "这个图片不错", "分享", View.OnClickListener {
            shareImage(url)
        }, "保存", View.OnClickListener {

            saveImage(url)
        }, true)

    }

    /**
     * 分享图片
     */
    private fun shareImage(url: String) {
        var rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(Consumer {
                    if (it) {
                        Observable.create<String> {
                            val bitmap = Glide.with(mActivity).asBitmap().load(url).submit().get()
                            val fileByPath = RxFileTool.getFileByPath(RxFileTool.getSDCardPath().toString() + File.separator + "imagetemp" + File.separator + "tempshare.png")
                            val save = RxImageTool.save(bitmap, fileByPath, Bitmap.CompressFormat.PNG)
                            it.onNext(if (save) fileByPath.path else "")
                        }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(Consumer {
                                    if (!TextUtils.isEmpty(it)) {
                                        ShareAction.sendImage(mActivity, Uri.parse(it))
                                    }
                                })
                    }
                });

    }

    /**
     * 保存图片
     */
    private fun saveImage(url: String) {
        var rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(Consumer {
                    if (it) {
                        Observable.create<Boolean> {
                            val bitmap = Glide.with(mActivity).asBitmap().load(url).submit().get()
                            val fileByPath = RxFileTool.getFileByPath(RxFileTool.getSDCardPath().toString() + File.separator + "imagelz" + File.separator + RxTimeTool.getCurTimeMills() + ".png")
                            val save = RxImageTool.save(bitmap, fileByPath, Bitmap.CompressFormat.PNG)
                            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                            val uri = Uri.fromFile(fileByPath)
                            intent.data = uri
                            sendBroadcast(intent)
                            it.onNext(save)
                        }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(Consumer {
                                    Snackbar.make(bgabanner, if (it) "保存成功" else "保存失败", Snackbar.LENGTH_SHORT).show()
                                })
                    }
                });
    }

    private inner class PhotoViewOnDoubleTapListener internal constructor(private val photoViewAttacher: PhotoViewAttacher?) : DefaultOnDoubleTapListener(photoViewAttacher) {
        private var canZoom = true

        override fun onDoubleTap(ev: MotionEvent): Boolean {
            if (photoViewAttacher == null)
                return false
            try {
                val x = ev.x
                val y = ev.y

                if (canZoom) {
                    photoViewAttacher.setScale(photoViewAttacher.mediumScale, x, y, true)
                } else {
                    photoViewAttacher.setScale(photoViewAttacher.minimumScale, x, y, true)
                }
                canZoom = !canZoom
            } catch (e: ArrayIndexOutOfBoundsException) {
                // Can sometimes happen when getX() and getY() is called
            }

            return true
        }
    }

    private val ALPHA_MAX = 0xFF

    private val onDismissListener = object : DismissFrameLayout.OnDismissListener {

        override fun onScaleProgress(scale: Float) {
            mColorDrawable?.setAlpha(
                    Math.min(ALPHA_MAX, mColorDrawable!!.alpha - (scale * ALPHA_MAX).toInt()))
        }

        override fun onDismiss() {
            finish()
        }

        override fun onCancel() {
            mColorDrawable?.setAlpha(ALPHA_MAX)
        }
    }

}
