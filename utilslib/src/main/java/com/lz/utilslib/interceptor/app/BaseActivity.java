package com.lz.utilslib.interceptor.app;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.inject_annotation.InjectActivity;
import com.lz.inject_annotation.InjectTools;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseActivity extends SwipeBackActivity implements BaseView {

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mActivity = this;
        initConfig();
        initInject();
        initViewData();
        initLisenter();
    }


    /**
     * 初始化公用的参数
     */
    protected abstract void initConfig();

    /**
     * 为presenter 注册毁掉
     */
    private void initInject() {
        InjectActivity annotation = this.getClass().getAnnotation(InjectActivity.class);
        if (annotation != null) {
            InjectTools.inject(this);
        }
        PresenterDispatch presenterDispatch = PresenterProviders.inject(this).presenterCreate();
        presenterDispatch.attachView(this, getLifecycle());
    }


    @Override
    public Context getContext() {
        return mActivity;
    }

    /**
     * 获取资源文件
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化数据
     */
    protected abstract void initViewData();

    protected void initLisenter() {
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        ActivityCompat.finishAfterTransition(this);
    }
}
