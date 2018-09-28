package com.lz.skinlibs;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.lz.skinlibs.utils.L;


/**
 * Created by zhy on 15/9/22.
 */
public class ResourceManager {
    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_MIPMAP = "mipmap";
    private static final String DEFTYPE_COLOR = "color";
    private static final String DIMENS_SIZER = "dimen";
    private Resources mResources;
    private String mPluginPackageName;
    private String mSuffix;


    public ResourceManager(Resources res, String pluginPackageName, String suffix) {
        mResources = res;
        mPluginPackageName = pluginPackageName;

        if (suffix == null) {
            suffix = "";
        }
        mSuffix = suffix;

    }

    public Drawable getDrawableByName(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name + " , " + mPluginPackageName);
            return mResources.getDrawable(mResources.getIdentifier(name, DEFTYPE_MIPMAP, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            try {
                name = appendSuffix(name);
                return mResources.getDrawable(mResources.getIdentifier(name, DEFTYPE_DRAWABLE, mPluginPackageName));
            } catch (Resources.NotFoundException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }

    public int getColor(String name) throws Resources.NotFoundException {
        name = appendSuffix(name);
        L.e("name = " + name);
        return mResources.getColor(mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));
    }

    public int getColorRes(String name) throws Resources.NotFoundException {
        name = appendSuffix(name);
        L.e("name = " + name);
        return mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName);
    }

    public int getColorResDefault(String name) throws Resources.NotFoundException {
        L.e("name = " + name);
        return mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName);
    }

    public int getDimens(String name) throws Resources.NotFoundException {
        name = appendSuffix(name);
        L.e("name = " + name);
        return (int) mResources.getDimension(mResources.getIdentifier(name, DIMENS_SIZER, mPluginPackageName));
    }

    public ColorStateList getColorStateList(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getColorStateList(mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String appendSuffix(String name) {
        if (!TextUtils.isEmpty(mSuffix))
            return name += "_" + mSuffix;
        return name;
    }

}
