package com.lz.framecase.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.fram.utils.RxLifecycleUtils;
import com.lz.framecase.anotation.MethodDot;
import com.lz.framecase.utils.SettingUtils;
import com.lz.inject_annotation.InjectFragment;
import com.lz.inject_annotation.InjectTools;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseFragment extends SwipeBackFragment implements BaseView {

    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        if (!SettingUtils.Companion.getSlideBackMode()) {
            setSwipeBackEnable(false); // 是否允许滑动
        }
        InjectFragment annotation = this.getClass().getAnnotation(InjectFragment.class);
        if (annotation != null) {
            InjectTools.inject(this);
        }
        onViewCreated();
        return attachToSwipeBack(rootView);
    }

    @Override
    @MethodDot("onViewCreated")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewData();
        initLisenter();
    }

    protected void initLisenter() {
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    protected void onViewCreated() {
        PresenterDispatch mPresenterDispatch = PresenterProviders.inject(this).presenterCreate();
        mPresenterDispatch.attachView(this, getLifecycle());
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

    protected abstract int getLayout();


    protected abstract void initViewData();
}
