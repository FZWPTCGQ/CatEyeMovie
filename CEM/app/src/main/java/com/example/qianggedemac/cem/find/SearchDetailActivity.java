package com.example.qianggedemac.cem.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class SearchDetailActivity extends AppCompatActivity {
    private SearchDetailAdapter mSearchDetailAdapter;
    private PullLoadMoreRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        mRecyclerView = (PullLoadMoreRecyclerView)findViewById(R.id.search_detail_activity_rv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("SearchDetailActivity", url);
        mSearchDetailAdapter = new SearchDetailAdapter();
        OkHttpManager.getInstance().get(url, SearchDetailBean.class, new NetCallBack<SearchDetailBean>() {
            @Override
            public void onResponse(SearchDetailBean bean) {
                mSearchDetailAdapter.setSearchDetailBean(bean);
//                Log.d("SearchDetailActivity", "bean:" + bean.getData().size());
//                Log.d("SearchDetailActivity", "bean.getData().get(1).getList().size():" + bean.getData().get(1).getList().get(2).getTitle());
//                Log.d("SearchDetailActivity", "bean.getData().get(0).getList().size():" + bean.getData().get(0).getList().size());
//                Log.d("SearchDetailActivity", "bean.getData().get(1).getLists().size():" + bean.getData().get(1).getList().size());

                mRecyclerView.setAdapter(mSearchDetailAdapter);
               mRecyclerView.setLinearLayout();

            }

            @Override
            public void onError(Exception e) {
                Log.d("SearchDetailActivity", "e:" + e);
            }
        });

    }
}
