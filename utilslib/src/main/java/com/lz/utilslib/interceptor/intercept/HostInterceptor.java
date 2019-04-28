package com.lz.utilslib.interceptor.intercept;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HostInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
       /* if (FrameApplication.mApplication.isHostError()) {
            String url = request.url().toString().replace(LpUrl.BASE_URl, LpUrl.BASE_URL_IP);
            request = request.newBuilder().url(url).build();
        }*/

        return chain.proceed(request);
    }
}
