package com.lz.fram.observer;


import com.lz.fram.base.BaseView;
import com.lz.fram.exception.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			  公用的订阅处理
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;

    protected CommonSubscriber() {
    }

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String message = ApiException.handleException(e).getLocalizedMessage();
        onError(message);
        if (mView == null) {
            return;
        }
        mView.showErrorMsg(message);
    }

    protected void onError(String mes) {

    }
}