package com.lz.framecase.base;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.lz.fram.base.RxPresenter;
import com.lz.framecase.logic.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseUiPresenter<T extends ViewDataBinding> extends RxPresenter {


    private View convertView;
    private Unbinder mUnbinder;
    private T mBind;


    public void creatLayout() {
        //初始化convertView
        Context context = (Context) mView;
        try {
            if (getLayout() != -1) {
                LayoutInflater inflater = LayoutInflater.from(context == null ? MyApplication.mApplication : context);
                try {
                    mBind = DataBindingUtil.inflate(inflater,
                            getLayout(), null, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (mBind == null) {
                    convertView = inflater.inflate(getLayout(), null, false);
                } else {
                    convertView = mBind.getRoot();
                }
                mUnbinder = ButterKnife.bind(this, convertView);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public View getHolderView() {
        if (convertView == null) {
            throw new NullPointerException("NullPointerException Please call createLayout first.");
        }
        return convertView;
    }


    protected abstract int getLayout();

    @Override
    public void detachView() {
        super.detachView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
