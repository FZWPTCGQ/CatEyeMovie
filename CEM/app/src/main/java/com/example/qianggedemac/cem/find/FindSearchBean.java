package com.example.qianggedemac.cem.find;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/26.
 */

public class FindSearchBean {

    /**
     * data : [{"cat":"惊悚,冒险,科幻","dur":0,"enm":"Alien: Covenant","fra":"美国","frt":"2017-05-19","globalReleased":false,"id":78888,"img":"http://p1.meituan.net/w.h/movie/866fe782b00f79a267ff35aa5501647020123.jpg","movieType":0,"nm":"异形：契约","onlinePlay":false,"pubDesc":"2017-05-19美国上映","sc":0,"show":"","showst":1,"type":0,"ver":"2D","wish":5570,"wishst":0},{"cat":"剧情,喜剧","dur":111,"enm":"Mr. Donkey","globalReleased":true,"id":410612,"img":"http://p1.meituan.net/w.h/movie/13d4883803f25244d266c7cd3ac2f15d327618.jpg","movieType":0,"nm":"驴得水","onlinePlay":false,"pubDesc":"2016-10-28大陆上映","rt":"2016-10-28","sc":8.5,"show":"","showst":2,"type":0,"ver":"2D","wish":29385,"wishst":0},{"cat":"剧情,喜剧,爱情","dur":113,"enm":"I Belonged to You","globalReleased":true,"id":246390,"img":"http://p1.meituan.net/w.h/movie/7c81af730b119bc04087df7cf2c88f0c310212.png","movieType":0,"nm":"从你的全世界路过","onlinePlay":false,"pubDesc":"2016-09-29大陆上映","rt":"2016-09-29","sc":8.6,"show":"","showst":2,"type":0,"ver":"2D/中国巨幕","wish":240303,"wishst":0},{"cat":"动作,冒险,科幻","dur":140,"enm":"Assassin's Creed","fra":"美国","frt":"2016-12-21","globalReleased":false,"id":78608,"img":"http://p0.meituan.net/w.h/movie/1198aab07e941e381fff91b0a48812f3409959.jpg","movieType":0,"nm":"刺客信条","onlinePlay":false,"pubDesc":"2017大陆上映","sc":0,"show":"","showst":1,"time":"2017","type":0,"ver":"2D","wish":15588,"wishst":0},{"cat":"动画,爱情,奇幻","dur":107,"enm":"君の名は。","fra":"日本","frt":"2016-08-26","globalReleased":true,"id":344881,"img":"http://p0.meituan.net/w.h/movie/910b2e6c7cb0da947d65ef5c33929eb9366676.jpg","movieType":0,"nm":"你的名字。","onlinePlay":false,"pubDesc":"2016-12-02大陆上映","rt":"2016-12-02","sc":9.2,"show":"","showst":3,"type":0,"ver":"2D","wish":277114,"wishst":0},{"cat":"动作,惊悚,科幻","dur":106,"enm":"Resident Evil: The Final Chapter","fra":"美国,韩国,日本","frt":"2017-01-27,,2016-12-23","ftime":",2017-01,","globalReleased":false,"id":246065,"img":"http://p1.meituan.net/w.h/movie/f178f8054c86caacf9fb723ffd932e4a143052.jpg","movieType":0,"nm":"生化危机6：终章","onlinePlay":false,"pubDesc":"2017-02大陆上映","sc":0,"show":"","showst":1,"time":"2017-02","type":0,"ver":"2D","wish":35349,"wishst":0},{"cat":"剧情,动作,悬疑","dur":125,"enm":"The Wasted Times","globalReleased":true,"id":246261,"img":"http://p0.meituan.net/w.h/movie/c23a59322f0053bba6bb05853b1958507925907.jpg","movieType":0,"nm":"罗曼蒂克消亡史","onlinePlay":false,"pubDesc":"2016-12-16大陆上映","rt":"2016-12-16","sc":7.5,"show":"","showst":3,"type":0,"ver":"2D/中国巨幕/全景声","wish":60896,"wishst":0},{"cat":"喜剧,爱情","dur":128,"enm":"See You Tomorrow","globalReleased":true,"id":246388,"img":"http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg","movieType":0,"nm":"摆渡人","onlinePlay":false,"pubDesc":"2016-12-23大陆上映","rt":"2016-12-23","sc":7.8,"show":"","showst":3,"type":0,"ver":"2D/中国巨幕/全景声","wish":203019,"wishst":0},{"cat":"喜剧,爱情","dur":113,"enm":"Some Like It Hot","globalReleased":false,"id":1188324,"img":"http://p1.meituan.net/w.h/movie/2e1e5b7d04489592f4b7212c19d4bd78211428.jpg","movieType":0,"nm":"情圣","onlinePlay":false,"pubDesc":"2016-12-30大陆上映","rt":"2016-12-30","sc":8.7,"show":"","showst":4,"type":0,"ver":"2D","wish":17672,"wishst":0},{"cat":"动作,惊悚,犯罪","dur":160,"enm":"Fast &Furious 8","fra":"美国","frt":"2017-04-14","globalReleased":false,"id":248700,"img":"http://p0.meituan.net/w.h/movie/625a5ae8a8699c265695d0ffc859fdc1706854.jpg","movieType":0,"nm":"速度与激情8","onlinePlay":false,"pubDesc":"2017-04-14美国上映","sc":0,"show":"","showst":1,"type":0,"ver":"2D","wish":32647,"wishst":0}]
     * total : 10
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cat : 惊悚,冒险,科幻
         * dur : 0
         * enm : Alien: Covenant
         * fra : 美国
         * frt : 2017-05-19
         * globalReleased : false
         * id : 78888
         * img : http://p1.meituan.net/w.h/movie/866fe782b00f79a267ff35aa5501647020123.jpg
         * movieType : 0
         * nm : 异形：契约
         * onlinePlay : false
         * pubDesc : 2017-05-19美国上映
         * sc : 0
         * show :
         * showst : 1
         * type : 0
         * ver : 2D
         * wish : 5570
         * wishst : 0
         * rt : 2016-10-28
         * time : 2017
         * ftime : ,2017-01,
         */

