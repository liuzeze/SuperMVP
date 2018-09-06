package com.lz.skinlibs.attr;


import android.view.View;

import com.lz.skinlibs.ChangeListener;

import java.util.List;

/**
 * Created by zhy on 15/9/22.
 */
public class SkinView
{
//    SoftReference<View> viewRef;
    public View view ;
    public List<SkinAttr> attrs;

    public SkinView(View view, List<SkinAttr> skinAttrs)
    {
        this.view = view;
        this.attrs = skinAttrs;
    }

    public void apply( ChangeListener changeListener)
    {
       // View view = viewRef.get();
        if (view == null) return;

        for (SkinAttr attr : attrs)
        {
            attr.apply(view,changeListener);
        }
    }
}
