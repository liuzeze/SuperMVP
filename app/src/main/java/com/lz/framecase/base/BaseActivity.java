package com.lz.framecase.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lz.fram.app.App;
import com.lz.fram.base.BasePresenter;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.lz.framecase.component.ActivityComponent;
import com.lz.framecase.component.DaggerActivityComponent;
import com.vondear.rxtool.view.RxToast;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initConfig();
        initInject();
        onViewCreated();
        initData();
    }

    /**
     * 初始化公用的参数
     */
    private void initConfig() {
        mActivity = this;
        //ButterKnife 初始化
        ButterKnife.bind(this);
    }

    /**
     * 关联appCompomponet
     *
     * @return
     */
    protected ActivityComponent getActivityComponent() {
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
        super.onDestroy();

    }
    @Override
    public void showErrorMsg(String msg) {
        RxToast.error(msg);
    }
    /**
     * 获取资源文件
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 注解当前activity
     */
    protected abstract void initInject();

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
