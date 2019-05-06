package com.lz.framecase.logic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.lz.fram.net.RxRequestUtils;
import com.lz.framecase.BuildConfig;
import com.lz.framecase.activity.MainActivity;
import com.lz.framecase.anotation.ClassRuntime;
import com.lz.framecase.utils.SettingUtils;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.app.ScreenAdaptation;
import com.lz.utilslib.interceptor.utils.LzAppUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxTool;

import lz.com.acatch.CatchHandler;
import me.ele.uetool.UETool;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-03       创建class
 */
@ClassRuntime()
public class MyApplication extends Application {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        registerActivityCycle();
    }


    @Override
    protected void attachBaseContext(Context base) {
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        super.attachBaseContext(base);


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
                new ScreenAdaptation(activity, 720).register();
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
