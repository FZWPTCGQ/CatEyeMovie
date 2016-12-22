package com.example.qianggedemac.cem.film.hot;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {


    private PullToRefreshListView mListView;
    private Banner mBanner;
    private HotFragmentAdapter mHotFragmentAdapter;
    private int offset = 0;
    private String mCityId = "65";
    private HotRefreshAdapter mHotRefreshAdapter;
    private PullToRefreshListView mPullToRefreshListView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {

        mListView = (PullToRefreshListView) view.findViewById(R.id.fragment_hot_lv);
        mHotFragmentAdapter = new HotFragmentAdapter(mContext);
        mHotRefreshAdapter = new HotRefreshAdapter(mContext);

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
        /**
         * 刷新数据方法
         */
        refreshMethod();


    }

    private void refreshMethod() {
        offset += 10;
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Log.d("sdahfls", "offset:" + offset);
//              getMovieHotList("65", offset, new NetCallBack<HotRefreshBean>() {
//                  @Override
//                  public void onResponse(HotRefreshBean bean) {
//                      mHotRefreshAdapter.clean();
//                      //mHotFragmentAdapter.setHotFragmentListViewBean(bean);
//                      mHotRefreshAdapter.setHotRefreshBean(bean);
//                      mListView.setAdapter(mHotRefreshAdapter);
//
//                      mHotRefreshAdapter.notifyDataSetChanged();
//                      mListView.onRefreshComplete();
//                  }
//
//                  @Override
//                  public void onError(Exception e) {
//
//                  }
//              });


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                getMovieHotList("65", offset, new NetCallBack<HotRefreshBean>() {
                    @Override
                    public void onResponse(HotRefreshBean bean) {
                        //mHotFragmentAdapter.setHotFragmentListViewBean(bean);

                        mHotFragmentAdapter.setHotRefreshBean(bean);
                        mListView.setAdapter(mHotFragmentAdapter);
                        mHotFragmentAdapter.notifyDataSetChanged();
                        mListView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });

    }

    public static void getMovieHotList(String cityId,int offset,NetCallBack<HotRefreshBean> netCallBack){


        String url = UrlTools.MOVIE_HOT_LIST + offset + UrlTools.MOVIE_HOT_CITY + cityId;
        Log.d("sdahfls", url);
        OkHttpManager.getInstance().get(url,HotRefreshBean.class,netCallBack);
    }

    private void parseListView() {
        OkHttpManager.getInstance().get(UrlTools.HOT_URL, HotFragmentListViewBean.class, new NetCallBack<HotFragmentListViewBean>() {
            @Override
            public void onResponse(HotFragmentListViewBean bean) {
                Log.d("HotFragment", "bean.getData().getHot().size():" + bean.getData().getHot().get(0).getBoxInfo());
                mHotFragmentAdapter.setHotFragmentListViewBean(bean);
                mListView.setAdapter(mHotFragmentAdapter);
            }

            @Override
            public void onError(Exception e) {
                Log.d("asjfhak", e.getMessage());
            }
        });
        getMovieHotList("65", 0, new NetCallBack<HotRefreshBean>() {
            @Override
            public void onResponse(HotRefreshBean bean) {
                mHotRefreshAdapter.setHotRefreshBean(bean);
                mListView.setAdapter(mHotRefreshAdapter);
            }

            @Override
            public void onError(Exception e) {

            }
        });


       View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_hot_header_view,null);
        mBanner = (Banner)view.findViewById(R.id.banner);
        //因为pulltoRreshListView不是继承ListView的,通过getRereshableView()来获取listView的实例,并调用addHeaderView()方法来添加头布局
        mListView.getRefreshableView().addHeaderView(view);
    }

    private void parseBanner() {
        OkHttpManager.getInstance().get(UrlTools.TURN_URL, HotFragmentTurnBean.class, new NetCallBack<HotFragmentTurnBean>() {
            @Override
            public void onResponse(HotFragmentTurnBean bean) {
                Log.d("HotFragment", "bean.getData().size():" + bean.getData().size());
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
    }

    /**
     * 让加载数据有个缓冲时间
     */
    public class PutDataTask extends AsyncTask<Integer,Void,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(1500);
                offset+=10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return offset;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            offset = integer;
        }
    }


}
