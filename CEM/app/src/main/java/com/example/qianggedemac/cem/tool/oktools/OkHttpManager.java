package com.example.qianggedemac.cem.tool.oktools;

import android.app.Application;

import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qianggedemac on 16/12/20.
 */
public class OkHttpManager  {
    private static OkHttpManager mOkHttpManager;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    private static OkHttpManager ourInstance = new OkHttpManager();

    public static OkHttpManager getInstance() {
        if (mOkHttpManager == null){
            synchronized (OkHttpManager.class){
                if (mOkHttpManager == null){
                    mOkHttpManager = new OkHttpManager();
                }
            }
        }
        return mOkHttpManager;
    }

    public OkHttpManager() {
        //保持数据返回主线程进行UI刷新
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    //post请求
    public <T> void post(String url, Class<T> mClass, NetCallBack<T> netCallBack, HashMap<String,String> body){
        FormBody.Builder builder = new FormBody.Builder();
        for (String s : body.keySet()) {
            builder.add(s,body.get(s));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).build();
        postOkHttp(request,mClass,netCallBack);
    }

    private <T> void postOkHttp(Request request, final Class<T> mClass, final NetCallBack<T> netCallBack) {
//        try {
//            Response execute = mOkHttpClient.newCall(request).execute();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new ErrorRunnable<T>(netCallBack,e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               //网络请求成功
                String result = response.body().string();
                try {

                    T bean = mGson.fromJson(result,mClass);
                    mHandler.post(new ResponseRunnable<T>(netCallBack,bean));
                } catch (Exception exception) {
                    mHandler.post(new ErrorRunnable<T>(netCallBack,exception));
                }
            }
        });
    }
    //get请求
    //声明泛型
    public<T> void get(String url, final Class<T> mClass, final NetCallBack<T> callBack){
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //网络请求失败
                mHandler.post(new ErrorRunnable<T>(callBack,e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                String result = response.body().string();
                try {
                  T bean = mGson.fromJson(result,mClass);
                    mHandler.post(new ResponseRunnable<T>(callBack,bean));

                } catch (Exception exception) {
                    mHandler.post(new ErrorRunnable<T>(callBack,exception));
                }
            }
        });
    }

    //为了省略成功和失败之后把数据传回主线程的过程,而创建的类
    abstract class OkHttpRunnable<T> implements Runnable{
        protected NetCallBack<T> mNetCallBack;
        //构造方法
        public OkHttpRunnable(NetCallBack<T> netCallBack){
            mNetCallBack = netCallBack;
        }
    }
    //数据成功请求
    class ResponseRunnable<T> extends OkHttpRunnable<T>{
        private T bean;
        public ResponseRunnable(NetCallBack<T> netCallBack,T bean) {
            super(netCallBack);
            this.bean = bean;
        }

        @Override
        public void run() {
            mNetCallBack.onResponse(bean);
        }
    }
    //数据请求失败
    class ErrorRunnable<T> extends OkHttpRunnable<T>{
        private Exception mException;
        public ErrorRunnable(NetCallBack<T> netCallBack,Exception e) {
            super(netCallBack);
            this.mException = e;
        }

        @Override
        public void run() {
            mNetCallBack.onError(mException);
        }
    }
}
