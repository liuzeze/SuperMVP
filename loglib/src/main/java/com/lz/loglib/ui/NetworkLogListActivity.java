package com.lz.loglib.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lz.loglib.R;
import com.lz.loglib.data.DataPoolImpl;
import com.lz.loglib.data.NetworkFeedModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.ContentValues.TAG;


/**
 * Created by linkaipeng on 2018/5/20.
 */

public class NetworkLogListActivity extends Activity {

    private RecyclerView mNetworkFeedRecyclerView;
    private NetworkLogAdapter mNetworkFeedAdapter;
    private ArrayList mArrayList;


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
        mArrayList = new ArrayList(DataPoolImpl.getInstance().getNetworkFeedMap().values());

        try {
            Collections.sort(mArrayList, new Comparator<NetworkFeedModel>() {
                @Override
                public int compare(NetworkFeedModel networkFeedModel1, NetworkFeedModel networkFeedModel2) {
                    return (int) (networkFeedModel2.getCreateTime() - networkFeedModel1.getCreateTime());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, TAG, e);
        }
        mNetworkFeedRecyclerView = (RecyclerView) findViewById(R.id.network_feed_recyclerView);
        mNetworkFeedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNetworkFeedAdapter = new NetworkLogAdapter(this, mArrayList);
        mNetworkFeedRecyclerView.setAdapter(mNetworkFeedAdapter);
        TextView viewById = (TextView) findViewById(R.id.network_feed_back_layout);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView tvClear = findViewById(R.id.tv_clear);
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                DataPoolImpl.getInstance().getNetworkFeedMap().clear();
                mNetworkFeedAdapter.setData(mArrayList);
            }
        });
        final EditText editText = (EditText) findViewById(R.id.et_title);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<NetworkFeedModel> arrayList = new ArrayList(DataPoolImpl.getInstance().getNetworkFeedMap().values());
                mArrayList.clear();
                String trim = editText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    mArrayList.clear();
                    mArrayList.addAll(arrayList);
                } else {
                    for (NetworkFeedModel model : arrayList) {
                        if (model.getUrl().contains(trim)) {
                            mArrayList.add(model);
                        }
                    }
                }
                mNetworkFeedAdapter.setData(mArrayList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextView etClear = (TextView) findViewById(R.id.et_clear);
        etClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }


}
