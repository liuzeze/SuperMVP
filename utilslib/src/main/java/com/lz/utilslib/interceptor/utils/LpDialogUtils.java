package com.lz.utilslib.interceptor.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * 对话框弹出帮助类，汇集了多种对话框的简单调用
 * Created by 刘泽 on 2018/7/13
 */
public class LpDialogUtils {

    private static LpAlertDialog mSaAlertDialog;

    /**
     * 通用的提示框,带有两个按钮,标题，
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static LpAlertDialog alertDialog(Context context, String title, String positiveBtnStr
            , View.OnClickListener positiveClickListener, String negativeBtnStr, View.OnClickListener negativeClickListener, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮,标题，
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param content               内容
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static LpAlertDialog alertDialog(Context context, String title, String content, String positiveBtnStr
            , View.OnClickListener positiveClickListener, String negativeBtnStr, View.OnClickListener negativeClickListener, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setMsg(content)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮,标题，
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static LpAlertDialog alertDialog(Context context, String title
            , View.OnClickListener positiveClickListener,
                                            View.OnClickListener negativeClickListener, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setPositiveButton("",
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton("", negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }


    /**
     * 通用的提示框,带有两个按钮,标题，
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static LpAlertDialog alertDialog(Context context, String title
            , View.OnClickListener positiveClickListener,
                                            boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setPositiveButton("",
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton("", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮,标题，
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static LpAlertDialog alertDialog(Context context, String title, String positiveBtnStr
            , View.OnClickListener positiveClickListener, String negativeBtnStr, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }


    /**
     * @param context      提示框绑定的上下文
     * @param view         提示框绑定的布局
     * @param isCancelable 是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog(Context context, View view, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setContentView(view)
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }

    /**
     * @param context          提示框绑定的上下文
     * @param view             提示框绑定的布局
     * @param onDismisListener dismiss监听
     * @param isCancelable     是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog(Context context, View view,
                                                DialogInterface.OnDismissListener onDismisListener
            , boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setContentView(view)
                .setCancelable(isCancelable)
                .setOnDismisListener(onDismisListener)
                .show();

        return mSaAlertDialog;
    }

    /**
     * @param context      提示框绑定的上下文
     * @param view         提示框绑定的布局
     * @param isCancelable 是否可取消
     * @return
     */
    public static LpAlertDialog secondAlertViewDialog(Context context, View view, boolean isCancelable) {

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setContentView(view)
                .setCancelable(isCancelable)
                .show();

        return mSaAlertDialog;
    }


    /**
     * @param context               提示框绑定的上下文
     * @param title                 标题
     * @param view                  提示框绑定的布局
     * @param positiveClickListener 确定监听
     * @param negativeClickListener 取消监听
     * @param isCancelable          是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog(Context context, String title, View view, View.OnClickListener positiveClickListener,
                                                View.OnClickListener negativeClickListener, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setContentView(view)
                .setCancelable(isCancelable)
                .setPositiveButton("",
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton("", negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

    /**
     * @param context               提示框绑定的上下文
     * @param title                 标题
     * @param view                  提示框绑定的布局
     * @param positiveClickListener 确定监听
     * @param negativeClickListener 取消监听
     * @param isCancelable          是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog2(Context context, String title, View view, View.OnClickListener positiveClickListener,
                                                 View.OnClickListener negativeClickListener, boolean isCancelable) {

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setContentView(view)
                .setCancelable(isCancelable)
                .setPositiveButton("",
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton("", negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

    /**
     * 点击确定按钮不取消dialog
     *
     * @param context               提示框绑定的上下文
     * @param title                 标题
     * @param view                  提示框绑定的布局
     * @param positiveClickListener 确定监听
     * @param negativeClickListener 取消监听
     * @param isCancelable          是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog3(Context context, String title, View view, View.OnClickListener positiveClickListener,
                                                 View.OnClickListener negativeClickListener, boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }
        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setContentView(view)
                .setCancelable(isCancelable)
                .setConfirmButton("",
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton("", negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }


    /**
     * @param context               提示框绑定的上下文
     * @param title                 标题
     * @param view                  提示框绑定的布局
     * @param pos                   确定文案
     * @param positiveClickListener 确定监听
     * @param neg                   取消文案
     * @param negativeClickListener 取消监听
     * @param isCancelable          是否可取消
     * @return
     */
    public static LpAlertDialog alertViewDialog(Context context, String title, View view, String pos, View.OnClickListener positiveClickListener,
                                                String neg, View.OnClickListener negativeClickListener, boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new LpAlertDialog(context).builder()
                .setTitle(title)
                .setContentView(view)
                .setCancelable(isCancelable)
                .setPositiveButton(pos,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(neg, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();

        return mSaAlertDialog;
    }

}
