package com.lz.framecase.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.R;
import com.lz.framecase.component.ActivityComponent;
import com.lz.framecase.component.DaggerActivityComponent;
import com.lz.utilslib.interceptor.base.InjectUtils;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.vondear.rxtool.RxDeviceTool;
import com.vondear.rxtool.view.RxToast;
import com.zhy.changeskin.SkinManager;
import com.zhy.changeskin.utils.PrefUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {

    @Inject
    protected T mPresenter;
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
        onCreate();
    }

    /**
     * 初始化公用的参数
     */
    private void initConfig() {
        mActivity = this;
        ButterKnife.bind(this);
        SkinManager.getInstance().register(this);
        //ButterKnife 初始化
        String suffix = new PrefUtils(this).getSuffix();
        mImmersionBar = ImmersionBar.with(this);
        //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
        mImmersionBar.barColor(suffix == "red" ? R.color.app_them_red : R.color.app_them_blue)
                //状态栏和导航栏变色后的颜色
                //  .barColorTransform(R.color.orange)
                //必须调用方可沉浸式
                .init();
    }

    /**
     * 关联appCompomponet
     *
     * @return
     */
    public ActivityComponent getObjectComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .build();
    }

    /**
     * 为presenter 注册毁掉
     */
    protected void onViewCreated() {
        PresenterProviders providers = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(providers);
        mPresenterDispatch.attachView(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 销毁毁掉
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mPresenterDispatch != null) {
            mPresenterDispatch.detachView();

        }
        mImmersionBar.destroy();
        SkinManager.getInstance().unregister(this);
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
    protected abstract void onCreate();

}
