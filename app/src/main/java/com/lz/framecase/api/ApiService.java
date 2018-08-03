package com.lz.framecase.api;


import io.reactivex.Flowable;
import retrofit2.http.GET;

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


}
