package com.example.qianggedemac.cem.film.hot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/21.
 */

public class HotRefreshBean {

    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":true,"movies":[{"cnms":0,"sn":0,"showInfo":"今天8家影院放映15场","late":false,"scm":"惊险3分钟，飞机坠天空","imax":true,"snum":25224,"preSale":0,"vd":"","dir":"克林特·伊斯特伍德","star":"汤姆·汉克斯,艾伦·艾克哈特,劳拉·琳妮","cat":"剧情,传记,灾难","wish":21258,"3d":false,"pn":74,"showDate":"","src":"","sc":8.9,"ver":"2D/IMAX 2D/中国巨幕","rt":"2016-12-09上映","img":"http://p1.meituan.net/165.220/movie/946e6e4182042c1e94feacb0dc93e7e3229652.jpg","nm":"萨利机长","dur":96,"time":"","id":342003},{"cnms":0,"sn":0,"showInfo":"2016-12-23 本周五上映","late":false,"scm":"银行遭抢劫，真相太凛冽","imax":false,"snum":3338,"preSale":1,"vd":"","dir":"史蒂芬·C·米勒","star":"布鲁斯·威利斯,克里斯托弗·米洛尼,戴夫·巴蒂斯塔","cat":"动作,犯罪","wish":10934,"3d":false,"pn":38,"showDate":"","src":"","sc":0,"ver":"2D","rt":"本周五上映","img":"http://p0.meituan.net/165.220/movie/55e03b872ef37050590ddb4dc1ad88172813333.jpg","nm":"掠夺者","dur":107,"time":"","id":342616},{"cnms":0,"sn":0,"showInfo":"2016-12-30 下周五上映","late":false,"scm":"哇哈哈哈哈，爆笑把年跨","imax":false,"snum":239,"preSale":1,"vd":"","dir":"宋晓飞,董旭","star":"肖央,闫妮,小沈阳","cat":"喜剧,爱情","wish":10629,"3d":false,"pn":32,"showDate":"","src":"","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/2e1e5b7d04489592f4b7212c19d4bd78211428.jpg","nm":"情圣","dur":113,"time":"","id":1188324},{"cnms":0,"sn":0,"showInfo":"今天5家影院放映11场","late":false,"scm":"快递藏国宝，爆笑接力跑","imax":false,"snum":47464,"preSale":0,"vd":"","dir":"宋啸","star":"陈赫,宋智孝,大卫·贝利","cat":"喜剧,动作","wish":50466,"3d":false,"pn":161,"showDate":"","src":"","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-12-02上映","img":"http://p0.meituan.net/165.220/movie/658b714699f37110db35e343474ccf12808915.png","nm":"超级快递","dur":91,"time":"","id":343866},{"cnms":0,"sn":0,"showInfo":"今天5家影院放映10场","late":false,"scm":"神奇动物城，法师显超能","imax":true,"snum":251824,"preSale":0,"vd":"","dir":"大卫·叶茨","star":"埃迪·雷德梅恩,凯瑟琳·沃特森,艾莉森·苏朵儿","cat":"冒险,奇幻,家庭","wish":130686,"3d":true,"pn":253,"showDate":"","src":"","sc":8.9,"ver":"3D/IMAX 3D/中国巨幕","rt":"2016-11-25上映","img":"http://p0.meituan.net/165.220/movie/f2820b28cff46c530a1aee47a2c00011274783.jpg","nm":"神奇动物在哪里","dur":133,"time":"","id":248918},{"cnms":0,"sn":0,"showInfo":"今天6家影院放映10场","late":false,"scm":"天敌变拍档，携手战猫王","imax":false,"snum":986,"preSale":0,"vd":"","dir":"孙友树,刘邦邦","star":"徐康,朱蓉蓉,周许晨阳","cat":"喜剧,动画,奇幻","wish":3275,"3d":false,"pn":49,"showDate":"","src":"","sc":6.8,"ver":"2D","rt":"2016-12-17上映","img":"http://p1.meituan.net/165.220/movie/f0133449a1d777a9afa716c566d49d7f168417.jpg","nm":"功夫四侠","dur":90,"time":"","id":1132299},{"cnms":0,"sn":0,"showInfo":"今天4家影院放映8场","late":false,"scm":"无敌铁蜜队，怂男一起怼","imax":false,"snum":294,"preSale":0,"vd":"","dir":"陈剑飞","star":"李思睿,刘思彤,王子","cat":"喜剧,爱情","wish":6051,"3d":false,"pn":46,"showDate":"","src":"","sc":0,"ver":"2D","rt":"2016-12-16上映","img":"http://p0.meituan.net/165.220/movie/7a2b1dea529084ded58f44708522ca33872441.jpg","nm":"五女闹京城","dur":82,"time":"","id":344812},{"cnms":0,"sn":0,"showInfo":"今天4家影院放映7场","late":false,"scm":"异能桃花源，穿越时光圈","imax":false,"snum":55684,"preSale":0,"vd":"","dir":"蒂姆·波顿","star":"伊娃·格林,阿沙·巴特菲尔德,塞缪尔·杰克逊","cat":"剧情,冒险,奇幻","wish":103395,"3d":true,"pn":109,"showDate":"","src":"","sc":8.6,"ver":"2D/3D/中国巨幕/全景声","rt":"2016-12-02上映","img":"http://p0.meituan.net/165.220/movie/eccf1862c4f30042a373a080acc18ccf9587819.jpeg","nm":"佩小姐的奇幻城堡","dur":126,"time":"","id":246373},{"cnms":0,"sn":0,"showInfo":"2016-12-30 下周五上映","late":false,"scm":"绑架十二年，归家惹事端","imax":false,"snum":1408,"preSale":1,"vd":"","dir":"周隼","star":"胡歌,宋佳,颜卓灵","cat":"爱情,悬疑,犯罪","wish":46432,"3d":false,"pn":65,"showDate":"","src":"","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/6b765c72510e7f4cd2f0cd8a6e9b4b7c996096.png","nm":"那年夏天你去了哪里","dur":0,"time":"","id":1187526},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"金莲戏中戏，官场局中局","imax":false,"snum":248113,"preSale":0,"vd":"","dir":"冯小刚","star":"范冰冰,郭涛,董成鹏","cat":"剧情,喜剧","wish":185572,"3d":false,"pn":211,"showDate":"","src":"","sc":7.9,"ver":"2D/中国巨幕/全景声","rt":"2016-11-18上映","img":"http://p1.meituan.net/165.220/movie/8e5eabeadf237a10227a3124419410ef549583.jpg","nm":"我不是潘金莲","dur":140,"time":"","id":341749},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"美女有异瞳，黑暗再行动","imax":false,"snum":31271,"preSale":0,"vd":"","dir":"静野孔文","star":"高山南,天海佑希,林原惠美","cat":"动画,悬疑","wish":43219,"3d":false,"pn":80,"showDate":"","src":"","sc":8.7,"ver":"2D","rt":"2016-11-25上映","img":"http://p0.meituan.net/165.220/movie/6742d5169d136a58a3b1ca2fd071e88c248086.jpg","nm":"名侦探柯南：纯黑的恶梦","dur":112,"time":"","id":344073},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"谱生命赞歌，书人间蹉跎","imax":false,"snum":514,"preSale":0,"vd":"","dir":"陈为军","star":"夏锦菊,陈小凤,李双双","cat":"纪录片","wish":5890,"3d":false,"pn":15,"showDate":"","src":"","sc":9.2,"ver":"2D","rt":"2016-12-16上映","img":"http://p0.meituan.net/165.220/movie/08978236117a0441087c9b1aa1b5731d526294.jpg","nm":"生门","dur":105,"time":"","id":1132973}]}
     */

