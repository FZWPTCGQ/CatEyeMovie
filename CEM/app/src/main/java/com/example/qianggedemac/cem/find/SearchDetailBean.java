package com.example.qianggedemac.cem.find;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/27.
 */

public class SearchDetailBean {

    /**
     * data : [{"list":[{"cat":"喜剧,爱情","dur":128,"enm":"See You Tomorrow","globalReleased":true,"id":246388,"img":"http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg","movieType":0,"nm":"摆渡人","onlinePlay":false,"pubDesc":"2016-12-23大陆上映","rt":"2016-12-23","sc":7.8,"show":"","showst":3,"type":0,"ver":"2D/中国巨幕/全景声","wish":203019,"wishst":0},{"cat":"奇幻,恐怖,惊悚","dur":100,"enm":"The Ferryman","fra":"新西兰","frt":"2007-04-11","globalReleased":true,"id":28290,"img":"http://p0.meituan.net/w.h/movie/13/8575689.jpg","movieType":0,"nm":"摆渡人","onlinePlay":false,"pubDesc":"2007-04-11大陆上映","rt":"2007-04-11","sc":4.9,"show":"","showst":2,"type":0,"ver":"2D","wish":0,"wishst":0}],"total":2,"type":0},{"list":[{"commentCount":55,"id":17767,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17767","source":"猫眼电影","title":"冯小刚谈《潘金莲》非常好，赞《长城》《摆渡人》都挺好","type":4,"viewCount":4909},{"commentCount":77,"id":17757,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17757","source":"猫眼电影","title":"《摆渡人》票房遭反超，王家卫微博发声能否挽救","type":4,"viewCount":10984},{"commentCount":34,"id":17756,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17756","source":"猫眼电影","title":"《摆渡人》4天破3亿，三天三破记录高居不下","type":4,"viewCount":3767},{"commentCount":82,"id":17752,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17752","source":"猫眼电影","title":"张嘉佳回应《摆渡人》差评：接受负评，拒绝谩骂","type":4,"viewCount":5370},{"commentCount":100,"id":17751,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17751","source":"猫眼电影","title":"一场电影圈的互顶，周星驰手写\u201c我也喜欢\u201d挺王家卫","type":4,"viewCount":3151},{"commentCount":0,"id":17742,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17742","source":"猫眼专业版","title":"上周票房10.6亿，《摆渡人》《铁道飞虎》《长城》引爆圣诞！","type":4,"viewCount":1888},{"commentCount":0,"id":17735,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17735","source":"猫眼电影","title":"一周口碑榜：《摆渡人》垫底，《铁道飞虎》也很差","type":4,"viewCount":44},{"author":"易二三四","commentCount":0,"id":122177,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=122177","source":"","title":"《摆渡人》：爱是折磨人的东西，让人心碎却又着迷","type":4,"viewCount":11},{"author":"艾鱼","commentCount":0,"id":122210,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=122210","source":"","title":"你有自己的朗读者，而我只是个摆渡人","type":4,"viewCount":2},{"author":"邑人影院","commentCount":14,"id":122158,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=122158","source":"","title":"从《长城》到《摆渡人》，年末的挽歌，来得一点也不动听","type":4,"viewCount":850}],"total":138,"type":4}]
     * correction :
     * correctionV2 :
     * correctionType : -1
     * algotype : 1
     * facetList : []
     */

    private String correction;
    private String correctionV2;
    private int correctionType;
    private int algotype;
    private List<DataBean> data;
    private List<?> facetList;

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getCorrectionV2() {
        return correctionV2;
    }

    public void setCorrectionV2(String correctionV2) {
        this.correctionV2 = correctionV2;
    }

    public int getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(int correctionType) {
        this.correctionType = correctionType;
    }

    public int getAlgotype() {
        return algotype;
    }

    public void setAlgotype(int algotype) {
        this.algotype = algotype;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getFacetList() {
        return facetList;
    }

    public void setFacetList(List<?> facetList) {
        this.facetList = facetList;
    }

    public static class DataBean {
        /**
         * list : [{"cat":"喜剧,爱情","dur":128,"enm":"See You Tomorrow","globalReleased":true,"id":246388,"img":"http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg","movieType":0,"nm":"摆渡人","onlinePlay":false,"pubDesc":"2016-12-23大陆上映","rt":"2016-12-23","sc":7.8,"show":"","showst":3,"type":0,"ver":"2D/中国巨幕/全景声","wish":203019,"wishst":0},{"cat":"奇幻,恐怖,惊悚","dur":100,"enm":"The Ferryman","fra":"新西兰","frt":"2007-04-11","globalReleased":true,"id":28290,"img":"http://p0.meituan.net/w.h/movie/13/8575689.jpg","movieType":0,"nm":"摆渡人","onlinePlay":false,"pubDesc":"2007-04-11大陆上映","rt":"2007-04-11","sc":4.9,"show":"","showst":2,"type":0,"ver":"2D","wish":0,"wishst":0}]
         * total : 2
         * type : 0
         */

        private int total;
        private int type;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cat : 喜剧,爱情
             * dur : 128
             * enm : See You Tomorrow
             * globalReleased : true
             * id : 246388
             * img : http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg
             * movieType : 0
             * nm : 摆渡人
             * onlinePlay : false
             * pubDesc : 2016-12-23大陆上映
             * rt : 2016-12-23
             * sc : 7.8
             * show :
             * showst : 3
             * type : 0
             * ver : 2D/中国巨幕/全景声
             * wish : 203019
             * wishst : 0
             * fra : 新西兰
             * frt : 2007-04-11
             */

            private String cat;
            private int dur;
            private String enm;
            private boolean globalReleased;
            private int id;
            private String img;
            private int movieType;
            private String nm;
            private boolean onlinePlay;
            private String pubDesc;
            private String rt;
            private double sc;
            private String show;
            private int showst;
            private int type;
            private String ver;
            private int wish;
            private int wishst;
            private String fra;
            private String frt;

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

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
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
        }
    }
}
