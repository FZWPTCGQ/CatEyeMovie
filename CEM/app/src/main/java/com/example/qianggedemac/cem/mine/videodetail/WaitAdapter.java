package com.example.qianggedemac.cem.mine.videodetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.mine.login.LoginContract;

/**
 * Created by qianggedemac on 17/1/6.
 * ViewPager的适配器
 */

public class WaitAdapter extends PagerAdapter {
    private MovieWaitBean mMovieWaitBean;

    public void setMovieWaitBean(MovieWaitBean movieWaitBean) {
        mMovieWaitBean = movieWaitBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return mMovieWaitBean == null ? 0 : mMovieWaitBean.getData().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_movie_wait_item,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.fragment_movie_wait_item_iv);
        ImageView imgIv = (ImageView)view.findViewById(R.id.fragment_movie_wait_Iv);
        TextView originTv = (TextView)view.findViewById(R.id.fragment_movie_wait_origin_tv);
        TextView nameTv = (TextView)view.findViewById(R.id.fragment_movie_wait_name_tv);
        Glide.with(container.getContext()).load(mMovieWaitBean.getData().get(position).getImg()).into(imgIv);
        nameTv.setText(mMovieWaitBean.getData().get(position).getMovieName());
        originTv.setText(mMovieWaitBean.getData().get(position).getOriginName());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(mMovieWaitBean.getData().get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                /**
                 * video:表示播放类型,如音频,视频
                 * * : 表示播放后缀名为所有可能
                 */
                intent.setDataAndType(uri,"video/*");
                container.getContext().startActivity(intent);
            }
        });
        container.addView(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      //  super.destroyItem(container, position, object);

    }
}
