package com.example.qianggedemac.cem.film.hot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

/**
 * Created by qianggedemac on 16/12/21.
 */

public class HotRefreshAdapter extends BaseAdapter {
    private Context mContext;
    private HotRefreshBean mHotRefreshBean;


    public HotRefreshAdapter(Context context) {
        mContext = context;

    }
    public void clean(){
        mHotRefreshBean.getData().getMovies().clear();
    }

    public void setHotRefreshBean(HotRefreshBean hotRefreshBean) {
        this.mHotRefreshBean = hotRefreshBean;
      //  this.mHotRefreshBean.getData().getMovies().addAll(hotRefreshBean.getData().getMovies());
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mHotRefreshBean.getData().getMovies().size() > 0 && mHotRefreshBean.getData().getMovies() != null ? mHotRefreshBean.getData().getMovies().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mHotRefreshBean.getData().getMovies().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HotViewHolder hotViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.hot_fragment_adapter_two_item,viewGroup,false);
            hotViewHolder = new HotViewHolder(view);
            view.setTag(hotViewHolder);
        }else{
            hotViewHolder = (HotViewHolder) view.getTag();
        }

        hotViewHolder.nmTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i).getNm());
        String imgUrl = mHotRefreshBean.getData().getMovies().get(i).getImg();
        Glide.with(mContext).load(imgUrl).into(hotViewHolder.imgIvTwo);
        String verIv = mHotRefreshBean.getData().getMovies().get(i).getVer();
        if (verIv.contains("3D")){
            hotViewHolder.verIvTwo.setImageResource(R.mipmap.wo);
        }else if (verIv.contains("3D") && verIv.contains("IMAX")){
            hotViewHolder.verIvTwo.setImageResource(R.mipmap.main_movie_hot_3d_imax);
        }else if (verIv.contains("2D") && verIv.contains("IMAX")){
            hotViewHolder.verIvTwo.setImageResource(R.mipmap.main_movie_hot_2d_imax);
        }
        int boxTv = mHotRefreshBean.getData().getMovies().get(i).getPreSale();
        Log.d("啊啊啊啊", boxTv + "");
        if (boxTv != 1){
            hotViewHolder.scTvTwo.setText("观众" + mHotRefreshBean.getData().getMovies().get(i).getSc());

        }else{
            hotViewHolder.scTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i).getWish() + "人想看");
            //Log.d("啊啊啊啊", "mHotFragmentListViewBean.getData().getHot().get(i).getWish():" + mHotFragmentListViewBean.getData().getHot().get(i).getWish());
        }
        hotViewHolder.scmTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i).getScm());
        hotViewHolder.showInFoTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i).getShowInfo());
        if (boxTv != 1){

            hotViewHolder.gPTv.setText("购票");
            Log.e("AAAA", "__---");
        }else{
            hotViewHolder.gPTv.setText("预售");
            Log.e("AAAA", "__---" + "额");
        }
        /**
         * 点击事件
         */
        hotViewHolder.playIvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "播放被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        hotViewHolder.gPTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "购票被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public class HotViewHolder{
        ImageView imgIvTwo,verIvTwo,playIvTwo;
        TextView nmTvTwo,scTvTwo,proscoreTvTwo,scmTvTwo,showInFoTvTwo,gPTv;
        public HotViewHolder(View view){
            playIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_detail_playIv);
            imgIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_detail_img);
            verIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_ver_threeD_iv);
            nmTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_nm_tv);
            scTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_guanzhong_tv);
            proscoreTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_zhuanye_tv);
            scmTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_three_ll_scm_tv);
            showInFoTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_four_ll_showInfo_tv);
            gPTv = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_goupiao_tv);
        }

    }
}
