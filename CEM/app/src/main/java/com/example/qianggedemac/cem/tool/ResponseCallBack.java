package com.example.qianggedemac.cem.tool;

/**
 * Created by qianggedemac on 17/1/7.
 */

public interface ResponseCallBack<T> {
    /**
     * 请求失败返回
     */
    void onError(Exception e);
    /**
     * 请求成功返回
     */
    void onResponse(T t);
}
