package com.lz.framecase;


import android.widget.Toast;

import com.lz.framecase.base.BaseActivity;
import com.lz.framecase.presenter.Main2Contract;
import com.lz.framecase.presenter.Main2Presenter;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */

public class Main2Activity extends BaseActivity<Main2Presenter> implements Main2Contract.View {


    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void showLoginInfor() {
        //案例回调
    }
}
