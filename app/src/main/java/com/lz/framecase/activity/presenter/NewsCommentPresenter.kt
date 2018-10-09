package com.lz.framecase.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.NewsCommentBean
import javax.inject.Inject


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class NewsCommentPresenter @Inject
constructor(internal var mRequestApi: RequestApi) : RxPresenter<NewsCommentContract.View>(), NewsCommentContract.Presenter {

    /**
     * 登录
     */
    override fun getNewCommentLists(groupId: String, itemId: Long) {
        val newsComment = mRequestApi.getNewsComment(groupId, itemId)
        newsComment
                ?.`as`(bindLifecycle<NewsCommentBean>())
                ?.subscribeWith(object : CommonSubscriber<NewsCommentBean>(mBaseView) {
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