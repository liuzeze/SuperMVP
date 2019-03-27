package com.lz.framecase.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.gyf.barlibrary.ImmersionBar;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.fram.utils.RxLifecycleUtils;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.utils.SettingUtils;
import com.lz.inject_annotation.InjectActivity;
import com.lz.inject_annotation.InjectTools;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseActivity extends SwipeBackActivity implements BaseView {

    protected Activity mActivity;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( getLayout());
        initConfig();
        onViewCreated();
        initViewData();
        initLisenter();
    }

    protected void initLisenter() {
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
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

        if (!SettingUtils.Companion.getSlideBackMode() ||
                this instanceof MainActivity) {
            setSwipeBackEnable(false); // 是否允许滑动
        }
    }

    /**
     * 为presenter 注册毁掉
     */
    protected void onViewCreated() {
        InjectActivity annotation = this.getClass().getAnnotation(InjectActivity.class);
        if (annotation != null) {
            InjectTools.inject(this);
        }
        PresenterDispatch mPresenterDispatch = PresenterProviders.inject(this).presenterCreate();
        mPresenterDispatch.attachView(this, getLifecycle());
    }

    /**
     * 销毁毁掉
     */
    @Override
    protected void onDestroy() {
        try {
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
    protected abstract void initViewData();

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        ActivityCompat.finishAfterTransition(this);
    }
}
