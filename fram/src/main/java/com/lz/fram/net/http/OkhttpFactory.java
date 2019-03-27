package com.lz.fram.net.http;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-16       创建class
 */
public class OkhttpFactory {

    private static final int TIME_OUT = 15;

    private static OkhttpFactory instance;
    private OkHttpClient.Builder mBuilder;

    public static OkhttpFactory getInstance() {
        if (instance == null) {
            synchronized (OkhttpFactory.class) {
                if (instance == null) {
                    instance = new OkhttpFactory();
                }
            }

        }
        return instance;
    }

    public OkhttpFactory() {

        initOkhttpClient();


    }

    private void initOkhttpClient() {
        GlobalConfigBuild configBuild = HttpConfigFactory.getInstance().getConfigBuild();

        mBuilder = new OkHttpClient.Builder();
        //如果外部提供了interceptor的集合则遍历添加
        if (configBuild != null) {
            if (configBuild.getInterceptors() != null) {
                for (Interceptor interceptor : configBuild.getInterceptors()) {
                    mBuilder.addInterceptor(interceptor);
                }
            }
            if (configBuild.getNetInterceptors() != null) {
                for (Interceptor interceptor : configBuild.getNetInterceptors()) {
                    mBuilder.addNetworkInterceptor(interceptor);
                }
            }
            if (configBuild.getOkhttpConfiguration() != null) {
                configBuild.getOkhttpConfiguration().configOkhttp(mBuilder);
            }
        }
        mBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        mBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        mBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);

    }

    public OkHttpClient.Builder getOkhttpBuilder() {
        return mBuilder;
    }


    public interface OkhttpConfiguration {
        void configOkhttp(OkHttpClient.Builder builder);
    }
}
