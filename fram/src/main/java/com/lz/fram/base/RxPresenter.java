package com.lz.fram.base;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.widget.Toast;

import com.lz.fram.observer.ObserverManager;
import com.lz.fram.scope.CallBackAnnotion;
import com.lz.fram.utils.LzInitUtils;
import com.lz.fram.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

import java.lang.reflect.Method;

import io.reactivex.disposables.Disposable;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	    刘泽			   presenter基类用于管理订阅监听以及注册view
 * 2017/12/26	9:24	    刘泽			    增加yyy属性
 */

public class RxPresenter<T extends BaseView> implements BasePresenter {
    protected T mBaseView;
    protected Context mContext;
    private LifecycleOwner mLifecycleOwner;


    protected ObserverManager mObserverManager;

    /**
     * 添加带有标记的订阅，方便手动注销，以免重复的订阅响应
     *
     * @param tag        订阅标记
     * @param disposable 订阅者
     */
    public void addSubscribe(String tag, Disposable disposable) {

        if (mObserverManager == null) {
            mObserverManager = new ObserverManager();
        }
        mObserverManager.add(tag, disposable);
    }


    /**
     * 为了防止重复请求，这里我们进行移除指定的订阅者
     *
     * @param tag 订阅者标记
     */
    public void removeSubscribe(String tag) {
        if (mObserverManager != null) {
            mObserverManager.cancel(tag);
        }
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == mLifecycleOwner) {
            throw new NullPointerException("lifecycleOwner == null");
        }
        return RxLifecycleUtils.bindLifecycle(mLifecycleOwner);
    }


    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        this.mBaseView = (T) lifecycleOwner;
        mContext = mBaseView.getContext();
    }


    @Override
    public void onDestroy(LifecycleOwner owner) {
        this.mBaseView = null;
        mContext = null;
        if (mObserverManager != null) {
            mObserverManager.cancelAll();
        }
    }


    /**
     * 注解回调方式
     *
     * @param tag
     * @param obj
     */
    @Deprecated
    protected void callBack(String tag, Object... obj) {
        if (mBaseView == null) {
            throw new NullPointerException("mBaseView is null");
        }
        Method[] declaredMethods = mBaseView.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            CallBackAnnotion annotation = declaredMethod.getAnnotation(CallBackAnnotion.class);
            if (annotation != null) {
                if (annotation.value().equals(tag)) {
                    try {
                        declaredMethod.invoke(mBaseView, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mBaseView.showErrorMsg(e.getMessage());

                    }
                    break;
                }
            }

        }

    }
}
