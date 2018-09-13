package com.lz.framecase.base;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.logic.MyApplication;
import com.lz.inject_annotation.InjectUtils;
import com.lz.utilslib.interceptor.base.InjectTools;
import com.lz.utilslib.interceptor.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public class BaseUtils<T extends ViewDataBinding> implements BaseView {


    View convertView;
    protected Context mContext;
    private PresenterDispatch mPresenterDispatch;
    private Unbinder mUnbinder;
    private T mBind;


    protected BaseUtils(@Nullable Context context) {
        mContext = context;
        init();

    }


    private void init() {
        //初始化convertView
        if (getLayout() != -1) {
            LayoutInflater inflater = LayoutInflater.from(mContext == null ? MyApplication.mApplication : mContext);
            try {
                mBind = DataBindingUtil.inflate(inflater,
                        getLayout(), null, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mBind == null) {
                convertView = inflater.inflate(getLayout(), null, false);
            } else {
                convertView = mBind.getRoot();
            }
            mUnbinder = ButterKnife.bind(this, convertView);
        }

        InjectUtils annotation = this.getClass().getAnnotation(InjectUtils.class);
        if (annotation != null) {
            InjectTools.inject(this);
        }
        attachView();
        initLisenter();
    }

    protected void initLisenter() {
    }

    public View getHolderView() {
        return convertView;
    }


    protected void attachView() {
        PresenterProviders providers = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(providers);
        mPresenterDispatch.attachView(this);

    }

    public void detachView() {
        if (mPresenterDispatch != null) {
            mPresenterDispatch.detachView();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


    protected int getLayout() {
        return -1;
    }


    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
    }
}
