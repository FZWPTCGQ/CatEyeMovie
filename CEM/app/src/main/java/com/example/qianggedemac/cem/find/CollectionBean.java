package com.example.qianggedemac.cem.find;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by qianggedemac on 17/1/3.
 */

public class CollectionBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String nickName;
    private String time;
    private String urlImg;
    private String title;
    private int targetId;
    private int feedType;

    public CollectionBean(String nickName, String time, String urlImg, String title, int targetId, int feedType) {
        this.nickName = nickName;
        this.time = time;
        this.urlImg = urlImg;
        this.title = title;
        this.targetId = targetId;
        this.feedType = feedType;
    }

    public CollectionBean() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getFeedType() {
        return feedType;
    }

    public void setFeedType(int feedType) {
        this.feedType = feedType;
    }
}
