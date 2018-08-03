package com.lz.fram.download;


import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			    文件下载
 */

public abstract class DownloadObserver extends ResourceSubscriber<ResponseBody> {

    private String fileName;

    protected DownloadObserver( String fileName) {
        this.fileName = fileName;
    }


    @Override
    public void onComplete() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void onNext(ResponseBody responseBody) {
        new DownloadManager().saveFile(responseBody, fileName, new ProgressListener() {
            @Override
            public void onResponseProgress(final long bytesRead, final long contentLength, final int progress, final boolean done, final String filePath) {
                Observable
                        .just(progress)
                        .distinctUntilChanged()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(@NonNull Integer integer) {
                                onSuccess(bytesRead, contentLength, progress, done, filePath);
                            }
                        });
            }

        });
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    /**
     * 成功回调
     *
     * @param filePath filePath
     */
    /**
     * 成功回调
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param progress      当前进度
     * @param done          是否下载完成
     * @param filePath      文件路径
     */
    public abstract void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath);


}
