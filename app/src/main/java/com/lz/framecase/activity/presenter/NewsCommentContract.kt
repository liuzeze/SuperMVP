package com.lz.framecase.presenter


import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView
import com.lz.framecase.bean.NewsCommentBean

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
interface NewsCommentContract {


    interface View : BaseView {
        fun getNewsCommentSuccess(data: ArrayList<NewsCommentBean.DataBean>)
    }

    interface Presenter : BasePresenter {
        fun getNewCommentLists(groupId: String, itemId: Long)
    }


}
