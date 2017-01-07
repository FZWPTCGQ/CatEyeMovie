package com.example.qianggedemac.cem.film.city;

import com.example.qianggedemac.cem.tool.ResponseCallBack;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;

/**
 * Created by qianggedemac on 17/1/7.
 */

public class HttpUtil {

    //根据经纬度获取城市的名字
    public static final String LOCATION_LONGITUDE_LATITUDE = "http://api.avatardata.cn/CoordAddress/Lookup?key=b43534b71d1c4e18b935d548949205ba&";


    /**
     * 根据经纬度进行定位
     */

    public static void getLocationByLongitudeAndLatitude(double longitude,double latitude,NetCallBack<LocationBean> responseCallBack){
        String url = LOCATION_LONGITUDE_LATITUDE + "lat=" + latitude + "&lon=" + longitude;
        OkHttpManager.getInstance().get(url,LocationBean.class,responseCallBack);
    }

}
