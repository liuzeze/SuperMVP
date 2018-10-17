package com.lz.framecase.activity.presenter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Base64
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.BuildConfig
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.FaceListEntity
import com.lz.framecase.bean.FaceResponse
import com.lz.framecase.bean.FaceppBean
import com.lz.framecase.bean.TokenBean
import com.lz.framecase.logic.Constans
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.vondear.rxtool.RxSPTool
import com.vondear.rxtool.RxTool
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import java.io.ByteArrayOutputStream
import java.io.ObjectInput
import javax.inject.Inject


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
@SuppressLint("CheckResult")
class FacePresenter @Inject
constructor(internal var mRequestApi: RequestApi) : RxPresenter<FaceContract.View>(), FaceContract.Presenter {
    override fun auth(context: Context) {
        mRequestApi.token()
                ?.`as`(bindLifecycle<TokenBean>())
                ?.subscribeWith(object : CommonSubscriber<TokenBean>(mBaseView) {
                    override fun onError(mes: String?) {
                        super.onError(mes)
                        mBaseView.authFailed()
                    }

                    override fun onNext(s: TokenBean) {
                        RxSPTool.putString(context, Constans.FACETOKEN, s.access_token)
                        mBaseView.authSuccess()
                    }
                })
    }

    override fun getNewPicture(bitmap: Bitmap?) {
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val bytes = baos.toByteArray()
        val s = Base64.encodeToString(bytes, Base64.DEFAULT)
        val string = RxSPTool.getString(RxTool.getContext(), Constans.FACETOKEN)


      /*  mRequestApi.token()
                ?.observeOn(Schedulers.io())
                ?.flatMap {
                    mRequestApi.getNewPicture(s,
                            it.access_token, "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities")
                }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.`as`(bindLifecycle<FaceResponse>())
                ?.subscribeWith(object : CommonSubscriber<FaceResponse>(mBaseView){
                    override fun onError(e: Throwable?) {
                        super.onError(e)
                    }

                    override fun onNext(s: FaceResponse) {
                        if (s.error_msg.equals("SUCCESS")) {
                            detectResult(bitmap, s.result)
                        } else {
                            ToastUtils.error(s.error_msg)
                        }
                    }

                })
*/

        val newPicture = mRequestApi.getNewPicture(s,
                string, "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities")

        newPicture
                ?.`as`(bindLifecycle<FaceResponse>())
                ?.subscribeWith(object : CommonSubscriber<FaceResponse>(mBaseView) {
                    override fun onError(e: Throwable?) {
                        super.onError(e)
                    }

                    override fun onNext(s: FaceResponse) {
                        if (s.error_msg.equals("SUCCESS")) {
                            detectResult(bitmap, s.result)
                        } else {
                            ToastUtils.error(s.error_msg)
                        }
                    }
                })

    }

    private fun detectResult(photo: Bitmap?, faceppBean: FaceppBean?) {
        val faces = faceppBean?.face_list
        if (faces == null || faces!!.size == 0) {
            mBaseView.getNewPictureSuccess(null, null)

        } else {
            val photoMarkedFaces = markFacesInThePhoto(photo!!, faces!!)
            mBaseView.getNewPictureSuccess(photoMarkedFaces, faces)
        }
    }

    private fun markFacesInThePhoto(bitmap: Bitmap, faces: List<FaceListEntity>): Bitmap {
        val tempBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(tempBitmap)
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f

        for (face in faces) {
            val faceRectangle = face.location
            val top = faceRectangle?.top!!
            val left = faceRectangle?.left!!
            val height = faceRectangle?.height!!
            val width = faceRectangle?.width!!
            canvas.drawRect(left.toFloat(), top.toFloat(), (left + width).toFloat(), (top + height).toFloat(), paint)
        }
        return tempBitmap
    }

}