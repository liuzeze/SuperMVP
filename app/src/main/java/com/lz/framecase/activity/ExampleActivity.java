package com.lz.framecase.activity;

import android.graphics.Bitmap;

import com.lz.fram.scope.AttachView;
import com.lz.framecase.R;
import com.lz.framecase.activity.presenter.FaceContract;
import com.lz.framecase.activity.presenter.FacePresenter;
import com.lz.framecase.base.NewsBaseActivity;
import com.lz.framecase.bean.FaceListEntity;
import com.lz.utilslib.interceptor.utils.ToastUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-11       创建class
 */
public class ExampleActivity extends NewsBaseActivity implements FaceContract.View {
    @AttachView
    FacePresenter mFacePresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViewData() {
        mFacePresenter.auth(mActivity);
    }

    @Override
    protected void initLisenter() {
        super.initLisenter();
    }

    @Override
    public void getNewPictureSuccess(@Nullable Bitmap s, @Nullable List<FaceListEntity> faces) {

    }

    @Override
    public void authSuccess() {
        ToastUtils.error("dgjakdghasdj");

    }

    @Override
    public void authFailed() {

    }
}
