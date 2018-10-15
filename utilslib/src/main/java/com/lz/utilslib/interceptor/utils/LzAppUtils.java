package com.lz.utilslib.interceptor.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.vondear.rxtool.RxAppTool;
import com.vondear.rxtool.RxTool;

import java.util.List;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-15       创建class
 */
public class LzAppUtils {
    public static boolean isCurrentProcess() {
        ActivityManager am = (ActivityManager) RxTool.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName.equals(RxAppTool.getAppPackageName(RxTool.getContext()));
                }
            }
        }
        return false;
    }
}
