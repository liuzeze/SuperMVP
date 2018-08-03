package com.lz.framecase.presenter;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.SystemClock;
import android.util.Log;

import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.base.RxPresenter;
import com.lz.fram.observer.CommonSubscriber;
import com.lz.framecase.api.RequestApi;
import com.vondear.rxtool.view.RxToast;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
public class Main2Presenter extends RxPresenter<Main2Contract.View> implements Main2Contract.Presenter {


    RequestApi mRequestApi;


    @Inject
    public Main2Presenter(RequestApi requestApi) {
        this.mRequestApi = requestApi;
    }

    /**
     * 登录
     */
    @Override
    public void getNewLists() {
        CommonSubscriber<String> subscriber = new CommonSubscriber<String>(mView) {
            @Override
            public void onNext(final String s) {
                RxToast.info(s);

            }
        };
        addSubscribe("getZhiHuNews", mRequestApi.getNewLists(subscriber));

    }
}