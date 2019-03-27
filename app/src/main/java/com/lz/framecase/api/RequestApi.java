package com.lz.framecase.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lz.fram.net.RxRequestUtils;
import com.lz.fram.observer.Transformer;
import com.lz.framecase.bean.MultNewsBean;
import com.lz.framecase.bean.WendaArticleBean;

import java.util.Random;

import io.reactivex.Flowable;

/**
 * 网络请求
 */
public class RequestApi {


    public Flowable<String> getNewLists(String category, String time) {

        int i = new Random().nextInt(10);
        if (i % 2 == 0) {
            return
                    RxRequestUtils
                            .create(ApiService.class)
                            .getNewsArticle3(category, time)
                            .compose(Transformer.<String>switchSchedulers());
        } else {
            return
                    RxRequestUtils
                            .create(ApiService.class)
                            .getNewsArticle3(category, time)
                            .compose(Transformer.<String>switchSchedulers());
        }


    }
    public Flowable<WendaArticleBean> getWenDaLists(String time) {

        Flowable<WendaArticleBean> compose = RxRequestUtils
                .create(ApiService.class)
                .getWendaArticle(time)
                .compose(Transformer.<WendaArticleBean>switchSchedulers());


        return compose;
    }


}
