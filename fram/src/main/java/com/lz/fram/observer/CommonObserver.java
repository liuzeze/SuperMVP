package com.lz.fram.observer;


import com.lz.fram.base.BaseView;
import com.lz.fram.net.http.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			  公用的订阅处理
 */
public abstract class CommonObserver<T> implements Observer<T> {
    protected BaseView mView;

    protected CommonObserver() {
    }

    protected CommonObserver(BaseView view) {
        this.mView = view;
    }


    @Override
    public void onSubscribe(Disposable d) {

    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        ApiException apiException = ApiException.handleException(e);
        String message = apiException.getLocalizedMessage();
        int code = apiException.getCode();
        onError(message);
        if (mView == null) {
            return;
        }
        mView.showErrorMsg(code + " : " + message);
    }

    @Override
    public void onComplete() {
        //极端情况这里不会执行

    }


    protected void onError(String mes) {

    }
}