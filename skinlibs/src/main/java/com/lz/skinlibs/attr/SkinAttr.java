package com.lz.skinlibs.attr;


import android.view.View;

import com.lz.skinlibs.ChangeListener;

/**
 * Created by zhy on 15/9/22.
 */
public class SkinAttr
{
    public String resName;
    public SkinAttrType attrType;


    public SkinAttr(SkinAttrType attrType, String resName)
    {
        this.resName = resName;
        this.attrType = attrType;
    }

    public void apply(View view, ChangeListener changeListener)
    {
        attrType.apply(view, resName ,changeListener);
    }
}
