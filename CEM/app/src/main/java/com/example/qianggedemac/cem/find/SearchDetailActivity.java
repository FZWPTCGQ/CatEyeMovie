package com.example.qianggedemac.cem.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.qianggedemac.cem.R;

public class SearchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("SearchDetailActivity", url);
    }
}
