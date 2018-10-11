package com.lz.fram.base;


import android.arch.lifecycle.LifecycleOwner;
import android.widget.Toast;

import com.lz.fram.app.FrameApplication;
import com.lz.fram.scope.CallBackAnnotion;
import com.lz.fram.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	    刘泽			   presenter基类用于管理订阅监听以及注册view
 * 2017/12/26	9:24	    刘泽			    增加yyy属性
 */

public class RxPresenter<T extends BaseView> implements BasePresenter {
    protected T mBaseView;
    private LifecycleOwner mLifecycleOwner;

    private HashMap<Object, Disposable> mapDisposable;


    /**
     * 添加带有标记的订阅，方便手动注销，以免重复的订阅响应
     *
     * @param tag          订阅标记
     * @param subscription 订阅者
     */
    protected void addSubscribe(String tag, Disposable subscription) {
        if (mapDisposable == null) {
            mapDisposable = new HashMap<>();
        }
        //添加前先移除
        removeSubscribe(tag);
        //加入到管理
        mapDisposable.put(tag, subscription);
    }


    /**
     * 为了防止重复请求，这里我们进行移除指定的订阅者
     *
     * @param tag 订阅者标记
     */
    private void removeSubscribe(String tag) {
        if (mapDisposable != null) {
            //取出队列中订阅者进行主动消费掉
            Disposable disposable = mapDisposable.get(tag);

            if (disposable != null) {
                //主动的进行消费
                disposable.dispose();
                //从管理集合中移除
                mapDisposable.remove(tag);
            }
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
    }

    @Override
    public void detachView() {
        this.mBaseView = null;
    }


    @Override
    public void onDestroy(LifecycleOwner owner) {
        detachView();
    }

    protected void callBack(String tag, Object... obj) {
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
                        if (mBaseView != null) {
                            mBaseView.showErrorMsg(e.getMessage());
                        } else {
                            Toast.makeText(FrameApplication.mApplication, "mBaseView is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
            }

        }
    }
}
