package com.example.qianggedemac.cem.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.find.CollectionBean;
import com.example.qianggedemac.cem.find.FindDetailActivity;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

import java.util.ArrayList;

/**
 * Created by qianggedemac on 17/1/3.
 */

public class CollectDetailAdapter extends RecyclerView.Adapter<CommonVH> {
    private ArrayList<CollectionBean> mCollectionBeen;
    private Context mContext;

    public CollectDetailAdapter(Context context) {
        mContext = context;
    }

    public void setCollectionBeen(ArrayList<CollectionBean> collectionBeen) {
        mCollectionBeen = collectionBeen;
        notifyDataSetChanged();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //只有一种布局,不用写一个布局的数组了,直接找
        return CommonVH.getViewHolder(parent,R.layout.item_collect);
    }

    @Override
    public void onBindViewHolder(final CommonVH holder, final int position) {
        holder.setText(R.id.item_collect_nick_name,mCollectionBeen.get(position).getNickName());
        holder.setText(R.id.item_collect_title,mCollectionBeen.get(position).getTitle());
        holder.setImage(R.id.item_collect_img,mCollectionBeen.get(position).getUrlImg());
        holder.setText(R.id.item_collect_time,mCollectionBeen.get(position).getTime());
        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApp.getContext(), FindDetailActivity.class);
                intent.putExtra("targetID", mCollectionBeen.get(position).getTargetId());
                intent.putExtra("feedType", mCollectionBeen.get(position).getFeedType());
                intent.putExtra("nickName", mCollectionBeen.get(position).getNickName());
                intent.putExtra("urlImg", mCollectionBeen.get(position).getUrlImg());
                intent.putExtra("Title", mCollectionBeen.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCollectionBeen == null ? 0 : mCollectionBeen.size();
    }
}
