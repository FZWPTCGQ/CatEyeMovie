package com.example.qianggedemac.cem.tool.oktools;

/**
 * Created by qianggedemac on 16/12/20.
 */
public class OkHttpManager {
    private static OkHttpManager ourInstance = new OkHttpManager();

    public static OkHttpManager getInstance() {
        return ourInstance;
    }

    private OkHttpManager() {
    }
}
