package com.lz.fram.app;

import android.app.Application;

import com.lz.fram.net.RxRequestUtils;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-28       基类application
 */
public class FrameApplication extends Application  {

    /**
     * 获取应用程序组件
     */
    public static FrameApplication mApplication;




    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        RxRequestUtils.getInstance().init(this);


    }
}
