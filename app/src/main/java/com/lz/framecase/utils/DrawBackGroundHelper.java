package com.lz.framecase.utils;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.lz.framecase.R;
import com.lz.utilslib.interceptor.utils.LzDrawableUtils;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-11       创建class
 */
public class DrawBackGroundHelper {
    /*@BindingMethods({
       @BindingMethod(type = "android.widget.ImageView",
                      attribute = "android:tint",
                      method = "setImageTintList"),
})*/


    @BindingAdapter(value = {
            "shape_strokr_width",
            "shape_solid_color",
            "shape_strokr_color",
            "shape_radius",
            "shape_is_circle"
    }, requireAll = false)
    public static void setViewShapeBackground(View view, int width, int solidCorlor, int strokeCorlor, float radius, boolean isCircle) {
        try {
            Drawable drawable = LzDrawableUtils.shapeStrokeDrawable(width, solidCorlor, strokeCorlor, radius,isCircle);
            view.setBackground(drawable);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter(value = {
            "select_strokr_width",
            "select_def_color",
            "select_def_stroke_color",
            "select_sel_color",
            "select_sel_stroke_color",
            "select_radius",
            "select_is_circle"
    }, requireAll = false)
    public static void setViewSelecterBackground(View view, int width, int normal, int normalStroke, int pressed, int pressedStroke, int radius,boolean isCircle) {
        try {
            Drawable drawable = LzDrawableUtils.shapeSelectorStroke(width, pressed, normal, pressedStroke, normalStroke, radius,isCircle);
            view.setBackground(drawable);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }


    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }
}
