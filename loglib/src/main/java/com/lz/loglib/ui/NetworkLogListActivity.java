package com.lz.loglib.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lz.loglib.R;


/**
 * Created by linkaipeng on 2018/5/20.
 */

public class NetworkLogListActivity extends Activity {

    private RecyclerView mNetworkFeedRecyclerView;
    private NetworkLogAdapter mNetworkFeedAdapter;


    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkLogListActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_log_list);
        initView();
    }


    private void initView() {
        mNetworkFeedRecyclerView = (RecyclerView) findViewById(R.id.network_feed_recyclerView);
        mNetworkFeedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNetworkFeedAdapter = new NetworkLogAdapter(this);
        mNetworkFeedRecyclerView.setAdapter(mNetworkFeedAdapter);
        TextView viewById = (TextView) findViewById(R.id.network_feed_back_layout);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
