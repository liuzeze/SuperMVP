package com.lz.fram.net.download;


import com.lz.fram.net.http.OkhttpFactory;
import com.lz.fram.net.http.RetrofitFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-16       创建class
 */
public class DownLoadFactory {

    private static DownLoadFactory instance;
    private final Retrofit mRetrofit;

    public static DownLoadFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new DownLoadFactory();
                }
            }
        }
        return instance;
    }

    public DownLoadFactory() {
        OkHttpClient build = OkhttpFactory.getInstance().getOkhttpBuilder().build();

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.baidu.com")
                .client(build)
                .build();

    }

    public DownloadApi getRetrofitClient() {
        return mRetrofit.create(DownloadApi.class);
    }

}
