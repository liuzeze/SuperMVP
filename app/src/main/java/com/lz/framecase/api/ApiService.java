package com.lz.framecase.api;


import com.lz.framecase.logic.LpUrl;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * API 管理器服务
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(LpUrl.LOGIN_URl)
    Flowable<String> login(@Field("loginName") String loginName);

}
