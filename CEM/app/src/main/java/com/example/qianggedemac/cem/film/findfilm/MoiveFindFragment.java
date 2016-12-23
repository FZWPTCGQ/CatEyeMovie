package com.example.qianggedemac.cem.film.findfilm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoiveFindFragment extends BaseFragment{


    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private MoiveFindAdapter mMoiveFindAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_find_film;
    }

    @Override
    protected void initView(View view) {
    mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)view.findViewById(R.id.fragment_find_film_pullLoadMoreRecyclerView);
        mMoiveFindAdapter = new MoiveFindAdapter(mContext);
        mPullLoadMoreRecyclerView.setAdapter(mMoiveFindAdapter);
        //设置PullLoadMoreRecyclerView是线性方式
        mPullLoadMoreRecyclerView.setLinearLayout();
        //设置网格布局
      //  mPullLoadMoreRecyclerView.setGridLayout(2);//参数为列数
        //设置瀑布流
        // mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数

    }

    @Override
    protected void initData() {
        OkHttpManager.getInstance().get(UrlTools.MOVIE_FIND_TYPE_WHERE_WHEN, FindTypeBean.class, new NetCallBack<FindTypeBean>() {
            @Override
            public void onResponse(FindTypeBean bean) {
                mMoiveFindAdapter.setFindTypeBean(bean);

            }

            @Override
            public void onError(Exception e) {

            }
        });
        OkHttpManager.getInstance().get(UrlTools.MOVIE_FIND_CENTER, ClassifyBean.class, new NetCallBack<ClassifyBean>() {
            @Override
            public void onResponse(ClassifyBean bean) {
                mMoiveFindAdapter.setClassifyBean(bean);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        //调用下拉刷新和加载的方法
//        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//                //刷新结束的方法
//                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//            }
//
//            @Override
//            public void onLoadMore() {
//                //刷新结束的方法
//                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//            }
//        });

//        //不需要下拉刷新
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
//        //不需要上拉帅新的方法
        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //设置上拉刷新文字
       // mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置下拉刷新颜色
       // mPullLoadMoreRecyclerView.setColorSchemeResources();
        //快速Top
   //     mPullLoadMoreRecyclerView.scrollToTop();
    }

}
