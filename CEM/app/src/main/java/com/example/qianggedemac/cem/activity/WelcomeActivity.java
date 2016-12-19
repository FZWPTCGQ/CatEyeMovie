package com.example.qianggedemac.cem.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.qianggedemac.cem.tool.url.UrlTools;
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
               Gson gson = new Gson();
                mWelcomeBean = gson.fromJson(response,WelcomeBean.class);
                path = mWelcomeBean.getPosters().get(0).getPic();
                new ImageTask().execute(path);
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
           }
       }
   }
}
