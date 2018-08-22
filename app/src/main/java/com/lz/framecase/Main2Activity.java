package com.lz.framecase;


import com.lz.framecase.base.BaseActivity;
import com.lz.framecase.env.SuspensionView;
import com.lz.framecase.presenter.Main2Contract;
import com.lz.framecase.presenter.Main2Presenter;
import com.lz.inject_annotation.InjectComponet;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
@InjectComponet
public class Main2Activity extends BaseActivity<Main2Presenter> implements Main2Contract.View {


    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        SuspensionView.getInstance().init(this);
        mPresenter.getNewLists();
    }


}
