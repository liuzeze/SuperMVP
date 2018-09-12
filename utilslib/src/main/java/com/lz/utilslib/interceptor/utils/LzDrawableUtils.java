package com.lz.utilslib.interceptor.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2016/11/10	10:19	    刘泽			    新增 类
 * 2016/11/10	10:19	    刘泽			    手动创建背景图片
 */
public class LzDrawableUtils {
    /**
     * 动态创建圆角的Drawable，和在xml中使用<shape>一样的
     *
     * @return
     */
    public static Drawable shapeDrawable(int corlor, float[] radius) {
        if (radius == null) {
            radius = new float[]{0, 0, 0, 0};
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);//设置形状为矩形，默认就是矩形
        drawable.setCornerRadii(radius); //设置矩形圆角的半径
        drawable.setColor(corlor);//设置填充色
        return drawable;
    }

    /**
     * 动态创建圆角的Drawable，和在xml中使用<shape>一样的
     *
     * @return
     */
    public static Drawable shapeDrawable(int corlor, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);//设置形状为矩形，默认就是矩形
        drawable.setCornerRadius(radius); //设置矩形圆角的半径
        drawable.setColor(corlor);//设置填充色
        return drawable;
    }

    /**
     * 动态selector
     *
     * @param pressed 图片
     * @param normal
     * @return
     */
    public static Drawable shapeSelectorD(Drawable pressed, Drawable normal) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);//设置按下的图片
        drawable.addState(new int[]{}, normal);//设置默认的图片

        drawable.setEnterFadeDuration(500);
        drawable.setExitFadeDuration(500);


        return drawable;
    }

    /**
     * 动态的创建Selector
     *
     * @param pressed
     * @param normal
     * @return
     */
    public static Drawable shapeSelectorC(int pressed, int normal, int radius) {
        StateListDrawable drawable = new StateListDrawable();
        //设置按下的图片
        drawable.addState(new int[]{
                android.R.attr.state_pressed
        }, shapeDrawable(pressed, radius));
        drawable.addState(new int[]{
                android.R.attr.state_checked
        }, shapeDrawable(pressed, radius));
        drawable.addState(new int[]{
                android.R.attr.state_selected
        }, shapeDrawable(pressed, radius));
        drawable.addState(new int[]{}, shapeDrawable(normal, radius));

        drawable.setEnterFadeDuration(200);
        drawable.setExitFadeDuration(200);
        return drawable;
    }
}
