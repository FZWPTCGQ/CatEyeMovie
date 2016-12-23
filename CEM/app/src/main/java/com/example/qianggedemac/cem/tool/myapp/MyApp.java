package com.example.qianggedemac.cem.tool.myapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by qianggedemac on 16/12/20.
 */
public class MyApp extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }


}