    private ControlBean control;
    private int status;
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean {
        /**
         * expires : 1800
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {
        /**
         * hasNext : true
         * movies : [{"cnms":0,"sn":0,"showInfo":"今天8家影院放映15场","late":false,"scm":"惊险3分钟，飞机坠天空","imax":true,"snum":25224,"preSale":0,"vd":"","dir":"克林特·伊斯特伍德","star":"汤姆·汉克斯,艾伦·艾克哈特,劳拉·琳妮","cat":"剧情,传记,灾难","wish":21258,"3d":false,"pn":74,"showDate":"","src":"","sc":8.9,"ver":"2D/IMAX 2D/中国巨幕","rt":"2016-12-09上映","img":"http://p1.meituan.net/165.220/movie/946e6e4182042c1e94feacb0dc93e7e3229652.jpg","nm":"萨利机长","dur":96,"time":"","id":342003},{"cnms":0,"sn":0,"showInfo":"2016-12-23 本周五上映","late":false,"scm":"银行遭抢劫，真相太凛冽","imax":false,"snum":3338,"preSale":1,"vd":"","dir":"史蒂芬·C·米勒","star":"布鲁斯·威利斯,克里斯托弗·米洛尼,戴夫·巴蒂斯塔","cat":"动作,犯罪","wish":10934,"3d":false,"pn":38,"showDate":"","src":"","sc":0,"ver":"2D","rt":"本周五上映","img":"http://p0.meituan.net/165.220/movie/55e03b872ef37050590ddb4dc1ad88172813333.jpg","nm":"掠夺者","dur":107,"time":"","id":342616},{"cnms":0,"sn":0,"showInfo":"2016-12-30 下周五上映","late":false,"scm":"哇哈哈哈哈，爆笑把年跨","imax":false,"snum":239,"preSale":1,"vd":"","dir":"宋晓飞,董旭","star":"肖央,闫妮,小沈阳","cat":"喜剧,爱情","wish":10629,"3d":false,"pn":32,"showDate":"","src":"","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/2e1e5b7d04489592f4b7212c19d4bd78211428.jpg","nm":"情圣","dur":113,"time":"","id":1188324},{"cnms":0,"sn":0,"showInfo":"今天5家影院放映11场","late":false,"scm":"快递藏国宝，爆笑接力跑","imax":false,"snum":47464,"preSale":0,"vd":"","dir":"宋啸","star":"陈赫,宋智孝,大卫·贝利","cat":"喜剧,动作","wish":50466,"3d":false,"pn":161,"showDate":"","src":"","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-12-02上映","img":"http://p0.meituan.net/165.220/movie/658b714699f37110db35e343474ccf12808915.png","nm":"超级快递","dur":91,"time":"","id":343866},{"cnms":0,"sn":0,"showInfo":"今天5家影院放映10场","late":false,"scm":"神奇动物城，法师显超能","imax":true,"snum":251824,"preSale":0,"vd":"","dir":"大卫·叶茨","star":"埃迪·雷德梅恩,凯瑟琳·沃特森,艾莉森·苏朵儿","cat":"冒险,奇幻,家庭","wish":130686,"3d":true,"pn":253,"showDate":"","src":"","sc":8.9,"ver":"3D/IMAX 3D/中国巨幕","rt":"2016-11-25上映","img":"http://p0.meituan.net/165.220/movie/f2820b28cff46c530a1aee47a2c00011274783.jpg","nm":"神奇动物在哪里","dur":133,"time":"","id":248918},{"cnms":0,"sn":0,"showInfo":"今天6家影院放映10场","late":false,"scm":"天敌变拍档，携手战猫王","imax":false,"snum":986,"preSale":0,"vd":"","dir":"孙友树,刘邦邦","star":"徐康,朱蓉蓉,周许晨阳","cat":"喜剧,动画,奇幻","wish":3275,"3d":false,"pn":49,"showDate":"","src":"","sc":6.8,"ver":"2D","rt":"2016-12-17上映","img":"http://p1.meituan.net/165.220/movie/f0133449a1d777a9afa716c566d49d7f168417.jpg","nm":"功夫四侠","dur":90,"time":"","id":1132299},{"cnms":0,"sn":0,"showInfo":"今天4家影院放映8场","late":false,"scm":"无敌铁蜜队，怂男一起怼","imax":false,"snum":294,"preSale":0,"vd":"","dir":"陈剑飞","star":"李思睿,刘思彤,王子","cat":"喜剧,爱情","wish":6051,"3d":false,"pn":46,"showDate":"","src":"","sc":0,"ver":"2D","rt":"2016-12-16上映","img":"http://p0.meituan.net/165.220/movie/7a2b1dea529084ded58f44708522ca33872441.jpg","nm":"五女闹京城","dur":82,"time":"","id":344812},{"cnms":0,"sn":0,"showInfo":"今天4家影院放映7场","late":false,"scm":"异能桃花源，穿越时光圈","imax":false,"snum":55684,"preSale":0,"vd":"","dir":"蒂姆·波顿","star":"伊娃·格林,阿沙·巴特菲尔德,塞缪尔·杰克逊","cat":"剧情,冒险,奇幻","wish":103395,"3d":true,"pn":109,"showDate":"","src":"","sc":8.6,"ver":"2D/3D/中国巨幕/全景声","rt":"2016-12-02上映","img":"http://p0.meituan.net/165.220/movie/eccf1862c4f30042a373a080acc18ccf9587819.jpeg","nm":"佩小姐的奇幻城堡","dur":126,"time":"","id":246373},{"cnms":0,"sn":0,"showInfo":"2016-12-30 下周五上映","late":false,"scm":"绑架十二年，归家惹事端","imax":false,"snum":1408,"preSale":1,"vd":"","dir":"周隼","star":"胡歌,宋佳,颜卓灵","cat":"爱情,悬疑,犯罪","wish":46432,"3d":false,"pn":65,"showDate":"","src":"","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/6b765c72510e7f4cd2f0cd8a6e9b4b7c996096.png","nm":"那年夏天你去了哪里","dur":0,"time":"","id":1187526},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"金莲戏中戏，官场局中局","imax":false,"snum":248113,"preSale":0,"vd":"","dir":"冯小刚","star":"范冰冰,郭涛,董成鹏","cat":"剧情,喜剧","wish":185572,"3d":false,"pn":211,"showDate":"","src":"","sc":7.9,"ver":"2D/中国巨幕/全景声","rt":"2016-11-18上映","img":"http://p1.meituan.net/165.220/movie/8e5eabeadf237a10227a3124419410ef549583.jpg","nm":"我不是潘金莲","dur":140,"time":"","id":341749},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"美女有异瞳，黑暗再行动","imax":false,"snum":31271,"preSale":0,"vd":"","dir":"静野孔文","star":"高山南,天海佑希,林原惠美","cat":"动画,悬疑","wish":43219,"3d":false,"pn":80,"showDate":"","src":"","sc":8.7,"ver":"2D","rt":"2016-11-25上映","img":"http://p0.meituan.net/165.220/movie/6742d5169d136a58a3b1ca2fd071e88c248086.jpg","nm":"名侦探柯南：纯黑的恶梦","dur":112,"time":"","id":344073},{"cnms":0,"sn":0,"showInfo":"今天2家影院放映3场","late":false,"scm":"谱生命赞歌，书人间蹉跎","imax":false,"snum":514,"preSale":0,"vd":"","dir":"陈为军","star":"夏锦菊,陈小凤,李双双","cat":"纪录片","wish":5890,"3d":false,"pn":15,"showDate":"","src":"","sc":9.2,"ver":"2D","rt":"2016-12-16上映","img":"http://p0.meituan.net/165.220/movie/08978236117a0441087c9b1aa1b5731d526294.jpg","nm":"生门","dur":105,"time":"","id":1132973}]
         */

        private boolean hasNext;
        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {
            /**
             * cnms : 0
             * sn : 0
             * showInfo : 今天8家影院放映15场
             * late : false
             * scm : 惊险3分钟，飞机坠天空
             * imax : true
             * snum : 25224
             * preSale : 0
             * vd :
             * dir : 克林特·伊斯特伍德
             * star : 汤姆·汉克斯,艾伦·艾克哈特,劳拉·琳妮
             * cat : 剧情,传记,灾难
             * wish : 21258
             * 3d : false
             * pn : 74
             * showDate :
             * src :
             * sc : 8.9
             * ver : 2D/IMAX 2D/中国巨幕
             * rt : 2016-12-09上映
             * img : http://p1.meituan.net/165.220/movie/946e6e4182042c1e94feacb0dc93e7e3229652.jpg
             * nm : 萨利机长
             * dur : 96
             * time :
             * id : 342003
             */

            private int cnms;
            private int sn;
            private String showInfo;
            private boolean late;
            private String scm;
            private boolean imax;
            private int snum;
            private int preSale;
            private String vd;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            @SerializedName("3d")
            private boolean value3d;
            private int pn;
            private String showDate;
            private String src;
            private String sc;
            private String ver;
            private String rt;
            private String img;
            private String nm;
            private int dur;
            private String time;
            private int id;

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public boolean isValue3d() {
                return value3d;
            }

            public void setValue3d(boolean value3d) {
                this.value3d = value3d;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
