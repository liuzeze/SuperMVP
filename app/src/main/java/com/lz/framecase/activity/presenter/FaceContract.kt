package com.lz.framecase.activity.presenter

import android.content.Context
import android.graphics.Bitmap
import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.FaceListEntity


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
interface FaceContract {


    interface View : BaseView {
        fun getNewPictureSuccess(s: Bitmap?, faces: List<FaceListEntity>?)
        fun authSuccess()
        fun authFailed()
    }

    interface Presenter : BasePresenter {
        fun getNewPicture(bitmap: Bitmap?)
        fun auth(context: Context)
    }


}
