package com.example.qianggedemac.cem.mine.mv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;

public class UnuseActivity extends BaseActivity {
    WebView webview;

    @Override
    protected int setLayout() {
        return R.layout.activity_unuse;
    }

    @Override
    protected void initViews() {
        webview = (WebView) findViewById(R.id.webview);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        webview.loadUrl(url);
        // 设置webview加载网页的属性 websettings
        WebSettings set = webview.getSettings();
//            artFastWv.loadUrl(url);
        // 让WebView能够执行javaScript
        set.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);

        set.setAllowFileAccess(true);

        // 设置缓存
        set.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//            set.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        set.setSupportZoom(true);
        // 将图片调整到合适的大小
        set.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        set.setDisplayZoomControls(true);
        // 设置默认字体大小
        set.setDefaultFontSize(12);
    }



}
