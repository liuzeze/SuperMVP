package com.lz.fram.net.download;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface DownloadApi {

    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Header("Range") String index, @Url String fileUrl);
}