        private String cat;
        private int dur;
        private String enm;
        private String fra;
        private String frt;
        private boolean globalReleased;
        private int id;
        private String img;
        private int movieType;
        private String nm;
        private boolean onlinePlay;
        private String pubDesc;
        private float sc;
        private String show;
        private int showst;
        private int type;
        private String ver;
        private int wish;
        private int wishst;
        private String rt;
        private String time;
        private String ftime;

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public int getDur() {
            return dur;
        }

        public void setDur(int dur) {
            this.dur = dur;
        }

        public String getEnm() {
            return enm;
        }

        public void setEnm(String enm) {
            this.enm = enm;
        }

        public String getFra() {
            return fra;
        }

        public void setFra(String fra) {
            this.fra = fra;
        }

        public String getFrt() {
            return frt;
        }

        public void setFrt(String frt) {
            this.frt = frt;
        }

        public boolean isGlobalReleased() {
            return globalReleased;
        }

        public void setGlobalReleased(boolean globalReleased) {
            this.globalReleased = globalReleased;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMovieType() {
            return movieType;
        }

        public void setMovieType(int movieType) {
            this.movieType = movieType;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public boolean isOnlinePlay() {
            return onlinePlay;
        }

        public void setOnlinePlay(boolean onlinePlay) {
            this.onlinePlay = onlinePlay;
        }

        public String getPubDesc() {
            return pubDesc;
        }

        public void setPubDesc(String pubDesc) {
            this.pubDesc = pubDesc;
        }

        public float getSc() {
            return sc;
        }

        public void setSc(float sc) {
            this.sc = sc;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public int getShowst() {
            return showst;
        }

        public void setShowst(int showst) {
            this.showst = showst;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }

        public int getWishst() {
            return wishst;
        }

        public void setWishst(int wishst) {
            this.wishst = wishst;
        }

        public String getRt() {
            return rt;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
