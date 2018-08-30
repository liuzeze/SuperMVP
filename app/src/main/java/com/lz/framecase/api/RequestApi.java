package com.lz.framecase.api;


import com.lz.fram.base.LpLoadDialog;
import com.lz.fram.observer.CommonSubscriber;
import com.lz.fram.observer.Transformer;
import com.lz.framecase.bean.MultNewsBean;
import com.lz.framecase.bean.WendaArticleBean;
import com.lz.framecase.bean.WendaArticleDataBean;
import com.vondear.rxtool.RxTimeTool;

import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
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


    public CommonSubscriber<MultNewsBean> getNewLists(String category, String time, CommonSubscriber<MultNewsBean> subscriber) {

        int i = new Random().nextInt(10);
        if (i % 2 == 0) {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle(category, time)
                            .compose(Transformer.<MultNewsBean>switchSchedulers(mLpLoadDialog))
                            .subscribeWith(subscriber);
        } else {
            return
                    mRetrofit.create(ApiService.class)
                            .getNewsArticle(category, time)
                            .compose(Transformer.<MultNewsBean>switchSchedulers(mLpLoadDialog))
                            .subscribeWith(subscriber);
        }


    }

    public CommonSubscriber<WendaArticleBean> getWenDaLists(String time, CommonSubscriber<WendaArticleBean> subscriber) {
        return
                mRetrofit.create(ApiService.class)
                        .getWendaArticle(time)
                        .compose(Transformer.<WendaArticleBean>switchSchedulers(mLpLoadDialog))
                        .subscribeWith(subscriber);

    }

}
