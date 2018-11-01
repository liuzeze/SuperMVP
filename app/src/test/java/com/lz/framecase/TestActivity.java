package com.lz.framecase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-30       创建class
 */
public class TestActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout view = new LinearLayout(this);
        view.setId(1);

        setContentView(view);
    }


}
