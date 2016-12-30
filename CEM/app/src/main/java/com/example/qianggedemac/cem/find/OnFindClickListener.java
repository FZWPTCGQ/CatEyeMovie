package com.example.qianggedemac.cem.find;

/**
 * Created by dllo on 2016/12/29.
 */

public interface OnFindClickListener {
    void findTopClick(String name);// 四个图标
    // 不同行布局的
    void findClick(int targetID, int feedType, String nickName, String urlImg, String title);
}
