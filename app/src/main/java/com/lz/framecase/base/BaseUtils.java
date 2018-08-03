package com.lz.framecase.base;


import android.content.Context;
import android.view.View;

import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.component.DaggerUtilsComponent;
import com.lz.framecase.component.UtilsComponent;
import com.vondear.rxtool.RxTool;
import com.vondear.rxtool.view.RxToast;

import javax.inject.Inject;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public class BaseUtils<T extends BasePresenter> implements BaseView {

    @Inject
    protected T mPresenter;
    View convertView;
    protected Context mContext;
    private PresenterDispatch mPresenterDispatch;

    public BaseUtils(Context context) {
        mContext = context;
        //初始化convertView
        convertView = initView();
        initInject();
        attachView();
    }

    public BaseUtils() {
        //初始化convertView
        convertView = initView();
        initInject();
        attachView();
    }


    public View getHolderView() {
        return convertView;
    }

    protected UtilsComponent getUtilsComponent() {
        return DaggerUtilsComponent.builder()
                .appComponent(((App) RxTool.getContext().getApplicationContext()).getAppComponent())
                .build();
    }

    protected void attachView() {
        PresenterProviders providers = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(providers);
        mPresenterDispatch.attachView(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    public void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mPresenterDispatch != null) {
            mPresenterDispatch.detachView();
        }
    }

    protected void initInject() {
    }

    protected View initView() {
        return null;
    }


    @Override
    public void showErrorMsg(String msg) {
        RxToast.error(msg);
    }
}
