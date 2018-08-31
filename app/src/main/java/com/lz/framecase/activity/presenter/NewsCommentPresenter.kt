package com.lz.framecase.presenter


import com.google.gson.Gson
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.NewsCommentBean
import com.lz.utilslib.interceptor.utils.ToastUtils
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
        val subscriber = object : CommonSubscriber<NewsCommentBean>(mView) {
            override fun onNext(s: NewsCommentBean) {
                val data = s.data;
                val arrayList = ArrayList<NewsCommentBean.DataBean>()

                for (newsdata in data?.iterator()!!) {

                    arrayList.add(newsdata)
                }
                mView.getNewsCommentSuccess(arrayList)
            }
        }
        addSubscribe("getNewCommentLists", mRequestApi.getNewsComment(groupId, itemId, subscriber))

    }
}