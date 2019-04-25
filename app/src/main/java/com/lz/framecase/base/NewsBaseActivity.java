package com.lz.framecase.base;


import com.gyf.barlibrary.ImmersionBar;
import com.lz.fram.utils.RxLifecycleUtils;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.utils.SettingUtils;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.app.BaseActivity;
import com.lz.utilslib.interceptor.utils.ToastUtils;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class NewsBaseActivity extends BaseActivity {


    private ImmersionBar mImmersionBar;

    /**
     * 初始化公用的参数
     */
    @Override
    protected void initConfig() {
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
            setSwipeBackEnable(false);
        }
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

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }


}
