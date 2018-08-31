package com.lz.utilslib.interceptor.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-08-31       创建class
 */
public class SnackbarUtils {
    public static void show(View view, String mes) {
        Snackbar.make(view, mes, Snackbar.LENGTH_SHORT).show();

    }
}
