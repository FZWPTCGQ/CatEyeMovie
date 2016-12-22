package com.example.qianggedemac.cem.tool;

/**
 * Created by qianggedemac on 16/12/19.
 */

public class UrlTools {
    //欢迎页
    public static final String WELCOME_URL = "http://api.maoyan.com/apptools/launchScreen.json?city_id=65&poster_type=1&utm_campaign=AmovieBmovieCD100&movieBundleVersion=7601&utm_source=xiaomi&utm_medium=android&utm_term=7.6.0&utm_content=860887036129012&ci=65&net=255";
    //轮播图
    public static final String TURN_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=65&category=11&version=7.6.0&new=0&app=movie&clienttp=android&uuid=89B38BD34B3C4B2B83BA7D389BEB138BD05D8DE7D11FD20C6BAAC72F1870761D&devid=860887036129012&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1";
    //首页热映页面listView
    public static final String HOT_URL = "http://api.maoyan.com/mmdb/movie/v3/list/hot.json?ci=65&limit=12&token=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7601&utm_source=vivo_dy&utm_medium=android&utm_term=7.6.0&utm_content=861469039886796&net=255";
    // 发现 -> 今天
    public static final String FIND_TODAY = "http://api.maoyan.com/sns/v5/feed.json?";
    //发现 -> 上面四个图标
    public static final String FIND_TOP = "http://api.maoyan.com/sns/v2/buttons.json?utm_term=7.5.0&utm_medium=android";
    //热映界面刷新网址
    public static final String MOVIE_HOT_LIST = "http://m.maoyan.com/movie/list.json?type=hot&limit=12&offset=";
    public static final String MOVIE_HOT_CITY = "&ci=";
    //待映网址
    //电影 -> 待映 -> 预告片推荐
    public static final String MOVIE_WAIT_RECOMMENDATION = "http://api.maoyan.com/mmdb/movie/lp/list.json";
    //电影 -> 待映 -> 近期最受期待
    public static final String MOVIE_WAIT_WISH = "http://api.maoyan.com/mmdb/movie/v1/list/wish/order/coming.json?offset=0&limit=50&ci=65";
    //下面的lisView
    public static final String MOVIE_WAIT_LISTVIEW = "http://api.maoyan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=65&limit=12&token=&utm_campaign=AmovieBmovieCD-1";




}
