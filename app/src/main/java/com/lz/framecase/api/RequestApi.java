package com.lz.framecase.api;


import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.observer.CommonSubscriber;
import com.lz.fram.observer.Transformer;

import java.util.Random;

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


    public CommonSubscriber<String> getNewLists(String category, String time, CommonSubscriber<String> subscriber) {

        int i = new Random().nextInt(10);
        if (i % 2 == 0) {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle(category, time)
                            .compose(Transformer.<String>switchSchedulers())
                            .subscribeWith(subscriber);
        } else {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle2(category, time)
                            .compose(Transformer.<String>switchSchedulers())
                            .subscribeWith(subscriber);
        }


    }

}
