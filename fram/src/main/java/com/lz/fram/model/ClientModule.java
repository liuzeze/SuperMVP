
package com.lz.fram.model;



import com.lz.fram.convert.FastJsonConverterFactory;
import com.lz.fram.gson.GsonAdapter;
import com.lz.fram.scope.InterceptorsScope;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.Nullable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			   网络相关对象获取  提供okhttp  和 retrofit相关实例
 *
 */
@Module
public abstract class ClientModule {
    private static final int TIME_OUT = 15;

    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * 提供 {@link Retrofit}
     *
     * @param
     * @return {@link Retrofit}
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofit(Retrofit.Builder builder, @Nullable RetrofitConfiguration configuration, OkHttpClient client, HttpUrl url) {

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   .addConverterFactory(ScalarsConverterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()))
                .baseUrl(url)
                .client(client);
        if (configuration != null) {
            configuration.configRetrofit(builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }


    /**
     * 提供 {@link OkHttpClient}
     *
     * @return {@link OkHttpClient}
     */
    @Singleton
    @Provides
    static OkHttpClient provideClient(OkHttpClient.Builder builder, @Nullable OkhttpConfiguration configuration,
                                      @Nullable @InterceptorsScope("Interceptors") List<Interceptor> interceptors,
                                      @Nullable @InterceptorsScope("netInterceptors") List<Interceptor> netInterceptors) {

        //如果外部提供了interceptor的集合则遍历添加
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (netInterceptors != null) {
            for (Interceptor interceptor : netInterceptors) {
                builder.addNetworkInterceptor(interceptor);
            }
        }
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        if (configuration != null) {
            configuration.configOkhttp(builder);
        }
        return builder.build();
    }

    public interface RetrofitConfiguration {
        void configRetrofit(Retrofit.Builder builder);
    }

    public interface OkhttpConfiguration {
        void configOkhttp(OkHttpClient.Builder builder);
    }

}
