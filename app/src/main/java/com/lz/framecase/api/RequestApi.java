package com.lz.framecase.api;


import com.google.gson.Gson;
import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.observer.CommonSubscriber;
import com.lz.fram.observer.Transformer;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * 网络请求
 */
public class RequestApi {
    Retrofit mRetrofit;
    Gson mGson;
    @Inject
    LpLoadDialog mLpLoadDialog;

    @Inject
    public RequestApi(Retrofit retrofit, Gson gson) {
        mRetrofit = retrofit;
        mGson = gson;
    }

    /**
     * 登录接口
     *
     * @param loginName  登录名

     * @param subscriber
     * @return
     */

    public CommonSubscriber<String> login(String loginName,  CommonSubscriber<String> subscriber) {
        return mRetrofit.create(ApiService.class)
                .login(loginName)
                .compose(Transformer.<String>switchSchedulers(mLpLoadDialog))
                .subscribeWith(subscriber);
    }

}
