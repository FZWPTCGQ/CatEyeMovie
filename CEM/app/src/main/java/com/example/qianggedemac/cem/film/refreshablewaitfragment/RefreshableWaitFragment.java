package com.example.qianggedemac.cem.film.refreshablewaitfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.film.wait.RecommedBean;
import com.example.qianggedemac.cem.film.wait.WaitRecommedAdapter;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefreshableWaitFragment extends BaseFragment {


    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_refreshable_wait;
    }

    @Override
    protected void initView(View view) {
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)view.findViewById(R.id.refreshable_wait_fragment_rv);

    }

    @Override
    protected void initData() {
       parseInternetData();
    }

    private void parseInternetData() {
        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_RECOMMENDATION, RecommedBean.class, new NetCallBack<RecommedBean>() {
            @Override
            public void onResponse(RecommedBean bean) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}
