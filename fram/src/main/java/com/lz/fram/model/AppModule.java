package com.lz.fram.model;


import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lz.fram.base.LpLoadDialog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.Nullable;

/**
 * ----- 日期 ---------- 维护人 ---------- 变更内容 ----------
 * 2017/4/26 11:14	    刘泽              全局参数初始化配置
 */

@Module
public class AppModule {
    @Singleton
    @Provides
    static Gson provideGson(Application application, @Nullable GsonConfiguration configuration) {
        GsonBuilder builder = new GsonBuilder();
        if (configuration != null) {
            configuration.configGson(application, builder);
        }
        return builder.create();
    }

    public interface GsonConfiguration {
        void configGson(Context context, GsonBuilder builder);
    }

    @Singleton
    @Provides
    static LpLoadDialog provideDialog(Application application, @Nullable GsonConfiguration configuration) {
        LpLoadDialog lpDialog = new LpLoadDialog(application);
        lpDialog.setOnWhole();
        return lpDialog;
    }

}
