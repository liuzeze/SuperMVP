package com.lz.framecase.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lz.fram.utils.RxLifecycleUtils;
import com.lz.framecase.utils.SettingUtils;
import com.lz.utilslib.interceptor.app.BaseFragment;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class NewsBaseFragment extends BaseFragment {

    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        if (!SettingUtils.Companion.getSlideBackMode()) {
            // 是否允许滑动
            setSwipeBackEnable(false);
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

}
