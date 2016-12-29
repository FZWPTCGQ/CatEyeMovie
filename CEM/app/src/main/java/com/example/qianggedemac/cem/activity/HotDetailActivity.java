package com.example.qianggedemac.cem.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.litesuits.orm.LiteOrm;

public class HotDetailActivity extends BaseActivity {


    private WebView mWebView;
    private ImageView mImageView;

    @Override
    protected int setLayout() {
        return R.layout.activity_hot_detail;
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);

        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        mWebView = (WebView)findViewById(R.id.hot_detail_activity_webView);
        mImageView = (ImageView)findViewById(R.id.hot_detail_activity_like_iv);
        collection();

    }

    private void collection() {
        LiteOrm liteOrm = LiteOrm.newCascadeInstance(this,"Collection.db");

    }

    @Override
    protected void initDatas() {
        webViewMethod();

    }

    private void webViewMethod() {
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId", 1000);
        Log.d("HotDetailActivity", UrlTools.MOVIE_HOT_LIST_DETAIL_BEFORE + movieId + UrlTools.MOVIE_HOT_LIST_DETAIL_AFTER);
        mWebView.loadUrl(UrlTools.MOVIE_HOT_LIST_DETAIL_BEFORE + movieId + UrlTools.MOVIE_HOT_LIST_DETAIL_AFTER);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }
        });
        //设置webView加载网页的属性webSettings
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置缓存
        webSettings.setAppCacheEnabled(true);
        //设置缓存模式,一共四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
