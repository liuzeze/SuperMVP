package com.lz.fram.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lz.fram.R;


/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-28       加载框
 */
public class LpLoadDialog extends Dialog {

    protected Context mContext;

    protected LayoutParams mLayoutParams;

    public LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public LpLoadDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = context;
        Window window = this.getWindow();
        mLayoutParams = window.getAttributes();
        mLayoutParams.alpha = 1f;
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ProgressBar progressBar = new ProgressBar(context);
        LinearLayout rootView = new LinearLayout(context);
        LinearLayout.LayoutParams rootLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        rootLp.gravity = Gravity.CENTER;
        rootView.setLayoutParams(rootLp);
        rootView.addView(progressBar);
        setContentView(rootView);

        getWindow().setType(LayoutParams.TYPE_SYSTEM_ALERT);

    }

    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置全屏显示
     */
    public void setFullScreen() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.FILL_PARENT;
        lp.height = LayoutParams.FILL_PARENT;
        window.setAttributes(lp);
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.FILL_PARENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    /**
     * 设置高度为match_parent
     */
    public void setFullScreenHeight() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = window.getAttributes();
        lp.width = LayoutParams.WRAP_CONTENT;
        lp.height = LayoutParams.FILL_PARENT;
        window.setAttributes(lp);
    }

    public void setOnWhole() {
        getWindow().setType(LayoutParams.TYPE_SYSTEM_ALERT);
    }


    //多次请求弹框问题
    private int showCount = 0;

    @Override
    public synchronized void show() {
        if (!isShowing()) {
            super.show();
        }
        showCount = showCount + 1;
    }

    @Override
    public synchronized void dismiss() {
        showCount = showCount - 1;
        if (showCount <= 0) {
            showCount = 0;
            super.dismiss();

        }
    }
}