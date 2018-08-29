package com.lz.framecase.api;


import com.lz.framecase.bean.MultNewsBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API 管理器服务
 */
public interface ApiService {
    /**
     * 知乎列表
     */
//    @Headers({DOMAIN_NAME_HEADER + DOUBAN_DOMAIN_NAME})
    @GET("news/latest")
    Flowable<String> getNewLists();

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Flowable<MultNewsBean> getNewsArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Flowable<MultNewsBean> getNewsArticle2(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);
}
