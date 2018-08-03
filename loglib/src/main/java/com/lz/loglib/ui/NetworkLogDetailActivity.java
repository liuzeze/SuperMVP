package com.lz.loglib.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lz.loglib.R;
import com.lz.loglib.data.DataPoolImpl;
import com.lz.loglib.data.NetworkFeedModel;

import java.util.Map;


/**
 * Created by linkaipeng on 2018/5/20.
 */

public class NetworkLogDetailActivity extends Activity {

    private NetworkFeedModel mNetworkFeedModel;
    private TextView mRequestHeadersTextView;
    private TextView mResponseHeadersTextView;
    private TextView mBodyTextView;
    private TextView mRequestTextView;
    private TextView mBack;
    private TextView mTvUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_log_detail);

        mRequestHeadersTextView = (TextView) findViewById(R.id.request_headers_textView);
        mResponseHeadersTextView = (TextView) findViewById(R.id.response_headers_textView);
        mBodyTextView = (TextView) findViewById(R.id.body_textView);
        mRequestTextView = (TextView) findViewById(R.id.request_textView);
        mBack = (TextView) findViewById(R.id.feed_detail_back_layout);
        mTvUrl = (TextView) findViewById(R.id.tv_url);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    public static void start(Context context, String requestId) {
        Intent starter = new Intent(context, NetworkLogDetailActivity.class);
        starter.putExtra("requestId", requestId);
        context.startActivity(starter);
    }


    private void initData() {
        String requestId = getIntent().getStringExtra("requestId");
        if (TextUtils.isEmpty(requestId)) {
            return;
        }
        mNetworkFeedModel = DataPoolImpl.getInstance().getNetworkFeedModel(requestId);
        if (mNetworkFeedModel == null) {
            return;
        }
        mTvUrl.setText(mNetworkFeedModel.getUrl());
        setRequestHeaders();
        setResponseHeaders();
        setBody();
    }

    private void setRequestHeaders() {
        mRequestHeadersTextView.setText(parseHeadersMapToString(mNetworkFeedModel.getRequestHeadersMap()));
    }

    private void setResponseHeaders() {
        mResponseHeadersTextView.setText(parseHeadersMapToString(mNetworkFeedModel.getResponseHeadersMap()));
    }

    private void setBody() {
        try {
            if (mNetworkFeedModel.getContentType().contains("json")) {

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                JsonObject bodyJO = new JsonParser().parse(mNetworkFeedModel.getBody()).getAsJsonObject();

                mBodyTextView.setText(gson.toJson(bodyJO));
            } else {
                mBodyTextView.setText(mNetworkFeedModel.getBody());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(mNetworkFeedModel.getReqBody())) {
            mRequestTextView.setText("没有获取到请求参数");
            return;
        }
        mRequestTextView.setText(mNetworkFeedModel.getReqBody());
    }

    private String parseHeadersMapToString(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return "Header is Empty.";
        }
        StringBuilder headersBuilder = new StringBuilder();
        for (String name : headers.keySet()) {
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            headersBuilder
                    .append(name)
                    .append(": ")
                    .append(headers.get(name))
                    .append("\n");
        }
        return headersBuilder.toString();
    }


}
