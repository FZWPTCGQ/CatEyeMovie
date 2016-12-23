package com.example.qianggedemac.cem.film.wait;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitFragment extends BaseFragment {


    private StickyListHeadersListView mStickyListHeadersListView;
    private WaitFragmentAdapter mWaitFragmentAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_wait;
    }

    @Override
    protected void initView(View view) {
        mStickyListHeadersListView = (StickyListHeadersListView)view.findViewById(R.id.fragment_wait_stickyListHeaderListView);
        mWaitFragmentAdapter = new WaitFragmentAdapter(mContext);

    }

    @Override
    protected void initData() {
     parseInternet();
    }

    private void parseInternet() {
        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_LISTVIEW, NearBean.class, new NetCallBack<NearBean>() {
            @Override
            public void onResponse(NearBean bean) {
                mWaitFragmentAdapter.setNearBean(bean);
                List<String> list = new ArrayList<String>();
                Map<String,List<NearBean.DataBean.ComingBean>> map = new ArrayMap<String, List<NearBean.DataBean.ComingBean>>();
                for (int i = 0; i < bean.getData().getComing().size(); i++) {
                    String date = bean.getData().getComing().get(i).getComingTitle();
                    list.add(date);
                    map.put(date,bean.getData().getComing());
                    Log.d("阿范德萨发大水", "map.size():" + map.size());
                }

                mWaitFragmentAdapter.setLists(list);
                mWaitFragmentAdapter.setMap(map);
                mStickyListHeadersListView.setAdapter(mWaitFragmentAdapter);

            }

            @Override
            public void onError(Exception e) {

            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_layout,null);
        mStickyListHeadersListView.addHeaderView(view);
    }

}
