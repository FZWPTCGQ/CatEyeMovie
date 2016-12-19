package com.example.qianggedemac.cem.activity.welcomebean;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/19.
 */

public class WelcomeBean {

    private List<PostersBean> posters;

    public List<PostersBean> getPosters() {
        return posters;
    }

    public void setPosters(List<PostersBean> posters) {
        this.posters = posters;
    }

    public static class PostersBean {
        /**
         * canSkip : true
         * duration : 2500
         * end : 1482163199
         * id : 972
         * isShowLogo : 0
         * pic : http://p0.meituan.net/movie/52a6f618a83a63a109b05dc42691f2c3107328.jpg
         * screenType : 0
         * showLogo : false
         * start : 1482076800
         * times : 5
         * title : 12.19
         * type : 1
         * url :
         */

        private boolean canSkip;
        private int duration;
        private int end;
        private int id;
        private int isShowLogo;
        private String pic;
        private int screenType;
        private boolean showLogo;
        private int start;
        private int times;
        private String title;
        private int type;
        private String url;

        public boolean isCanSkip() {
            return canSkip;
        }

        public void setCanSkip(boolean canSkip) {
            this.canSkip = canSkip;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsShowLogo() {
            return isShowLogo;
        }

        public void setIsShowLogo(int isShowLogo) {
            this.isShowLogo = isShowLogo;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getScreenType() {
            return screenType;
        }

        public void setScreenType(int screenType) {
            this.screenType = screenType;
        }

        public boolean isShowLogo() {
            return showLogo;
        }

        public void setShowLogo(boolean showLogo) {
            this.showLogo = showLogo;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
