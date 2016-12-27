package com.example.qianggedemac.cem.cinema;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends Fragment {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private MapView mMapView;
    private AMap mAMap;
    /**
     * 声明mLocationOption对象
     */
    public AMapLocationClientOption mAMapLocationClientOption = null;
    private double lat;
    private double lon;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    lat = amapLocation.getLatitude();
                    lon = amapLocation.getLongitude();
                    Log.v("pcw","lat : "+lat+" lon : "+lon);
                    Log.v("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());

                    mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon),19));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat,lon));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.xe));
                    markerOptions.icon(bitmapDescriptor);
                    mAMap.addMarker(markerOptions);
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }

    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView)view.findViewById(R.id.map);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //必须写
        mMapView.onCreate(savedInstanceState);
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApp.getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //设置属性
        init();
    }
    /**
     * 初始化AMap对象
     */
    private void init() {
        if (mAMap == null){
            mAMap = mMapView.getMap();
        }
        /**
         * 设置地图
         */
        setUpMap();
    }
    private void setUpMap() {
        //初始化定位参数
        mAMapLocationClientOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式,Battery_saving是低功耗模式
        mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息(默认返回地址信息)
        mAMapLocationClientOption.setNeedAddress(true);
        //设置是否只定位一次
        mAMapLocationClientOption.setOnceLocation(false);
        //设置是否强制刷新WIFI,默认强制刷新
        mAMapLocationClientOption.setWifiActiveScan(true);
        //设置是否允许你模拟位置,默认为false
        mAMapLocationClientOption.setMockEnable(false);
        //设置定位间隔,单位为毫秒,默认为2000ms
        mAMapLocationClientOption.setInterval(2000);
        //给定位个护短对象设置参数
        mLocationClient.setLocationOption(mAMapLocationClientOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        /**
         * 停止定位
         */
        mLocationClient.stopLocation();
    }

    /**
     * 方法必须重写
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mMapView.onDestroy();
        mLocationClient.onDestroy();
    }
}
