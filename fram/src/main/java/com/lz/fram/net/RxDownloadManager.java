package com.lz.fram.net;

import android.os.Environment;
import android.text.TextUtils;

import com.lz.fram.net.download.DownParamBean;
import com.lz.fram.net.download.DownloadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;


public class RxDownloadManager {

    private Map<String, DownloadTask> mDownloadTasks;
    private static RxDownloadManager mInstance;


    public static RxDownloadManager getInstance() {
        if (mInstance == null) {
            synchronized (RxDownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new RxDownloadManager();
                }
            }
        }
        return mInstance;
    }

    public RxDownloadManager() {
        mDownloadTasks = new HashMap<>();
    }


    public void downLoad(final String url, String fileName, String filePath, final Consumer<DownParamBean> consumer) {
        if (!mDownloadTasks.containsKey(url)) {
            if (TextUtils.isEmpty(filePath)) {
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "download" + File.separator;
            }
            if (TextUtils.isEmpty(fileName)) {
                fileName = url.substring(url.lastIndexOf("/") + 1);
            }

            DownloadTask downloadTask = new DownloadTask(
                    new DownParamBean(url, filePath, fileName),
                    consumer);
            mDownloadTasks.put(url, downloadTask);
        }
        mDownloadTasks.get(url).start();
    }


    public void pause(String url) {

        if (mDownloadTasks.containsKey(url)) {
            mDownloadTasks.get(url).pause();
        }

    }


    public void cancel(String url) {

        if (mDownloadTasks.containsKey(url)) {
            mDownloadTasks.get(url).cancel();
        }

    }


    public boolean isDownloading(String url) {
        boolean result = false;
        if (mDownloadTasks.containsKey(url)) {
            result = mDownloadTasks.get(url).isDownloading();
        }
        return result;
    }


}
