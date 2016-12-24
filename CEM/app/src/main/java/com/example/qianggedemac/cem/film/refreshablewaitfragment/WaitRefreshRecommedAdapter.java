package com.example.qianggedemac.cem.film.refreshablewaitfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.film.wait.RecommedBean;

/**
 * Created by qianggedemac on 16/12/22.
 */

public class WaitRefreshRecommedAdapter extends RecyclerView.Adapter<WaitRefreshRecommedAdapter.WRViewHolder> {
    private Context mContext;
    private RecommedBean mRecommedBean;

    public WaitRefreshRecommedAdapter(Context context) {
        mContext = context;
    }

    public void setRecommedBean(RecommedBean recommedBean) {
        mRecommedBean = recommedBean;
        notifyDataSetChanged();
    }

    @Override
    public WaitRefreshRecommedAdapter.WRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wait_item_one_detail,parent,false);
        WRViewHolder wrViewHolder = new WRViewHolder(view);
        return wrViewHolder;
    }

    @Override
    public void onBindViewHolder(WaitRefreshRecommedAdapter.WRViewHolder holder, int position) {
       holder.mTextViewOriginName.setText(mRecommedBean.getData().get(position).getOriginName());
        holder.mTextViewMovieName.setText(mRecommedBean.getData().get(position).getMovieName());
        String url = mRecommedBean.getData().get(position).getImg();
        String newUrl = url.replace("/w.h/","/165.220/");
        Glide.with(mContext).load(newUrl).into(holder.mImageViewImg);
    }

    @Override
    public int getItemCount() {
        return mRecommedBean != null ?  mRecommedBean.getData().size() : 0;
    }

    public class WRViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewImg,mImageViewPlayIv;
        TextView mTextViewMovieName,mTextViewOriginName;
        public WRViewHolder(View itemView) {
            super(itemView);
            mImageViewImg = (ImageView)itemView.findViewById(R.id.wait_item_one_detail_img_iv);
            mImageViewPlayIv = (ImageView)itemView.findViewById(R.id.wait_item_one_detail_play_iv);
            mTextViewMovieName = (TextView)itemView.findViewById(R.id.wait_item_one_detail_movieName_tv);
            mTextViewOriginName = (TextView)itemView.findViewById(R.id.wait_item_one_detail_originName_tv);
        }
    }
}
