package com.lz.utilslib.interceptor;

import android.annotation.SuppressLint;


import com.lz.fram.base.SpKey;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-05-16       创建class
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    //需要存储cookies的地址
    private final String mBaseUrl;

    public ReceivedCookiesInterceptor(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @SuppressLint("CheckResult")
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable
                    .fromIterable(originalResponse.headers("Set-Cookie"))
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            cookieBuffer.append(s).append(";");
                        }
                    });
            if (chain.request().url().toString().equals(mBaseUrl)) {
                RxSPTool.putString(RxTool.getContext(),SpKey.NET_COOKIES, cookieBuffer.toString());
            }
        }

        return originalResponse;
    }
}
