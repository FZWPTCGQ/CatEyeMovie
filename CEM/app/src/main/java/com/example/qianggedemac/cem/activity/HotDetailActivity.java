package com.example.qianggedemac.cem.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;

public class HotDetailActivity extends BaseActivity {


    private TextView mTextView;

    @Override
    protected int setLayout() {
        return R.layout.activity_hot_detail;
    }

    @Override
    protected void initViews() {
        mTextView = (TextView)findViewById(R.id.hot_detail_activity);


    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId",1000);
        mTextView.setText(movieId + "");

    }
}
