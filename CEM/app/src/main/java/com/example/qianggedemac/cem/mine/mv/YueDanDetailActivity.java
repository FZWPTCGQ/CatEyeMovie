package com.example.qianggedemac.cem.mine.mv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.google.gson.Gson;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class YueDanDetailActivity extends BaseActivity {
    // 首播
    private JCVideoPlayerStandard videoPlayer;
    private ImageView yuedanDescribe, yuedanComment, yuedanList;
    YueDanDetailBean yueDanDetailBean;

    private String idUrl = "http://mapi.yinyuetai.com/video/show.json?deviceinfo=%7B%22aid%22:%2210201036%22,%22os%22:%22Android%22,%22ov%22:%225.0.2%22,%22rn%22:%22480*800%22,%22dn%22:%22Redmi%20Note%203%22,%22cr%22:%2246000%22,%22as%22:%22WIFI%22,%22uid%22:%22dbcaa6c4482bc05ecb0bf39dabf207d2%22,%22clid%22:110025000%7D&relatedVideos=true&id=";
    // chuan
    private int id;
    private String url,post;

    @Override
    protected int setLayout() {
        return R.layout.activity_yue_dan_detail;
    }

    @Override
    protected void initViews() {
        videoPlayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        yuedanDescribe = (ImageView) findViewById(R.id.yuedan_describe);
        yuedanComment = (ImageView) findViewById(R.id.yuedan_comment);
        yuedanList = (ImageView) findViewById(R.id.yuedan_list);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -10);
        post = intent.getStringExtra("post");
        url = intent.getStringExtra("url");
        yuedanComment.setOnClickListener(imageClickListener);
        yuedanDescribe.setOnClickListener(imageClickListener);
        yuedanList.setOnClickListener(imageClickListener);
        yueDanDetailBean = new YueDanDetailBean();
        parseUrl();
    }

    private void parseUrl() {
        OkHttpManager.getInstance().get(idUrl + id, YueDanDetailBean.class, new NetCallBack<YueDanDetailBean>() {
            @Override
            public void onResponse(YueDanDetailBean bean) {
                yueDanDetailBean = bean;
                // 设置播放网址 播放格式 播放题目
//                bean.getUrl()
                videoPlayer.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, bean.getTitle());
//                videoPlayer.startButton.performClick(); // 自动按开始键开始播放
                // 设置播放图片
                Glide.with(YueDanDetailActivity.this).load(post).into(videoPlayer.thumbImageView);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setImageBackground(ImageView imageView, int resId) {
        imageView.setBackgroundResource(resId);
    }

    private View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.yuedan_describe:
                    setImageBackground(yuedanDescribe, R.mipmap.player_yue_p);
                    setImageBackground(yuedanComment, R.mipmap.player_yue_comment);
                    setImageBackground(yuedanList, R.mipmap.player_yuelist);
                    break;
                case R.id.yuedan_comment:
                    setImageBackground(yuedanDescribe, R.mipmap.player_yue);
                    setImageBackground(yuedanComment, R.mipmap.player_yue_comment_p);
                    setImageBackground(yuedanList, R.mipmap.player_yuelist);
                    break;
                case R.id.yuedan_list:
                    setImageBackground(yuedanDescribe, R.mipmap.player_yue);
                    setImageBackground(yuedanComment, R.mipmap.player_yue_comment);
                    setImageBackground(yuedanList, R.mipmap.player_yuelist_p);
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        JCVideoPlayer.releaseAllVideos();
        super.onPause();

    }
}
