package com.lz.framecase.presenter


import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber
import com.lz.framecase.api.RequestApi
import com.lz.utilslib.interceptor.utils.ToastUtils
import javax.inject.Inject


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class EmptyPresenter @Inject constructor()
    : RxPresenter<EmptyContract.View>(), EmptyContract.Presenter