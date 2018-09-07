package com.lz.framecase.logic;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;

import com.lz.fram.model.ConfigModule;
import com.lz.fram.model.GlobalConfigBuild;
import com.lz.framecase.component.DaggerAppComponent;
import com.lz.framecase.component.ManifestParser;
import com.lz.framecase.env.SuspensionView;
import com.lz.framecase.utils.SettingUtils;
import com.lz.skinlibs.SkinManager;
import com.lz.utilslib.interceptor.app.ScreenAdaptation;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxTool;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.DaggerApplication;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-03       创建class
 */
public class MyApplication extends dagger.android.support.DaggerApplication {

    public static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RxTool.init(this);
        SkinManager.getInstance().init(this, new AttrChangeLisnter());
        SuspensionView.getInstance().init(this);
        if (SettingUtils.Companion.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        registerActivityCycle();

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
                new ScreenAdaptation(activity, 2500).register();
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


    @Override
    protected AndroidInjector<? extends dagger.android.support.DaggerApplication> applicationInjector() {
        AndroidInjector<MyApplication> applicationAndroidInjector = DaggerAppComponent
                .builder()
                //提供application
                .application(this)
                //全局配置
                .globalConfigModule(getGlobalConfigModule(new ManifestParser(this).parse()))
                .create(this);
        return applicationAndroidInjector;
    }

    /**
     * 设置全局参数  包括网络请求层
     *
     * @param modules
     * @return GlobalConfigBuild
     */
    private static GlobalConfigBuild getGlobalConfigModule(List<ConfigModule> modules) {
        GlobalConfigBuild.Builder builder = GlobalConfigBuild
                .builder();
        for (ConfigModule module : modules) {
            // 给全局配置 GlobalConfigBuild 添加参数
            module.applyOptions(builder);
        }
        return builder.build();
    }

}
