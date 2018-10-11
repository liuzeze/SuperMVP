/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lz.framecase.logic;


import android.content.Context;

import com.google.gson.GsonBuilder;
import com.lz.fram.model.AppModule;
import com.lz.fram.model.ClientModule;
import com.lz.fram.model.ConfigModule;
import com.lz.fram.model.GlobalConfigBuild;
import com.lz.framecase.BuildConfig;
import com.lz.loglib.reporter.OkNetworkMonitorInterceptor;
import com.lz.utilslib.interceptor.intercept.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 请求框架参数赋值
 */
public class GlobalConfiguration implements ConfigModule {


    @Override
    public void applyOptions(GlobalConfigBuild.Builder builder) {


        if (BuildConfig.DEBUG) {
            //Release 时,让框架不再打印 Http 请求和响应的信息
            builder.addInterceptor(new LoggerInterceptor());
            builder.addNetworkInterceptor(new OkNetworkMonitorInterceptor());
        }

        builder
                //.addInterceptor(new ReceivedCookiesInterceptor(LpUrl.BASE_URl + LpUrl.LOGIN_URl))
//                .addInterceptor(new HostInterceptor())
               // .addInterceptor(new AddCookiesInterceptor())
                .baseurl(LpUrl.BASE_URl)
                .gsonConfiguration(new AppModule.GsonConfiguration() {
                    @Override
                    public void configGson(Context context, GsonBuilder builder) {
                        //Gson 信息配置
                        builder.serializeNulls()//支持序列化null的参数
                                .enableComplexMapKeySerialization();//支持将序列化key为object的map,默认只能序列化key为string的map

                    }
                })
                .retrofitConfiguration(new ClientModule.RetrofitConfiguration() {
                    @Override
                    public void configRetrofit(Retrofit.Builder builder) {
                        //retrofit  信息配置
                        //builder.addConverterFactory(ScalarsConverterFactory.create());
                        // .addConverterFactory(GsonConverterFactory.create())
                    }
                })
                .okhttpConfiguration(new ClientModule.OkhttpConfiguration() {
                    @Override
                    public void configOkhttp(OkHttpClient.Builder builder) {
                        //这里可以自己自定义配置Okhttp的参数
                        builder.writeTimeout(10, TimeUnit.SECONDS);
                        builder.readTimeout(10, TimeUnit.SECONDS);
                        builder.connectTimeout(10, TimeUnit.SECONDS);
                    }
                });


    }

}
