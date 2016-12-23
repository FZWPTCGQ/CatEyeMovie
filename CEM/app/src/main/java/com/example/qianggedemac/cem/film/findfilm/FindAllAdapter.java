package com.example.qianggedemac.cem.film.findfilm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;

/**
 * Created by qianggedemac on 16/12/23.
 */

public class FindAllAdapter extends RecyclerView.Adapter<CommonVH> {
    private AllPrizeBean mMovieFindAllPrizeBean;
    private Context mContext;

    public FindAllAdapter(Context context) {
        mContext = context;
    }

    public void setMovieFindAllPrizeBean(AllPrizeBean movieFindAllPrizeBean) {
        mMovieFindAllPrizeBean = movieFindAllPrizeBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.movie_find_all_rv);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        holder.setText(R.id.movie_find_all_festival_name, mMovieFindAllPrizeBean.getData().get(position).getFestivalName());
        holder.setText(R.id.movie_find_all_date, mMovieFindAllPrizeBean.getData().get(position).getHeldDate().substring(5));
        holder.setText(R.id.movie_find_all_prize_name, mMovieFindAllPrizeBean.getData().get(position).getPrizeName());
        holder.setText(R.id.movie_find_all_movie_name, mMovieFindAllPrizeBean.getData().get(position).getMovieName());
        holder.setImage(R.id.movie_find_all_img, mMovieFindAllPrizeBean.getData().get(position).getImg().replace("w.h","165.220"));
    }

    @Override
    public int getItemCount() {
        return mMovieFindAllPrizeBean.getData() == null ? 0 : mMovieFindAllPrizeBean.getData().size();
    }

}
