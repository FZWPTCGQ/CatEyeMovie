package com.example.qianggedemac.cem.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class SearchDetailActivity extends AppCompatActivity {
    private SearchDetailAdapter mSearchDetailAdapter;
    private PullToRefreshListView mPullToRefreshListView;
    private RecyclerView mRecyclerView;
    private SearchDetailHeaderAdapter mSearchDetailHeaderAdapter;
    private int offest = 0;
    private String mUrl;
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.search_detail_activity_lv);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mContent = intent.getStringExtra("content");
        Log.d("SearchDetailActivity", mContent);
        Log.d("SearchDetailActivity", mUrl);
        mSearchDetailAdapter = new SearchDetailAdapter(this);
        /**
         *
         */
        View view = LayoutInflater.from(this).inflate(R.layout.search_layout_one_item,null);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.search_layout_one_item_rv);
        mSearchDetailHeaderAdapter = new SearchDetailHeaderAdapter();

        /**
         *
         */
       OkHttpManager.getInstance().get(mUrl, SearchHeaderBean.class, new NetCallBack<SearchHeaderBean>() {
           @Override
           public void onResponse(SearchHeaderBean bean) {

                   mSearchDetailAdapter.setSearchHeaderBean(bean);
                   mPullToRefreshListView.setAdapter(mSearchDetailAdapter);
                   mSearchDetailHeaderAdapter.setSearchHeaderBean(bean);
                   mRecyclerView.setAdapter(mSearchDetailHeaderAdapter);
                   LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext(),LinearLayoutManager.VERTICAL,false);
                   mRecyclerView.setLayoutManager(manager);



           }

           @Override
           public void onError(Exception e) {

           }
       });
        mPullToRefreshListView.getRefreshableView().addHeaderView(view);




        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                offest += 10;
                Log.d("撒地方", UrlTools.SEARCH_KEY_BEFORE + mContent + UrlTools.SEARCH_DETAIL_CENTER + offest + UrlTools.SEARCH_DETAIL_AFTER);
                OkHttpManager.getInstance().get(UrlTools.SEARCH_KEY_BEFORE + mContent + UrlTools.SEARCH_DETAIL_CENTER + offest + UrlTools.SEARCH_DETAIL_AFTER, SearchHeaderBean.class, new NetCallBack<SearchHeaderBean>() {
                    //  UrlTools.SEARCH_KEY_BEFORE + mContent + UrlTools.SEARCH_DETAIL_CENTER + offest + UrlTools.SEARCH_DETAIL_AFTER
                    @Override
                    public void onResponse(SearchHeaderBean bean) {
                        Log.d("新数据", "bean.getData().size():" + bean.getData().size());
                        for (SearchHeaderBean.DataBean dataBean : bean.getData()) {

                        }
                        mSearchDetailAdapter.addSearchHeaderBean(bean);
                        /**
                         * 就set一边适配器就行了
                         */
                      //  mPullToRefreshListView.setAdapter(mSearchDetailAdapter);

                        mPullToRefreshListView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });

    }

}
