package com.lz.framecase.activity;


import android.view.View;

import com.lz.framecase.R;
import com.lz.framecase.base.BaseActivity;
import com.lz.framecase.base.BaseUtils;
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
        new inner().initdata();
    }

    @InjectComponet
    public  class inner extends BaseUtils<Main2Presenter> {
        @Override
        protected View initView() {


            return super.initView();
        }

        public void initdata() {
           // mPresenter.getNewLists();
            new inner2().initdata();
        }

        @InjectComponet
        public  class inner2 extends BaseUtils<Main2Presenter> {
            @Override
            protected View initView() {
                return super.initView();
            }

            public void initdata() {
                mPresenter.getNewLists();

            }
        }
    }
}
