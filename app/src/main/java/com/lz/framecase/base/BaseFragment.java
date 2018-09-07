package com.lz.framecase.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.component.DaggerFragmentComponent;
import com.lz.framecase.component.FragmentComponent;
import com.lz.framecase.utils.SettingUtils;
import com.lz.utilslib.interceptor.base.InjectUtils;
import com.lz.utilslib.interceptor.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SwipeBackFragment implements BaseView {

    @Inject
    protected T mPresenter;
    protected Context mContext;
    private Unbinder mUnbinder;
    private PresenterDispatch mPresenterDispatch;

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
        InjectUtils.inject(this);
        onViewCreated();

        return attachToSwipeBack(rootView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public FragmentComponent getObjectComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(((App) mContext.getApplicationContext()).getAppComponent())
                .build();
    }

    protected void onViewCreated() {
        PresenterProviders providers = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(providers);
        mPresenterDispatch.attachView(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mPresenterDispatch != null) {
            mPresenterDispatch.detachView();

        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
    }

    protected abstract int getLayout();


    protected abstract void init();
}
