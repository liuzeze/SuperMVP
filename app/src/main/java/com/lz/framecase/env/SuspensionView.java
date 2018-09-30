package com.lz.framecase.env;


import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lz.framecase.R;
import com.lz.loglib.ui.NetworkLogListActivity;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-06-12       创建class
 */
public class SuspensionView implements View.OnTouchListener {

    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    //定义浮动窗口布局
    LinearLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    WindowManager mWindowManager;
    private Context mContext;
    static SuspensionView mFloatView = new SuspensionView();

    private SuspensionView() {
    }

    public static SuspensionView getInstance() {
        return mFloatView;
    }

    public void init(Context context) {
        mContext = context;
        // onRemoveView();
        createFloatView(context);
    }


    public void onRemoveView() {
        try {
            if (mFloatLayout != null) {
                mWindowManager.removeView(mFloatLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void createFloatView(final Context context) {
        wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        wmParams.format = PixelFormat.TRANSLUCENT;

        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;

        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = mWindowManager.getDefaultDisplay().getWidth();
        wmParams.y = 205;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
        //获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.suspension_view, null);
        View tvLog = mFloatLayout.findViewById(R.id.tv_log);
        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);
        //浮动窗口按钮
        tvLog.setOnTouchListener(this);
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        tvLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkLogListActivity.start(v.getContext());
              /*  Intent intent = new Intent(context, DisplayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);*/
            }
        });


    }

    private float StartX;
    private float StartY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY() - 25;
        Log.i("currP", "currX" + x + "====currY" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();
                StartX = x;
                StartY = y;
                Log.i("startP", "startX" + mTouchStartX + "====startY" + mTouchStartY);

                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - StartX) > 5 && Math.abs(y - StartY) > 5) {
                    updateViewPosition();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (Math.abs(x - StartX) > 5 && Math.abs(y - StartY) > 5) {
                } else {
                    v.performClick();
                }
                // updateViewPosition();
                // 抬起手指时让floatView紧贴屏幕左右边缘
                wmParams.x = wmParams.x <= (mWindowManager.getDefaultDisplay().getWidth() / 2) ? 0 : mWindowManager.getDefaultDisplay().getWidth();
                wmParams.y = (int) (y - mTouchStartY);
                mWindowManager.updateViewLayout(mFloatLayout, wmParams);

                mTouchStartX = mTouchStartY = 0;

                break;
            default:
                break;
        }
        return true;
    }

    private void updateViewPosition() {
        //更新浮动窗口位置参数
        wmParams.x = (int) (x - mTouchStartX);
        wmParams.y = (int) (y - mTouchStartY);
        mWindowManager.updateViewLayout(mFloatLayout, wmParams);
    }
}