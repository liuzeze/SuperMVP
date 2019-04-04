package com.lz.framecase.base;


import android.content.Context;
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

public abstract class BaseUiPresenter extends RxPresenter {


    private View convertView;
    private Unbinder mUnbinder;


    public void creatLayout() {
        //初始化convertView
        Context context = (Context) mBaseView;
        try {
            if (getLayout() != -1) {
                LayoutInflater inflater = LayoutInflater.from(context == null ? MyApplication.mApplication : context);

                convertView = inflater.inflate(getLayout(), null, false);

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
