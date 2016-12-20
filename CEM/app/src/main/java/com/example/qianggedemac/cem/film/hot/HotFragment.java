package com.example.qianggedemac.cem.film.hot;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {


    private ListView mListView;
    private Banner mBanner;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {

        mListView = (ListView)view.findViewById(R.id.fragment_hot_lv);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("哈哈");
        }
        mArrayAdapter = new ArrayAdapter(mContext,R.layout.support_simple_spinner_dropdown_item,list);

    }

    @Override
    protected void initData() {

        /**
         * listView数据
         */
        parseListView();
        /**
         * 轮播图数据
         */
        parseBanner();
       mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(AbsListView absListView, int i) {

           }

           @Override
           public void onScroll(AbsListView absListView, int i, int i1, int i2) {

           }
       });

    }

    private void parseListView() {
        mListView.setAdapter(mArrayAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_hot_header_view,null);
        mBanner = (Banner)view.findViewById(R.id.banner);
        mListView.addHeaderView(view);
    }

    private void parseBanner() {
        OkHttpManager.getInstance().get(UrlTools.TURN_URL, HotFragmentBean.class, new NetCallBack<HotFragmentBean>() {
            @Override
            public void onResponse(HotFragmentBean bean) {
                ArrayList<String> image = new ArrayList<>();
                for (int i = 0; i < bean.getData().size(); i++) {
                    image.add(bean.getData().get(i).getImgUrl());
                }

                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                // 设置图片加载器
                mBanner.setImageLoader(new GrideImageLoader());
                // 设置图片集合
                mBanner.setImages(image);
                // 设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                mBanner.setBannerAnimation(Transformer.DepthPage);
                // 设置自动轮播 默认为true
                mBanner.isAutoPlay(true);
                // 设置轮播时间
                mBanner.setDelayTime(2000);
                // 设置指示器位置 (当banner模式中有指示器时)
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                // banner设置方法全部调用完毕时最后调用
                mBanner.start();

            }

            @Override
            public void onError(Exception e) {

            }
        });
//        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//        StringRequest stringRequest = new StringRequest(UrlTools.TURN_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                HotFragmentBean hotFragmentBean = gson.fromJson(response,HotFragmentBean.class);
//                ArrayList<String> image = new ArrayList<>();
//                for (int i = 0; i < hotFragmentBean.getData().size(); i++) {
//                    image.add(hotFragmentBean.getData().get(i).getImgUrl());
//                }
//
//                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//                // 设置图片加载器
//                mBanner.setImageLoader(new GrideImageLoader());
//                // 设置图片集合
//                mBanner.setImages(image);
//                // 设置banner动画效果
//                mBanner.setBannerAnimation(Transformer.DepthPage);
//                mBanner.setBannerAnimation(Transformer.DepthPage);
//                // 设置自动轮播 默认为true
//                mBanner.isAutoPlay(true);
//                // 设置轮播时间
//                mBanner.setDelayTime(2000);
//                // 设置指示器位置 (当banner模式中有指示器时)
//                mBanner.setIndicatorGravity(BannerConfig.CENTER);
//                // banner设置方法全部调用完毕时最后调用
//                mBanner.start();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
    }

}
