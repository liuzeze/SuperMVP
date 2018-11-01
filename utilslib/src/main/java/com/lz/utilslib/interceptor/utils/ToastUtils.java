package com.lz.utilslib.interceptor.utils;

import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.view.RxToast;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-30       创建class
 */
public class ToastUtils {

    public static void show(String str) {
        RxToast.showToast(str);
    }

    public static void error(String str) {
        RxToast.error(str);
    }

    public static void success(String str) {
        RxToast.success(str);
    }

    public static void info(String str) {
        RxToast.info(str);
    }


}
