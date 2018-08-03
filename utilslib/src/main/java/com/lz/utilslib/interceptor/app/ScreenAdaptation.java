package com.lz.utilslib.interceptor.app;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * 屏幕适配
 */

public class ScreenAdaptation {


    private Context mContext;
    private float mWidth = 1366;

    public ScreenAdaptation(Context context, float width) {
        mContext = context;
        mWidth = width;
    }

    /**
     * 注册
     */
    public void register() {
        resetDensity(mContext, mWidth);
    }

    /**
     * 注销
     */
    public void unregister() {
        //设置为默认
        mContext.getResources().getDisplayMetrics().setToDefaults();
    }

    /**
     * dp适配 getResources().getDisplayMetrics().density
     * sp适配 getResources().getDisplayMetrics().scaledDensity
     * pt适配 getResources().getDisplayMetrics().xdpi
     *
     * @param context
     * @param width   ui设计图的宽度
     */
    private static void resetDensity(Context context, float width) {
        Point point = new Point();
        //获取屏幕的数值
        ((WindowManager) context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        //dp适配 getResources().getDisplayMetrics().density
        context.getResources().getDisplayMetrics().density = point.x / width;
        // context.getResources().getDisplayMetrics().density = point.y/height*2f;
        //sp适配 getResources().getDisplayMetrics().scaledDensity
        context.getResources().getDisplayMetrics().scaledDensity = point.x / width;
        //  context.getResources().getDisplayMetrics().scaledDensity = point.y/height*2f;
    }



/*

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    */
/**
     * 今日头条适配方案  (不可以直接用需要修改)
     * @param activity
     * @param application
     *//*

    private static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics=application.getResources().getDisplayMetrics();
        if(sNoncompatDensity==0){
            sNoncompatDensity=appDisplayMetrics.density;
            sNoncompatScaledDensity=appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig!=null&&newConfig.fontScale>0){
                        sNoncompatScaledDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final  float targetDensity=appDisplayMetrics.widthPixels/360;
        final  float targetScaledDensity=targetDensity*(sNoncompatScaledDensity/sNoncompatDensity);
        final int targetDensityDpi=(int)(160*targetDensity);
        appDisplayMetrics.density=targetDensity;
        appDisplayMetrics.scaledDensity=targetScaledDensity;
        appDisplayMetrics.densityDpi=targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics=activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density=targetDensity;
        activityDisplayMetrics.scaledDensity=targetScaledDensity;
        activityDisplayMetrics.densityDpi=targetDensityDpi;

    }
*/

}
