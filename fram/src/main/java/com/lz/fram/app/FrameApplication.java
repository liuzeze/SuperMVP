package com.lz.fram.app;

import android.app.Application;
import android.content.Context;


import com.lz.fram.component.AppComponent;

import io.reactivex.annotations.NonNull;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-28       基类application
 */
public class FrameApplication extends Application implements App {


    /**
     * 获取应用程序组件
     */
    public static FrameApplication mApplication;

    private boolean isHostError;

    protected ConfigDelegate mConfigDelegate;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mConfigDelegate == null) {
            mConfigDelegate = new ConfigDelegate(base);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        if (mConfigDelegate != null) {
            mConfigDelegate.init(mApplication);
        }

    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return mConfigDelegate.getAppComponent();
    }

    public boolean isHostError() {
        return isHostError;
    }

    public void setHostError(boolean hostError) {
        isHostError = hostError;
    }
}
