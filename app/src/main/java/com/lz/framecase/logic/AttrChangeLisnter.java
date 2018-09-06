package com.lz.framecase.logic;

import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.TextView;

import com.lz.skinlibs.ChangeListener;
import com.lz.skinlibs.SkinManager;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-06       创建class
 */
public class AttrChangeLisnter implements ChangeListener {
    @Override
    public void change(View view, String resName) {
        try {
            if (view instanceof CollapsingToolbarLayout) {
                int color = SkinManager.getInstance().getResourceManager().getColor(resName);
                ((CollapsingToolbarLayout) view).setContentScrimColor(color);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
