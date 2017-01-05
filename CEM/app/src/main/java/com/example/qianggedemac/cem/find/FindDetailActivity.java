package com.example.qianggedemac.cem.find;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.film.findfilm.ClassifyBean;
import com.example.qianggedemac.cem.tool.CollectionDBTool;
import com.example.qianggedemac.cem.tool.LiteOrmInstance;
import com.example.qianggedemac.cem.tool.UrlTools;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class FindDetailActivity extends BaseActivity {

    private WebView mWebView;
    private Toolbar mToolbar;
    private TextView mTextView;
    private FindDetailBR mFindDetailBR;

    private String mTitle;



    private String nickName;
    private String urlImg;
    private int mFeedType;
    private int mTargetId;
    private String name;


    private String wvUrl;

    private ImageView mReturnIv;
    private ImageView mCollectionIv;
    private boolean isCollected = false;
    @Override
    protected int setLayout() {
        return R.layout.activity_find_detail;
    }

    @Override
    protected void initViews() {
       //  CollectionDBTool.getInstance().queryByValuesCollectBean(CollectionBean.class,"title",);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
//        mToolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        mWebView = (WebView) findViewById(R.id.find_detail_wv);
        mTextView = (TextView) findViewById(R.id.return_tv);
        mReturnIv = (ImageView) findViewById(R.id.return_iv);
        mCollectionIv = (ImageView)findViewById(R.id.collect_iv);
        Intent intent = getIntent();
        mTargetId = intent.getIntExtra("targetID", -1);
        mFeedType = intent.getIntExtra("feedType", -1);
        name = intent.getStringExtra("Title");
        nickName = intent.getStringExtra("nickName");
        urlImg = intent.getStringExtra("urlImg");

        if (mFeedType == 7) {
            wvUrl = UrlTools.FIND_TODAY_DETAIL + mTargetId + "?_v_=yes";
        } else if (mFeedType == 2 || mFeedType == 10) {
            wvUrl = UrlTools.FIND_TODAY_DETAIL_ELSE + mTargetId + "?_v_=yes";
        } else if (mFeedType == -1) {
            Intent intent1 = getIntent();
            mTitle = intent1.getStringExtra("title");

            if (mTitle.equals("今日TOP10")) {
                wvUrl = UrlTools.FIND_TOP10;
                mTextView.setText(name);
            } else if (mTitle.equals("影视快讯")) {
                wvUrl = UrlTools.FIND_FAST_MSG;
                mTextView.setText(name);
            } else if (mTitle.equals("周边商城")) {
                wvUrl = UrlTools.FIND_STORE;
                mTextView.setText(name);
            }else if (mTitle.equals("实时票房")) {
                wvUrl = UrlTools.FIND_NOW;
                mTextView.setText(name);
            }

        }
        /**
         * 判断数据库中是否有该条数据
         */
        CollectionDBTool.getInstance().queryByValuesCollectBean(CollectionBean.class, "title", new String[]{name}, new CollectionDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<CollectionBean> collectionBeen) {
                if (collectionBeen.size() > 0){
                    mCollectionIv.setImageResource(R.mipmap.xingxing_select);
                }else{
                    mCollectionIv.setImageResource(R.mipmap.xingxing);
                }
            }
        });

        mFindDetailBR = new FindDetailBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("findDetail");
        registerReceiver(mFindDetailBR, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mFindDetailBR);
    }

    @Override
    protected void initDatas() {

        mReturnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWebView.loadUrl(wvUrl);
        Log.d("FindDetailActivity", wvUrl);
        /**
         * 将webView的标头去掉
         */
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String fun="javascript:function getClass(parent,sClass)" +
                        " { var aEle=parent.getElementsByTagName('div'); " +
                        "var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";

                view.loadUrl(fun);
                String fun2="javascript:function hideOther() {getClass(document,'navload " +
                        "clearfix')[0].style.display='none';getClass(document,'navbar')[0].style.display='none';}";
                view.loadUrl(fun2);

                view.loadUrl("javascript:hideOther();");

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }
        });
        // 设置webview加载网页的属性 websettings
        WebSettings set = mWebView.getSettings();
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
//        mTextView.setAlpha(0);
//     mToolbar.setTitle("标题");
//
//    mToolbar.setSubtitle("子标题");

        mCollectionIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (!isCollected){
                 /**
                  * 获取当前系统的时间
                  */
                 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy--MM--dd hh:mm:ss");
                 String data = simpleDateFormat.format(new java.util.Date());
                 String data1 = data.substring(6,12);
                 CollectionBean collectionBean = new CollectionBean(nickName,data1,urlImg,name,mTargetId,mFeedType);
                 CollectionDBTool.getInstance().insertCollectBean(collectionBean);
                 mCollectionIv.setImageResource(R.mipmap.xingxing_select);
                 isCollected = !isCollected;
                // Toast.makeText(FindDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
             }else{
                 CollectionDBTool.getInstance().deleteValueBean(CollectionBean.class,"title",new String[]{name});
                 mCollectionIv.setImageResource(R.mipmap.xingxing);
                 isCollected = !isCollected;
               //  Toast.makeText(FindDetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
             }

            }
        });

    }

    public class FindDetailBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            int param = intent.getIntExtra("param", 100);
//            switch (param) {
//                case 0:
//                    mTargetId = intent.getIntExtra("targetID", -1);
//                    mFeedType = intent.getIntExtra("feedType", -1);
//                    mNickName = intent.getStringExtra("nickName");
//                    mUrlImg = intent.getStringExtra("urlImg");
//                    mMainTitle = intent.getStringExtra("title");
//                    mTitle = intent.getStringExtra("Title");
//
//                    if (mFeedType == 7) {
//                        wvUrl = UrlTools.FIND_TODAY_DETAIL + mTargetId + "?_v_=yes";
//                    } else if (mFeedType == 2 || mFeedType == 10) {
//                        wvUrl = UrlTools.FIND_TODAY_DETAIL_ELSE + mTargetId + "?_v_=yes";
//                    } else if (mTitle.equals("今日TOP10")) {
//                        wvUrl = UrlTools.FIND_TOP10;
//                    } else if (mTitle.equals("实时票房")) {
//                        wvUrl = UrlTools.FIND_NOW;
//                    } else if (mTitle.equals("影视快讯")) {
//                        wvUrl = UrlTools.FIND_FAST_MSG;
//                    }
//                    Log.d("FindDetailBR", wvUrl);
//                    break;
//            }
        }
    }
}
