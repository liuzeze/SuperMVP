package com.lz.framecase.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonObserver
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.NewsCommentBean


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class NewsCommentPresenter : RxPresenter<NewsCommentContract.View>(), NewsCommentContract.Presenter {
    var mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi();
    }

    /**
     * 登录
     */
    override fun getNewCommentLists(groupId: String, itemId: Long) {
        val newsComment = mRequestApi.getNewsComment(groupId, itemId)
        newsComment

                ?.`as`(bindLifecycle<NewsCommentBean>())
                ?.subscribeWith(object : CommonObserver<NewsCommentBean>(mBaseView) {
                    override fun onNext(s: NewsCommentBean) {



                        val data = s.data;
                        val arrayList = ArrayList<NewsCommentBean.DataBean>()

                        for (newsdata in data?.iterator()!!) {

                            arrayList.add(newsdata)
                        }
                        mBaseView.getNewsCommentSuccess(arrayList)
                    }
                })

    }
}