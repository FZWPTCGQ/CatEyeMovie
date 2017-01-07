package com.example.qianggedemac.cem.film.city;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;

import java.util.ArrayList;

/**
 * Created by qianggedemac on 17/1/7.
 */
public class HistoryCityAdapter extends RecyclerView.Adapter<CommonVH>{
    private ArrayList<String> mStringArrayList;


    public void setStringArrayList(ArrayList<String> stringArrayList) {
        mStringArrayList = stringArrayList;
        notifyDataSetChanged();

    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_hot);
    }


    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        holder.setText(R.id.hot_content_tv,mStringArrayList.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return mStringArrayList == null ? 0 : mStringArrayList.size();
    }
}
