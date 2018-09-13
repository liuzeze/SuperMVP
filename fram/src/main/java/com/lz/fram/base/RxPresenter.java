package com.lz.fram.base;


import android.arch.lifecycle.LifecycleOwner;

import com.lz.fram.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	    刘泽			   presenter基类用于管理订阅监听以及注册view
 * 2017/12/26	9:24	    刘泽			    增加yyy属性
 */

public class RxPresenter<T extends BaseView> implements BasePresenter {

    protected T mView;
    private LifecycleOwner mLifecycleOwner;


    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == mLifecycleOwner) {
            throw new NullPointerException("lifecycleOwner == null");
        }
        return RxLifecycleUtils.bindLifecycle(mLifecycleOwner);
    }


    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        this.mView = (T) lifecycleOwner;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }


    @Override
    public void onDestroy(LifecycleOwner owner) {
        detachView();
    }


}
