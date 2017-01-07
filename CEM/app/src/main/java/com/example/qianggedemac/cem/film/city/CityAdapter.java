package com.example.qianggedemac.cem.film.city;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.ResponseCallBack;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by qianggedemac on 17/1/6.
 */

public class CityAdapter extends RecyclerView.Adapter<CommonVH> {
    private List<CityBean.CtsBean> mDatas;
    private int[] mInflater = {R.layout.location_city,
            R.layout.history_scan,
            R.layout.hot_city,
            R.layout.item_city};
    //定位后的维度
    private double mLatitude;
    //定位后的精度
    private double mLongitude;
    private String mCity;
    private String mString;
    private CityFragment.OnSelectCity mOnSelectCity;

    public void setOnSelectCity(CityFragment.OnSelectCity onSelectCity) {
        mOnSelectCity = onSelectCity;
    }

    public void setDatas(List<CityBean.CtsBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }



    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,mInflater[viewType]);
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 3){
            return 3;
        }else{
            return position;
        }
    }

    @Override
    public void onBindViewHolder(final CommonVH holder, final int position) {
     int type = getItemViewType(position);
        switch (type){
            //定位
            case 0:
                holder.setViewClick(R.id.postion_city_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /**
                         *  Location 在 Android 开发中还是经常用到的，比如 通过经
                         *  纬度获取天气，根据 Location 获取所在地区详细 Address （比如 Google Map 开发).等。而
                         *  在 Android 中通过 LocationManager 来获取 Location .通常获取 Location 有 GPS 获取，WIFI
                         *  获取。
                         */
                        Location cityLocation = getLocation();
                        if (cityLocation != null){
                            //获取定位后的纬度
                            mLatitude = cityLocation.getLatitude();
                            //获取定位后的精度
                            mLongitude = cityLocation.getLongitude();
                            HttpUtil.getLocationByLongitudeAndLatitude(mLongitude, mLatitude, new NetCallBack<LocationBean>() {
                                @Override
                                public void onError(Exception e) {

                                }

                                @Override
                                public void onResponse(LocationBean locationBean) {
                                   mCity = locationBean.getResult().getCity().replace("市","");
                                    holder.setText(R.id.postion_city_tv,mCity);
                                    getCityId();
                                    mOnSelectCity.selectCityName(mCity,getCityId());
                                }
                            });

                        }

                    }
                });
                break;
            //历史
            case 1:
                GridLayoutManager historyManager = new GridLayoutManager(MyApp.getContext(),3);
                RecyclerView historyCityRv = (RecyclerView)holder.getItemView().findViewById(R.id.city_scan_history_rv);
                final HistoryCityAdapter historyCityAdapter = new HistoryCityAdapter();
                historyCityRv.setAdapter(historyCityAdapter);
                historyCityRv.setLayoutManager(historyManager);
                DBTools.getInstance().queryHistoryInfo(HistoryCityBean.class, new DBTools.OnQueryHistoryInfo<HistoryCityBean>() {
                    @Override
                    public void OnQuery(ArrayList<HistoryCityBean> query) {
                        ArrayList<String> arrayList = new ArrayList<String>();
                        if (query.size() != 0){
                            Collections.reverse(query);
                            /**
                             * 最多添加三个
                             */
                            for (int i = 0; i < (query.size() <= 3 ? query.size() : 3); i++) {
                                arrayList.add(query.get(i).getContent());
                            }
                            historyCityAdapter.setStringArrayList(arrayList);
                        }
                    }
                });
                break;
            case 2:
                //通过封装的getItemView方法找到控件
                RecyclerView hotCityRv = (RecyclerView)holder.getItemView().findViewById(R.id.hot_city_rv);
                HotCityAdapter hotCityAdapter = new HotCityAdapter();
                GridLayoutManager hotManager = new GridLayoutManager(MyApp.getContext(),3);
                hotCityRv.setAdapter(hotCityAdapter);
                hotCityRv.setLayoutManager(hotManager);
                hotCityAdapter.setOnSelcetHotCityListener(new HotCityAdapter.OnSelcetHotCityListener() {
                    @Override
                    public void onSelectHotCity(String cityName) {
                        getCityId(cityName);
                        mOnSelectCity.selectCityName(cityName,getCityId(cityName));
                    }
                });
                break;
            case 3:
                holder.setText(R.id.tvCity, mDatas.get(position).getNm());
                holder.setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyApp.getContext(), "mDatas.get(position).getId():" + mDatas.get(position).getId(), Toast.LENGTH_SHORT).show();
                        mOnSelectCity.selectCityName(mDatas.get(position).getNm(), mDatas.get(position).getId() + "");
                        //  删除之后 插入数据
                        DBTools.getInstance().deleteOneHistoryInfo(HistoryCityBean.class, mDatas.get(position).getNm());
                        DBTools.getInstance().inserthistoryInfo(new HistoryCityBean(mDatas.get(position).getNm()));

                    }
                });

                break;
        }
    }

    private String getCityId(){
        String cityId = "-1";
        for (CityBean.CtsBean data : mDatas) {
            if (mCity.equals(data.getNm())){
                cityId = String.valueOf(data.getId());
            }
        }
        return cityId;
    }

    private String getCityId(String cityName){
        String cityId = "-1";
        for (CityBean.CtsBean data : mDatas) {
            if (cityName.equals(data.getNm())){
                cityId = String.valueOf(data.getId());


            }
        }
        return cityId;
    }

    /**
     * 获取经纬度
     * @return
     */
    public Location getLocation(){
        String mProvider;
        Location mLocation;

        // 权限检查, 自动添加的
        if (ActivityCompat.checkSelfPermission(MyApp.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApp.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        LocationManager locationManager = (LocationManager) MyApp.getContext().getSystemService(Context.LOCATION_SERVICE);
        mProvider = LocationManager.NETWORK_PROVIDER;
        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);

        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {

            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(MyApp.getContext(), "No location provider to use", Toast.LENGTH_SHORT).show();
        }


        mLocation = locationManager.getLastKnownLocation(mProvider);


        return mLocation;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}
