package com.lz.framecase.presenter


import android.util.Log
import android.widget.Toast
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.anotation.ObjectUtil
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.NewsCommentBean
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.tencent.bugly.proguard.s
import io.reactivex.*
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe
import io.reactivex.internal.subscriptions.SubscriptionHelper.cancel
import java.util.concurrent.TimeUnit


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