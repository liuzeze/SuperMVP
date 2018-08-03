package com.lz.framecase.api;


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
    @Inject
    LpLoadDialog mLpLoadDialog;

    @Inject
    public RequestApi(Retrofit retrofit) {
        mRetrofit = retrofit;
    }


    public CommonSubscriber<String> getNewLists(CommonSubscriber<String> subscriber) {
        return
                mRetrofit.create(ApiService.class)
                        .getNewLists()
                        .compose(Transformer.<String>switchSchedulers(mLpLoadDialog))
                        .subscribeWith(subscriber);

    }

}
