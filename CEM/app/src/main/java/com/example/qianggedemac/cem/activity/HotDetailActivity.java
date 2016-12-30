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
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.UrlTools;

public class HotDetailActivity extends BaseActivity {


    private WebView mWebView;

    @Override
    protected int setLayout() {
        return R.layout.activity_hot_detail;
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);

        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        mWebView = (WebView)findViewById(R.id.hot_detail_activity_webView);

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId",1000);
        Log.d("HotDetailActivity", UrlTools.MOVIE_HOT_LIST_DETAIL_BEFORE + movieId + UrlTools.MOVIE_HOT_LIST_DETAIL_AFTER);
        mWebView.loadUrl(UrlTools.MOVIE_HOT_LIST_DETAIL_BEFORE + movieId + UrlTools.MOVIE_HOT_LIST_DETAIL_AFTER);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String fun="javascript:function getClass(parent,sClass) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";

                view.loadUrl(fun);

                String fun2="javascript:function hideOther() {getClass(document,'navload " +
                        "clearfix')[0].style.display='none';getClass(document,'navbar')[0].style.display='none';}";

                view.loadUrl(fun2);

                view.loadUrl("javascript:hideOther();");


                super.onPageFinished(view, url);
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
