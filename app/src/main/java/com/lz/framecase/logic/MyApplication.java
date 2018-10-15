package com.lz.framecase.logic;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.lz.fram.app.FrameApplication;
import com.lz.framecase.BuildConfig;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.anotation.ClassRuntime;
import com.lz.framecase.utils.SettingUtils;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.app.ScreenAdaptation;
import com.lz.utilslib.interceptor.utils.LzAppUtils;
import com.next.uceh.ExceptionInfoBean;
import com.next.uceh.UCECallback;
import com.next.uceh.UCEDefaultActivity;
import com.next.uceh.UCEHandler;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxAppTool;
import com.vondear.rxtool.RxTool;

import java.util.List;

import me.ele.uetool.UETool;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-03       创建class
 */
@ClassRuntime()
public class MyApplication extends FrameApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        SkinManager.getInstance().init(this, new AttrChangeLisnter());
        if (SettingUtils.Companion.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        registerActivityCycle();
        Beta.initDelay = 3 * 1000;
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Bugly.init(this, BuildConfig.BUGGLY_APPID, true);

        if (BuildConfig.DEBUG) {
            new UCEHandler.Builder(getApplicationContext())
                    .setServiceUrl("https://oapi.dingtalk.com/robot/send?access_token=751492721368e26c2ffb52b6ce43481eca37b7a71eef2c3a09e47eda32000aae")
                    .build();
            if (LzAppUtils.isCurrentProcess()) {
                UETool.showUETMenu();
            }
            // GTRController.init(this);
            BlockCanary.install(this, new BlockCanaryContext()).start();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        super.attachBaseContext(base);
        // 安装tinker
        Beta.installTinker();
    }


    /**
     * activity周期管理
     */
    private void registerActivityCycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //栈管理
                RxActivityTool.addActivity(activity);
                //  new ScreenAdaptation(activity,720).register();
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                RxActivityTool.finishActivity(activity);
            }
        });
    }

}
