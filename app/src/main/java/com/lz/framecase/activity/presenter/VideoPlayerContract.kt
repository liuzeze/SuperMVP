package com.lz.framecase.presenter


import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.NewsCommentBean

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
interface VideoPlayerContract {


    interface View : BaseView {
        fun getVideoUrlSuccess(url: String)
    }

    interface Presenter : BasePresenter{
        fun getVideoUrl(videoId: String)
    }


}
