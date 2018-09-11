package com.lz.framecase.base;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.component.DaggerUtilsComponent;
import com.lz.framecase.component.UtilsComponent;
import com.lz.framecase.logic.MyApplication;
import com.lz.utilslib.interceptor.base.InjectUtils;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.vondear.rxtool.RxTool;
import com.vondear.rxtool.view.RxToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public class BaseUtils implements BaseView {


    View convertView;
    protected Context mContext;
    private PresenterDispatch mPresenterDispatch;
    private Unbinder mUnbinder;
    private ViewDataBinding mBind;


    public BaseUtils() {
        init();
    }

    public BaseUtils(Context context) {
        mContext = context;
        init();

    }


    private void init() {
        //初始化convertView
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

        initInject();
        attachView();
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

    protected void initInject() {
        InjectUtils.inject(this);
    }

    protected int getLayout() {
        return -1;
    }


    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
    }
}
