package com.example.qianggedemac.cem.mine.videodetail;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 17/1/6.
 */

public class WishAdapter extends RecyclerView.Adapter<CommonVH>{
    private MovieWaitWishBean mMovieWaitWishBean;

    public void setMovieWaitWishBean(MovieWaitWishBean movieWaitWishBean) {
        mMovieWaitWishBean = movieWaitWishBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.fragment_movie_wait_wish_item);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
       if (mMovieWaitWishBean.getData().getComing().get(position).getComingTitle().contains("2017年")){
           String str = mMovieWaitWishBean.getData().getComing().get(position).getComingTitle().substring(2,10);
           holder.setText(R.id.fragment_movie_wait_wish_date_tv,str);
       }else{
           String str = mMovieWaitWishBean.getData().getComing().get(position).getComingTitle().substring(0,5);
           holder.setText(R.id.fragment_movie_wait_wish_date_tv,str);
       }
        String imageUrl = mMovieWaitWishBean.getData().getComing().get(position).getImg().replace("/w.h/","/165.220/");
        ImageView imageView = holder.getView(R.id.fragment_movie_wait_wish_iv);
        Glide.with(MyApp.getContext()).load(imageUrl).into(imageView);
        if (mMovieWaitWishBean.getData().getComing().get(position).getNm().length() > 5){
            String name = mMovieWaitWishBean.getData().getComing().get(position).getNm().substring(0,5) + "...";
            holder.setText(R.id.fragment_movie_wait_wish_name_tv, name);
        }else{
            holder.setText(R.id.fragment_movie_wait_wish_name_tv,mMovieWaitWishBean.getData().getComing().get(position).getNm());
        }
        holder.setText(R.id.fragment_movie_wait_wish_count_tv,mMovieWaitWishBean.getData().getComing().get(position).getWish() + "人想看");

    }

    @Override
    public int getItemCount() {
        return mMovieWaitWishBean == null ? 0 : mMovieWaitWishBean.getData().getComing().size();
    }
}
