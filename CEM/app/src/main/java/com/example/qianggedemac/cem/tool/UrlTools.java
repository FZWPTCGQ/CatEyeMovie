package com.example.qianggedemac.cem.tool;

/**
 * Created by qianggedemac on 16/12/19.
 */

public class UrlTools {
    //欢迎页
    public static final String WELCOME_URL = "http://api.maoyan.com/apptools/launchScreen.json?city_id=65&poster_type=1&utm_campaign=AmovieBmovieCD100&movieBundleVersion=7601&utm_source=xiaomi&utm_medium=android&utm_term=7.6.0&utm_content=860887036129012&ci=65&net=255";
    //轮播图
    public static final String TURN_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=65&category=11&version=7.6.0&new=0&app=movie&clienttp=android&uuid=89B38BD34B3C4B2B83BA7D389BEB138BD05D8DE7D11FD20C6BAAC72F1870761D&devid=860887036129012&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1";
    //电影 -> 热映 -> 列表 -> 详情
    public static final String MOVIE_HOT_LIST_DETAIL = "http://m.maoyan.com/movie/";
    //首页热映页面listView
    public static final String HOT_URL = "http://api.maoyan.com/mmdb/movie/v3/list/hot.json?ci=65&limit=12&token=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7601&utm_source=vivo_dy&utm_medium=android&utm_term=7.6.0&utm_content=861469039886796&net=255";
    //电影 -> 热映 -> 列表 -> 详情 -> 前半部
    public static final String MOVIE_HOT_LIST_DETAIL_BEFORE = "http://m.maoyan.com/movie/";
    //电影 -> 热映 -> 列表 -> 详情 -> 后半部
    public static final String MOVIE_HOT_LIST_DETAIL_AFTER = "?_v_=yes";
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
    public static final String MOVIE_WAIT_LISTVIEW = "http://api.maoyan.com/mmdb/movie/list/info.json?ci=65&headline=1&movieIds=345970,1198942,1187526,247913,1188324,345106,627570,344762,246276,343742,1190357,342613";
    //拼接后的URL
    public static final String MOVIE_WAIT_NEW_URL = "http://api.maoyan.com/mmdb/movie/list/info.json?ci=65&headline=1&movieIds=";
    //刷新
    public static final String MOVIE_WAIT_LISTVIEW_MORE = "http://api.maoyan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=65&limit=12&token=&utm_campaign=AmovieBmovieCD-1";
    //电影 ->搜索 ->找片 -> 类型/地区/年代
    public static final String MOVIE_FIND_TYPE_WHERE_WHEN = "http://api.maoyan.com/mmdb/search/movie/tag/types.json?";
    //电影 ->搜索 ->找片 -> 热映口碑等四个
    public static final String MOVIE_FIND_CENTER = "http://api.maoyan.com/mmdb/movieboard/fixedboard/v1/hot/list.json?";
    //电影 ->搜索 ->找片 -> 全球电影奖项
    public static final String MOVIE_FIND_ALL_PRIZE = "http://api.maoyan.com/mmdb/movie/winning/film/2016-11-21/list.json?";
    //电影 ->搜索 ->找片 ->全部电影奖项
    public static final String MOVIE_FIND_ALL_PRIZE_BODY = "http://api.maoyan.com/mmdb/movie/region/festival/list.json?";
    // 热门搜索
    public static final String HOT_SEARCH = "http://api.maoyan.com/mmdb/search/movie/hotmovie/list.json?tp=4&keyword=&limit=0&offset=0&cityId=";
    // 搜索框
    public static final String SEARCH_KEY_BEFORE = "http://api.maoyan.com/mmdb/search/integrated/keyword/list.json?almtype=1&keyword=";
    public static final String SEARCH_KEY_AFTER = "&stype=-1&refer=1&iscorrected=false&limit=10&offset=0&token=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7701&utm_source=xiaomi&utm_medium=android&utm_term=7.7.0&utm_content=860887036129012&ci=65&net=255&dModel=Redmi%20Note%203&uuid=89B38BD34B3C4B2B83BA7D389BEB138BD05D8DE7D11FD20C6BAAC72F1870761D&lat=38.883424&lng=121.544714&__reqTraceID=7654128655162426328&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1482743413823&__skua=32bcf146c756ecefe7535b95816908e3&__skno=c8d90383-6a95-4f6d-a152-6843c4561c63&__skcy=kfQthRGKc7jDUBTvCzquaanxBOs%3D";

    //搜索页面刷新
    public static final String SEARCH_DETAIL_CENTER = "&stype=-1&refer=1&iscorrected=false&limit=10&offset=";
    public static final String SEARCH_DETAIL_AFTER = "&token=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7701&utm_source=xiaomi&utm_medium=android&utm_term=7.7.0&utm_content=860887036129012&ci=65&net=255&dModel=Redmi%20Note%203&uuid=89B38BD34B3C4B2B83BA7D389BEB138BD05D8DE7D11FD20C6BAAC72F1870761D&lat=38.883424&lng=121.544714&__reqTraceID=7654128655162426328&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1482743413823&__skua=32bcf146c756ecefe7535b95816908e3&__skno=c8d90383-6a95-4f6d-a152-6843c4561c63&__skcy=kfQthRGKc7jDUBTvCzquaanxBOs%3D";


<<<<<<< HEAD


=======
>>>>>>> 1d0a78142c0f0036a8fccf395d8c081dee94a663
    //发现 -> 今天 -> 点击详情
    public static final String FIND_TODAY_DETAIL = "http://m.maoyan.com/information/";
    //发现 -> 今天 -> 点击详情(另一种)
    public static final String FIND_TODAY_DETAIL_ELSE = "http://m.maoyan.com/topic/";
    //发现 ->今日TOP10
    public static final String FIND_TOP10 = "http://m.maoyan.com/information?_v_=yes&groupId=1481354&pageType=1&title=今日TOP10";
    //发现 ->影视快讯
    public static final String FIND_FAST_MSG = "http://m.maoyan.com/information?_v_=yes";
    //发现 ->周边商城
    public static final String FIND_STORE = "http://http://m.maoyan.com/store?_v_=yes";
    //发现 ->实时票房
    public static final String FIND_NOW = "http://piaofang.maoyan.com/?f=android&userid=-1";

}
