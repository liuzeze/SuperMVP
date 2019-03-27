package com.lz.fram.net.download;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.lz.fram.net.download.DownParamBean.STATU_CANCEL;
import static com.lz.fram.net.download.DownParamBean.STATU_FINISH;
import static com.lz.fram.net.download.DownParamBean.STATU_PAUSE;
import static com.lz.fram.net.download.DownParamBean.STATU_PROGRESS;


public class DownloadTask {

    private final int THREAD_COUNT = 3;
    private DownParamBean mParamBean;
    private long mFileLength;


    private int childCanleCount;
    private int childPauseCount;
    private int childFinshCount;
    private long[] mProgress;
    private File[] mCacheFiles;
    private File mTmpFile;
    private boolean pause;
    private boolean cancel;


    private Consumer<DownParamBean> mConsumer;


    public DownloadTask(DownParamBean point, Consumer<DownParamBean> consumer) {
        this.mParamBean = point;
        this.mConsumer = consumer;
        this.mProgress = new long[THREAD_COUNT];
        this.mCacheFiles = new File[THREAD_COUNT];
    }


    private static final String TAG = "DownloadTask";

    @SuppressLint("CheckResult")
    public synchronized void start() {
        Log.e(TAG, "start: " + mParamBean.isDownloading + "\t" + mParamBean.url);
        if (mParamBean.isDownloading) {
            return;
        }
        mParamBean.isDownloading = true;
        DownLoadFactory
                .getInstance()
                .getRetrofitClient()
                .downloadFile(mParamBean.url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        mFileLength = responseBody.contentLength();
                        close(responseBody);
                        mTmpFile = new File(mParamBean.filePath, mParamBean.fileName + ".tmp");
                        if (!mTmpFile.getParentFile().exists()) {
                            mTmpFile.getParentFile().mkdirs();
                        }
                        RandomAccessFile tmpAccessFile = new RandomAccessFile(mTmpFile, "rw");
                        tmpAccessFile.setLength(mFileLength);
                        long blockSize = mFileLength / THREAD_COUNT;
                        for (int threadId = 0; threadId < THREAD_COUNT; threadId++) {
                            long startIndex = threadId * blockSize;
                            long endIndex = (threadId + 1) * blockSize - 1;
                            if (threadId == (THREAD_COUNT - 1)) {
                                endIndex = mFileLength - 1;
                            }
                            download(startIndex, endIndex, threadId);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        resetStutus();
                    }
                });


    }

    @SuppressLint("CheckResult")
    public void download(final long startIndex, final long endIndex, final int threadId) throws IOException {
        long newStartIndex = startIndex;
        final File cacheFile = new File(mParamBean.filePath, "thread" + threadId + "_" + mParamBean.fileName + ".txt");
        mCacheFiles[threadId] = cacheFile;
        final RandomAccessFile cacheAccessFile = new RandomAccessFile(cacheFile, "rwd");
        if (cacheFile.exists()) {
            String startIndexStr = cacheAccessFile.readLine();
            try {
                newStartIndex = Integer.parseInt(startIndexStr);
            } catch (NumberFormatException e) {
            }
        }
        final long finalStartIndex = newStartIndex;
        String rang = "bytes=" + finalStartIndex + "-" + endIndex;
        DownLoadFactory
                .getInstance()
                .getRetrofitClient()
                .downloadFile(rang, mParamBean.url)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        InputStream is = responseBody.byteStream();
                        RandomAccessFile tmpAccessFile = new RandomAccessFile(mTmpFile, "rw");
                        tmpAccessFile.seek(finalStartIndex);
                        byte[] buffer = new byte[1024 << 2];
                        int length = -1;
                        int total = 0;
                        long progress = 0;
                        pause = false;
                        while ((length = is.read(buffer)) > 0) {
                            if (cancel) {
                                close(cacheAccessFile, is, responseBody);
                                cleanFile(cacheFile);
                                downLoadState(STATU_CANCEL);
                                return;
                            }
                            if (pause) {
                                close(cacheAccessFile, is, responseBody);
                                downLoadState(STATU_PAUSE);
                                return;
                            }
                            tmpAccessFile.write(buffer, 0, length);
                            total += length;
                            progress = finalStartIndex + total;

                            cacheAccessFile.seek(0);
                            cacheAccessFile.write((progress + "").getBytes("UTF-8"));
                            mProgress[threadId] = progress - startIndex;
                            downLoadState(STATU_PROGRESS);
                        }
                        close(cacheAccessFile, is, responseBody);
                        cleanFile(cacheFile);
                        downLoadState(STATU_FINISH);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        resetStutus();
                    }
                });
    }


    public void downLoadState(int statu) {
        if (null == mConsumer) {
            return;
        }
        mParamBean.status = statu;
        switch (statu) {
            case STATU_PROGRESS:
                long progress = 0;
                for (int i = 0, length = mProgress.length; i < length; i++) {
                    progress += mProgress[i];
                }
                mParamBean.bytesRead = progress;
                mParamBean.contentLength = mFileLength;
                mParamBean.progress = (int) (progress * 100.0f / mFileLength);
                break;
            case STATU_PAUSE:
                childPauseCount++;
                if (childPauseCount % THREAD_COUNT != 0) return;
                resetStutus();
                break;
            case STATU_FINISH:
                childFinshCount++;
                if (childFinshCount % THREAD_COUNT != 0) return;
                mTmpFile.renameTo(new File(mParamBean.filePath, mParamBean.fileName));
                resetStutus();
                System.out.println("完成"+mParamBean.isDownloading);
                mParamBean.bytesRead = mFileLength;
                mParamBean.contentLength = mFileLength;
                mParamBean.progress = 100;
                mProgress = new long[THREAD_COUNT];
                break;
            case STATU_CANCEL:
                childCanleCount++;
                if (childCanleCount % THREAD_COUNT != 0) return;
                resetStutus();
                mProgress = new long[THREAD_COUNT];
                break;
            default:
                break;
        }
        callBackListener();
    }

    @SuppressLint("CheckResult")
    private void callBackListener() {
        Observable.just(mParamBean)
                .distinctUntilChanged(new Function<DownParamBean, Integer>() {
                    @Override
                    public Integer apply(DownParamBean downParamBean) throws Exception {
                        return downParamBean.progress;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mConsumer, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }


    private void close(Closeable... closeables) {
        int length = closeables.length;
        try {
            for (int i = 0; i < length; i++) {
                Closeable closeable = closeables[i];
                if (null != closeable)
                    closeables[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (int i = 0; i < length; i++) {
                closeables[i] = null;
            }
        }
    }

    private void cleanFile(File... files) {
        for (int i = 0, length = files.length; i < length; i++) {
            if (null != files[i])
                files[i].delete();
        }
    }


    public synchronized void pause() {
        if (!mParamBean.isPause()) {
            pause = true;
        }
    }


    public synchronized void cancel() {
        if (!mParamBean.isCancle()) {
            cancel = true;
            cleanFile(mTmpFile);
            if (!mParamBean.isDownloading) {
                if (null != mConsumer) {
                    cleanFile(mCacheFiles);
                    resetStutus();
                    mParamBean.status = STATU_CANCEL;
                    callBackListener();
                }
            }
        }
    }


    private void resetStutus() {
        pause = false;
        cancel = false;
        mParamBean.isDownloading = false;
    }

    public boolean isDownloading() {
        return mParamBean.isDownloading;
    }
}
