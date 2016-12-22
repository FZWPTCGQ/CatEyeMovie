package com.example.qianggedemac.cem.film.wait;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/22.
 */

public class RecommedBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img : http://p0.meituan.net/movie/aac0728d2ce5d9da863a67013a2456f636734.jpg
         * movieId : 342616
         * movieName : 掠夺者
         * name : 无法停止的连续犯罪版预告片
         * originName : 无法停止的连续犯罪版预告片
         * url : http://maoyan.meituan.net/movie/videos/854x4806f3c30d568344b7dbfee06ef39b7cbe0.mp4
         * videoId : 82459
         * wish : 11969
         */

        private String img;
        private int movieId;
        private String movieName;
        private String name;
        private String originName;
        private String url;
        private int videoId;
        private int wish;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }
    }
}
