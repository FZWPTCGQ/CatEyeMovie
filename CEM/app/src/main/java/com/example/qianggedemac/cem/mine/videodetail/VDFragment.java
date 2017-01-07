package com.example.qianggedemac.cem.mine.videodetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.GenericTranscodeRequest;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;

import okhttp3.OkHttpClient;


public class VDFragment extends BaseFragment implements View.OnClickListener {


    private ImageView mImageViewBack;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private WishAdapter mWishAdapter;
    private WaitAdapter mWaitAdapter;
    //获取手机屏幕分辨率(px)也就是长宽
    private DisplayMetrics mDisplayMetrics;
    private LinearLayout mLinearLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_vd;
    }

    @Override
    protected void initView(View view) {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.fragment_movie_wait_item,null);
        mLinearLayout = (LinearLayout)view1.findViewById(R.id.fragment_movie_wait_ll);
        mImageViewBack = (ImageView)view.findViewById(R.id.vdfragment_back_iv);
        mViewPager = (ViewPager)view.findViewById(R.id.fragment_movie_wait_vp);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_movie_wait_wish_rv);
        mWishAdapter = new WishAdapter();
        mWaitAdapter = new WaitAdapter();
        mDisplayMetrics = new DisplayMetrics();
    }

    @Override
    protected void initData() {
        addListener();
        addNetData();
    }

    private void addNetData() {
        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_RECOMMENDATION, MovieWaitBean.class, new NetCallBack<MovieWaitBean>() {


            @Override
            public void onResponse(MovieWaitBean bean) {
                //是否限制子视图在他的范围内进行绘制,默认是true
              //  mLinearLayout.setClipChildren(false);
                mViewPager.setClipChildren(false);
                //得到窗口管理器获取默认显示获取指标,显示指标
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
                //获取屏幕宽度
                int screenWidth = mDisplayMetrics.widthPixels;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
                layoutParams.leftMargin = screenWidth/15;
                layoutParams.rightMargin = screenWidth/15;
                //设置ViewPager当前显示页面的宽度
                mViewPager.setLayoutParams(layoutParams);
                mWaitAdapter.setMovieWaitBean(bean);
                mViewPager.setAdapter(mWaitAdapter);
                //显示图片
                mViewPager.setPageTransformer(true,new PicTransFormer());
                //显示三张图片
                mViewPager.setOffscreenPageLimit(3);
                mViewPager.setPageMargin((int) getResources().getDimension(R.dimen.five));


            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_WISH, MovieWaitWishBean.class, new NetCallBack<MovieWaitWishBean>() {
            @Override
            public void onResponse(MovieWaitWishBean bean) {
                mWishAdapter.setMovieWaitWishBean(bean);
                mRecyclerView.setAdapter(mWishAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void addListener() {
        mImageViewBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vdfragment_back_iv:
                getActivity().onBackPressed();
                break;
        }
    }
}
