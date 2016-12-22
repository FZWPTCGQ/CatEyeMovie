package com.example.qianggedemac.cem.tool.oktools;

/**
 * Created by qianggedemac on 16/12/20.
 */

public interface NetCallBack<T>{
    //成功的方法
    void onResponse(T bean);
    //失败的方法
    void onError(Exception e);
}
