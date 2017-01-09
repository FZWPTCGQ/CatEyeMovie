package com.example.qianggedemac.cem.mine.mv;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.SCREEN_WINDOW_FULLSCREEN;

// yuandan
public class PlayerActivity extends BaseActivity {
    int id;// 传过来的id
    String post;// 传过来的图片
    String playerUrl = "http://mapi.yinyuetai.com/playlist/show.json?deviceinfo=%7B%22aid%22:%2210201036%22,%22os%22:%22Android%22,%22ov%22:%225.0.2%22,%22rn%22:%22480*800%22,%22dn%22:%22Redmi%20Note%203%22,%22cr%22:%2246000%22,%22as%22:%22WIFI%22,%22uid%22:%22dbcaa6c4482bc05ecb0bf39dabf207d2%22,%22clid%22:110025000%7D&id=";
    private LinearLayout nav;
    private JCVideoPlayerStandard mVideoPlayer;
    ImageView mvDescribe;
    ImageView mvComment;
    ImageView relativeMv;
    private PlayerBean mPlayerBean;
    private List<PlayerBean> datas;
    private RequestQueue mRequestQueue;
    private int mPos;
    String title;

//    SensorManager sensorManager;
//    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    protected int setLayout() {
        return R.layout.activity_player;
    }

    @Override
    protected void initViews() {
        nav = (LinearLayout) findViewById(R.id.nav);
        mVideoPlayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        mvDescribe = (ImageView) findViewById(R.id.mv_describe);
        mvComment = (ImageView) findViewById(R.id.mv_comment);
        relativeMv = (ImageView) findViewById(R.id.relative_mv);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -10);
        title = intent.getStringExtra("title");
        post = intent.getStringExtra("post");
        mvDescribe.setOnClickListener(imageClickListener);
        mvComment.setOnClickListener(imageClickListener);
        relativeMv.setOnClickListener(imageClickListener);

    }

    private void setImageBackground(ImageView imageView, int resId) {
        imageView.setBackgroundResource(resId);
    }

    private View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mv_describe:
                    setImageBackground(mvDescribe, R.mipmap.player_mv_p);
                    setImageBackground(mvComment, R.mipmap.player_comment);
                    setImageBackground(relativeMv, R.mipmap.player_relative_mv);
                    break;
                case R.id.mv_comment:
                    setImageBackground(mvDescribe, R.mipmap.player_mv);
                    setImageBackground(mvComment, R.mipmap.player_comment_p);
                    setImageBackground(relativeMv, R.mipmap.player_relative_mv);
                    break;
                case R.id.relative_mv:
                    setImageBackground(mvDescribe, R.mipmap.player_mv);
                    setImageBackground(mvComment, R.mipmap.player_comment);
                    setImageBackground(relativeMv, R.mipmap.player_relative_mv_p);
                    break;
            }
        }
    };

    @Override
    protected void initDatas() {
        id = getIntent().getIntExtra("id", -10);
        mPos = getIntent().getIntExtra("pos", -1);
        mPlayerBean = new PlayerBean();
        datas = new ArrayList<>();

        OkHttpManager.getInstance().get((playerUrl + id), PlayerBean.class, new NetCallBack<PlayerBean>() {
            @Override
            public void onResponse(PlayerBean bean) {

                mPlayerBean = bean;
                mVideoPlayer.setUp(bean.getVideos().get(0).getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, mPlayerBean.getVideos().get(0).getTitle());
//                mVideoPlayer.startButton.performClick();
                Glide.with(PlayerActivity.this).load(post).into(mVideoPlayer.thumbImageView);
//                JCVideoPlayerStandard.startFullscreen(PlayerActivity.this, JCVideoPlayerStandard.class,bean.getVideos().get(0).getUrl(),title);

//                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//                sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        JCVideoPlayer.releaseAllVideos();
//        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private PlayVideoListener playVideoListener = new PlayVideoListener() {
        @Override
        public void playVideo(String url, String title) {
            mVideoPlayer.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, title);
            mVideoPlayer.startButton.performClick();
        }
    };
}
