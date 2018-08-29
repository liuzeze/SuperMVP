package com.lz.framecase.fragment.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.framecase.bean.MultNewsBean
import com.vondear.rxtool.view.RxToast
import javax.inject.Inject

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class NewsListPresenter @Inject
constructor(var mRequestApi: RequestApi)
    : RxPresenter<NewsListContract.View>(), NewsListContract.Presenter {

    /**
     * 登录
     */
    override fun getNewLists(category: String?) {
        val subscriber = object : CommonSubscriber<MultNewsBean>(mView) {
            override fun onNext(bean: MultNewsBean) {
                mView.getNewsListSuccess(bean)
            }
        }
        addSubscribe("getZhiHuNews", mRequestApi.getNewLists(category, subscriber))

    }
}