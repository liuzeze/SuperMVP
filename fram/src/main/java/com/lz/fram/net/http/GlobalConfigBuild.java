
package com.lz.fram.net.http;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			  请求框架参数初始化
 */
public class GlobalConfigBuild {
    private HttpUrl mApiUrl ;
    private List<Interceptor> mInterceptors;
    private List<Interceptor> netInterceptors;
    private RetrofitFactory.RetrofitConfiguration mRetrofitConfiguration;
    private OkhttpFactory.OkhttpConfiguration mOkhttpConfiguration;

    private GlobalConfigBuild(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mInterceptors = builder.interceptors;
        this.netInterceptors = builder.netInterceptors;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
    }

    public static Builder builder() {
        return new Builder();
    }


    List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    List<Interceptor> getNetInterceptors() {
        return netInterceptors;
    }

    RetrofitFactory.RetrofitConfiguration getRetrofitConfiguration() {
        return mRetrofitConfiguration;
    }


    OkhttpFactory.OkhttpConfiguration getOkhttpConfiguration() {
        return mOkhttpConfiguration;
    }


    /**
     * 提供 BaseUrl,默认使用 <"https://api.github.com/">
     *
     * @return
     */
    HttpUrl getBaseUrl() {
        return mApiUrl;
    }

    public static final class Builder {
        private HttpUrl apiUrl= HttpUrl.parse("https://api.github.com/");
        private List<Interceptor> interceptors;
        private List<Interceptor> netInterceptors;
        private RetrofitFactory.RetrofitConfiguration retrofitConfiguration;
        private OkhttpFactory.OkhttpConfiguration okhttpConfiguration;

        private Builder() {
        }

        public Builder baseurl(String baseUrl) {
            //基础url
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }


        public Builder addInterceptor(Interceptor interceptor) {
            //动态添加任意个interceptor
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            if (netInterceptors == null) {
                netInterceptors = new ArrayList<>();
            }
            this.netInterceptors.add(interceptor);
            return this;
        }


        public Builder retrofitConfiguration(RetrofitFactory.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okhttpConfiguration(OkhttpFactory.OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

        public GlobalConfigBuild build() {
            return new GlobalConfigBuild(this);
        }


    }


}
