package com.lz.fram.net.download;

public class DownParamBean {
    public static final int STATU_PROGRESS = 1;//进度
    public static final int STATU_FINISH = 2;//完成下载
    public static final int STATU_PAUSE = 3;//暂停
    public static final int STATU_CANCEL = 4;//暂停

    //文件名
    public String fileName;
    //下载地址
    public String url;
    //下载目录
    public String filePath;

    public long bytesRead;
    public long contentLength;
    public int progress;
    public int status;
    public boolean isDownloading = false;


    public DownParamBean(String url, String filePath, String fileName) {
        this.url = url;
        this.filePath = filePath;
        this.fileName = fileName;
    }


    public boolean isFinish() {
        return status == STATU_FINISH;
    }

    public boolean isCancle() {
        return status == STATU_CANCEL;
    }

    public boolean isPause() {
        return status == STATU_PAUSE;
    }

    public boolean isProcess() {
        return status == STATU_PROGRESS;
    }
}
