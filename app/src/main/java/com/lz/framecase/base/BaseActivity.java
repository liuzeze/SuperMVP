package com.lz.framecase.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.gyf.barlibrary.ImmersionBar;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.base.InjectUtils;
import com.lz.utilslib.interceptor.utils.ToastUtils;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseActivity extends SwipeBackActivity implements BaseView {

    protected Activity mActivity;
    private PresenterDispatch mPresenterDispatch;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initConfig();
        InjectUtils.inject(this);
        onViewCreated();
        init();

    }

    /**
     * 初始化公用的参数
     */
    private void initConfig() {
        mActivity = this;
        //ButterKnife 初始化
        ButterKnife.bind(this);
        try {
            SkinManager.getInstance().register(this);
            mImmersionBar = ImmersionBar.with(this);
            //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
            mImmersionBar.barColor(SkinManager.getInstance().getThemColor())
                    .init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //   setSwipeBackEnable(false); // 是否允许滑动

    }

    /**
     * 为presenter 注册毁掉
     */
    protected void onViewCreated() {
        mPresenterDispatch = PresenterProviders.inject(this).presenterCreate();
        mPresenterDispatch.attachView(this);
    }

    /**
     * 销毁毁掉
     */
    @Override
    protected void onDestroy() {
        try {
            if (mPresenterDispatch != null) {
                mPresenterDispatch.detachView();

            }
            mImmersionBar.destroy();
            SkinManager.getInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();

    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.error(msg);
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
    protected abstract void init();

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        ActivityCompat.finishAfterTransition(this);
    }
}
