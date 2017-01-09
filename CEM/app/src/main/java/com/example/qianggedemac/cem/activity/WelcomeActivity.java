package com.example.qianggedemac.cem.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.activity.welcomebean.WelcomeBean;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mImageView;
    private Button mButton;
    private String path;
    private WelcomeBean mWelcomeBean;
    private CountDownTimer mCountDownTimer;
    private Animation mAnimation;

    @Override
    protected int setLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initViews() {
        mImageView = (ImageView)findViewById(R.id.welcome_iv);
        mButton = (Button)findViewById(R.id.welcome_btn);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(UrlTools.WELCOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("WelcomeActivity", response);
               Gson gson = new Gson();
                mWelcomeBean = gson.fromJson(response,WelcomeBean.class);
                if (mWelcomeBean.getPosters() != null && mWelcomeBean.getPosters().size() > 0){
                    path = mWelcomeBean.getPosters().get(0).getPic();
                    new ImageTask().execute(path);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    protected void initDatas() {
      new ImageTask().execute(path);
        mCountDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                mCountDownTimer.cancel();
                finish();
            }
        };
        mCountDownTimer.start();
        mButton.setOnClickListener(this);
        mAnimation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.scale_to_normal);

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                mCountDownTimer.cancel();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        mCountDownTimer.cancel();
        finish();
    }

    public class ImageTask extends AsyncTask<String,Void,Bitmap>{
      private Bitmap mBitmap;
       @Override
       protected Bitmap doInBackground(String... strings) {
           String path = strings[0];
           try {
               URL url = new URL(path);
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               InputStream inputStream = httpURLConnection.getInputStream();
               mBitmap = BitmapFactory.decodeStream(inputStream);
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return mBitmap;
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
           if (mBitmap != null){
               mImageView.setImageBitmap(mBitmap);
               mImageView.startAnimation(mAnimation);// 执行动画
           }
       }
   }
}
