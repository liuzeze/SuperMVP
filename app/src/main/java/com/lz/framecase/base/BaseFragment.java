package com.lz.framecase.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.component.DaggerFragmentComponent;
import com.lz.framecase.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {

    @Inject
    protected T mPresenter;
    protected Context mContext;
    private Unbinder mUnbinder;
    private View rootView;
    private PresenterDispatch mPresenterDispatch;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        boolean initialized = rootView != null;
        if (!initialized) {
            rootView = inflater.inflate(getLayout(), container, false);
            mUnbinder = ButterKnife.bind(this, rootView);
            initInject();
        }
        onViewCreated();
        if (!initialized) {
            initData();
        }

//        ViewParent vp = rootView.getParent();
//        if(vp != null){
//            ((ViewGroup)vp).removeAllViews();
//        }

        return rootView;
    }

    protected FragmentComponent getFragmentComponent() {
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

    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initData();
}
