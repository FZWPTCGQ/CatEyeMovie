package com.example.qianggedemac.cem.film.city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;

/**
 * Created by qianggedemac on 17/1/7.
 */
public class HotCityAdapter extends RecyclerView.Adapter<CommonVH>{
    private String[] hotCity = new String[]{"上海","北京","广州","深圳","武汉","天津","西安","南京","杭州","成都","重庆"};
    private OnSelcetHotCityListener mOnSelcetHotCityListener;

    public HotCityAdapter setOnSelcetHotCityListener(OnSelcetHotCityListener onSelcetHotCityListener) {
        mOnSelcetHotCityListener = onSelcetHotCityListener;
        return this;
    }

    public HotCityAdapter() {


    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_hot_city);
    }


    @Override
    public void onBindViewHolder(CommonVH holder,final int position) {
        holder.setText(R.id.item_hot_city_tv, hotCity[position]);
        holder.setViewClick(R.id.item_hot_city_tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnSelcetHotCityListener.onSelectHotCity(hotCity[position]);
                //  删除之后 插入数据
                DBTools.getInstance().deleteOneHistoryInfo(HistoryCityBean.class, hotCity[position]);
                DBTools.getInstance().inserthistoryInfo(new HistoryCityBean(hotCity[position]));

            }
        });

    }


    @Override
    public int getItemCount() {
        return hotCity.length;
    }

    interface  OnSelcetHotCityListener{
        void onSelectHotCity(String cityName);
    }
}
