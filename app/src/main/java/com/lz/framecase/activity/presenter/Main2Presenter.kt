package com.lz.framecase.activity.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.utilslib.interceptor.utils.ToastUtils
import com.vondear.rxtool.RxTimeTool
import javax.inject.Inject


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class Main2Presenter @Inject
constructor(internal var mRequestApi: RequestApi) : RxPresenter<Main2Contract.View>(), Main2Contract.Presenter {

    /**
     * 登录
     */
    override fun getNewLists() {
        val subscriber = object : CommonSubscriber<String>(mView) {
            override fun onNext(s: String) {
                ToastUtils.info(s)

            }
        }
        addSubscribe("getZhiHuNews", mRequestApi.getNewLists("",(RxTimeTool.getCurTimeMills()/1000).toString(),subscriber))

    }
}