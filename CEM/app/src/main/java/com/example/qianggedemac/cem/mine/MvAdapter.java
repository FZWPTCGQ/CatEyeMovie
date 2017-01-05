package com.example.qianggedemac.cem.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.mine.mv.PlayerActivity;
import com.example.qianggedemac.cem.mine.mv.UnuseActivity;
import com.example.qianggedemac.cem.mine.mv.YueDanDetailActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 2017/1/3.
 */

public class MvAdapter extends RecyclerView.Adapter<MvAdapter.MVViewHolder>{

    private ArrayList<MvBean> datas;
    private Context mContext;

    private OnMvItemClick mOnMvItemClick;

    public void setOnMvItemClick(OnMvItemClick onMvItemClick) {
        mOnMvItemClick = onMvItemClick;
    }

    public MvAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(ArrayList<MvBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mv, parent, false);
        return new MVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MVViewHolder holder, final int position) {
        final MvBean mvBean = datas.get(position);
        holder.titleTv.setText(mvBean.getTitle());
        holder.descrTv.setText(mvBean.getDescription());
        Glide.with(mContext).load(mvBean.getPosterPic()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.posterIv);
        final int tag;
        String type = mvBean.getType();
        if ("ACTIVITY".equalsIgnoreCase(type)) {//打开页面 活动
            tag = 0;
            holder.typeIv.setImageResource(R.mipmap.home_page_activity);
        } else if ("VIDEO".equalsIgnoreCase(type)) {//首播，点击进去显示MV描述，相关MV
            tag = 1;
            holder.typeIv.setImageResource(R.mipmap.home_page_video);
        } else if ("WEEK_MAIN_STAR".equalsIgnoreCase(type)) {//(悦单)点击进去跟显示悦单详情一样
            tag = 2;
            holder.typeIv.setImageResource(R.mipmap.home_page_star);
        } else if ("PLAYLIST".equalsIgnoreCase(type)) {//(悦单)点击进去跟显示悦单详情一样
            tag = 3;
            holder.typeIv.setImageResource(R.mipmap.home_page_playlist);
        } else if ("AD".equalsIgnoreCase(type)) {
            tag = 4;
            holder.typeIv.setImageResource(R.mipmap.home_page_ad);
        } else if ("PROGRAM".equalsIgnoreCase(type)) {//跳到MV详情
            tag = 5;
            holder.typeIv.setImageResource(R.mipmap.home_page_program);
        } else if ("bulletin".equalsIgnoreCase(type)) {
            tag = 6;
            holder.typeIv.setImageResource(R.mipmap.home_page_bulletin);
        } else if ("fanart".equalsIgnoreCase(type)) {
            tag = 7;
            holder.typeIv.setImageResource(R.mipmap.home_page_fanart);
        } else if ("live".equalsIgnoreCase(type)) {
            tag = 8;
            holder.typeIv.setImageResource(R.mipmap.home_page_live);
        } else if ("LIVENEW".equalsIgnoreCase(type)|| ("LIVENEWLIST".equals(type))) {
            tag = 9;
            holder.typeIv.setImageResource(R.mipmap.home_page_live_new);
        } else if ("INVENTORY".equalsIgnoreCase(mvBean.getType())){//打开页面
            tag = 10;
            holder.typeIv.setImageResource(R.mipmap.home_page_project);
        }else {
            tag = -100;
            holder.typeIv.setImageResource(0);
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (tag){
                    case 0:
                    case 4:
                    case 10:
                        // 活动webview
                        intent.setClass(mContext, UnuseActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",mvBean.getUrl());
                        intent.putExtras(bundle);
                        break;
                    case 1:
                    case 5:
                    case 7:
                        // 首播 饭制
                       intent.setClass(mContext, YueDanDetailActivity.class);
                        intent.putExtra("id",mvBean.getId());
                        intent.putExtra("url",mvBean.getUrl());
                        intent.putExtra("post",mvBean.getPosterPic());
                        break;
                    case 2:
                    case 3:
                        //yuedan
                        intent.setClass(mContext, PlayerActivity.class);
                        intent.putExtra("id",mvBean.getId());
                        intent.putExtra("pos",position);
                        intent.putExtra("post",mvBean.getPosterPic());
//
                        break;
                    default:


                        return;
                }
              mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.size();
    }

    class MVViewHolder extends RecyclerView.ViewHolder{

        ImageView typeIv,posterIv;
        TextView titleTv,descrTv;
        CardView itemRoot;
        public MVViewHolder(View itemView) {
            super(itemView);
            itemRoot = (CardView) itemView.findViewById(R.id.item_root);
            posterIv = (ImageView) itemView.findViewById(R.id.iv_mv_post);
            typeIv = (ImageView) itemView.findViewById(R.id.iv_mv_type);
            titleTv = (TextView) itemView.findViewById(R.id.tv_mv_title);
            descrTv = (TextView) itemView.findViewById(R.id.tv_mv_descr);
        }
    }
}
