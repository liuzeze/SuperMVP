package com.lz.framecase.api;


import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.observer.CommonSubscriber;
import com.lz.fram.observer.Transformer;
import com.lz.framecase.bean.MultNewsBean;
import com.vondear.rxtool.RxTimeTool;

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


    public CommonSubscriber<MultNewsBean> getNewLists(String category, CommonSubscriber<MultNewsBean> subscriber) {
        return
                mRetrofit.create(ApiService.class)
                        .getNewsArticle(category, RxTimeTool.getCurTimeMills()/1000 + "")
                        .compose(Transformer.<MultNewsBean>switchSchedulers(mLpLoadDialog))
                        .subscribeWith(subscriber);

    }

}
