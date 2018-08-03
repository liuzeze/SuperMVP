package com.lz.framecase.logic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.lz.fram.app.FrameApplication;
import com.lz.utilslib.interceptor.app.ScreenAdaptation;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxTool;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-03       创建class
 */
public class MyApplication extends FrameApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);

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
                //dp sp 适配
                new ScreenAdaptation(activity, 1366).register();
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
