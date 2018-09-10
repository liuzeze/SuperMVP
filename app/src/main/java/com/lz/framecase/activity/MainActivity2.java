package com.lz.framecase.activity;

import android.view.View;
import android.widget.Button;

import com.lz.fram.scope.AttachView;
import com.lz.framecase.R;
import com.lz.framecase.activity.presenter.Main2Presenter;
import com.lz.framecase.base.BaseActivity;
import com.lz.inject_annotation.InjectActivity;

import javax.inject.Inject;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-10       创建class
 */
@InjectActivity
public class MainActivity2 extends BaseActivity {

    @Inject
    @AttachView
    Main2Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init() {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getNewLists();
            }
        });
    }
}
