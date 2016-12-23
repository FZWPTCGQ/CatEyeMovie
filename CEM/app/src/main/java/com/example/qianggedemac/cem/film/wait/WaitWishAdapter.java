package com.example.qianggedemac.cem.film.wait;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;

/**
 * Created by qianggedemac on 16/12/22.
 */

public class WaitWishAdapter extends RecyclerView.Adapter<WaitWishAdapter.WWViewHolder> {
    private Context mContext;
    private WishBean mWishBean;

    public WaitWishAdapter(Context context) {
        mContext = context;
    }

    public void setWishBean(WishBean wishBean) {
        mWishBean = wishBean;
        notifyDataSetChanged();
    }

    @Override
    public WaitWishAdapter.WWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wait_item_two_detail,parent,false);
        WWViewHolder wwViewHolder = new WWViewHolder(view);
        return wwViewHolder;
    }

    @Override
    public void onBindViewHolder(WaitWishAdapter.WWViewHolder holder, int position) {
       holder.mTextViewComingTilte.setText(mWishBean.getData().getComing().get(position).getComingTitle());
        holder.mTextViewNm.setText(mWishBean.getData().getComing().get(position).getNm());
        holder.mTextViewWish.setText(mWishBean.getData().getComing().get(position).getWish() + "人想看");
        String url = mWishBean.getData().getComing().get(position).getImg();
        String newUrl = url.replace("/w.h/","/165.220/");
        Glide.with(mContext).load(newUrl).into(holder.mImageViewImg);
    }

    @Override
    public int getItemCount() {
        return mWishBean != null ? mWishBean.getData().getComing().size() : 0;
    }

    public class WWViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewComingTilte,mTextViewNm,mTextViewWish;
        ImageView mImageViewImg,mImageViewLike;
        public WWViewHolder(View itemView) {
            super(itemView);
            mImageViewImg = (ImageView)itemView.findViewById(R.id.wait_item_two_detail_img_iv);
            mImageViewLike = (ImageView)itemView.findViewById(R.id.wait_item_two_detail_like_iv);
            mTextViewComingTilte = (TextView)itemView.findViewById(R.id.wait_item_two_detail_comingTitle_tv);
            mTextViewNm = (TextView)itemView.findViewById(R.id.wait_item_two_detail_nm_tv);
            mTextViewWish = (TextView)itemView.findViewById(R.id.wait_item_two_detail_wish_tv);
        }
    }
}
