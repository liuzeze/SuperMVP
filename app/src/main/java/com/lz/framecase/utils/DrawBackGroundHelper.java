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
            "bind_shape_color",
            "bind_shape_radius",
    }, requireAll = false)
    public static void setViewShapeBackground(View view, int color, int radius) {
        try {
            Drawable drawable = LzDrawableUtils.shapeDrawable(color, radius);
            view.setBackground(drawable);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter(value = {
            "bind_def_color",
            "bind_sel_color",
            "bind_sel_radius"
    }, requireAll = false)
    public static void setViewSelecterBackground(View view, int defColor, int selectColor, int radius) {
        try {
            Drawable drawable = LzDrawableUtils.shapeSelectorC(selectColor, defColor, radius);
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
