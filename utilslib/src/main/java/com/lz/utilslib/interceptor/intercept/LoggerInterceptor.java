package com.lz.utilslib.interceptor.intercept;

import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxLogTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggerInterceptor implements Interceptor {
    private static final String TAG = "Http";
    private StringBuilder sb;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        sb = new StringBuilder();
        long t1 = System.nanoTime();
        sb.append(request.method()).append(";").append(request.url()).append(";");
        Logger.e(TAG, sb.toString());

        Response response = chain.proceed(request);
        sb = new StringBuilder();
        long t2 = System.nanoTime();
        String[] split = request.url().url().toString().split("/");
        String route = split[split.length - 1].split("\\?")[0];
        String content = response.body().string();
        sb.append(response.code()).append("; ")
                .append((int) ((t2 - t1) / 1e6)).append("ms; /")
                .append(split[split.length - 2]).append("/")
                .append(route).append(";\n")
                .append(content);
        Logger.e(TAG, sb.toString());
        MediaType mediaType = response.body().contentType();
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
