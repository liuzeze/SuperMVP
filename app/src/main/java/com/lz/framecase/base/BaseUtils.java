package com.lz.framecase.base;


import android.content.Context;
import android.view.View;

import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.utilslib.interceptor.base.InjectUtils;
import com.lz.utilslib.interceptor.utils.ToastUtils;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public class BaseUtils implements BaseView {


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



    protected void attachView() {
        PresenterProviders providers = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(providers);
        mPresenterDispatch.attachView(this);

    }

    public void detachView() {
        if (mPresenterDispatch != null) {
            mPresenterDispatch.detachView();
        }
    }

    protected void initInject() {
        InjectUtils.inject(this);
    }

    protected View initView() {
        return null;
    }


    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
    }
}
