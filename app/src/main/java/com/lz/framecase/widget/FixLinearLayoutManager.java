package com.lz.framecase.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 *
 * 官方的BUG
 * 解决 IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter
 */

public class FixLinearLayoutManager extends LinearLayoutManager {
    public FixLinearLayoutManager(Context context) {
        super(context);
    }

    public FixLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public FixLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
