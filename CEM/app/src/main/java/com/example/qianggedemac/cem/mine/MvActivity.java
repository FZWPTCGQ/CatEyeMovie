package com.example.qianggedemac.cem.mine;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wuxiaolong.pullloadmorerecyclerview.SwipeRefreshLayoutOnRefresh;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MvActivity extends BaseActivity {
    //offset 0
    String mvHeadUrl = "http://mapi.yinyuetai.com/suggestions/front_page.json?deviceinfo=%7B%22aid%22:%2210201036%22,%22os%22:%22Android%22,%22ov%22:%225.0.2%22,%22rn%22:%22480*800%22,%22dn%22:%22Redmi%20Note%203%22,%22cr%22:%2246000%22,%22as%22:%22WIFI%22,%22uid%22:%22dbcaa6c4482bc05ecb0bf39dabf207d2%22,%22clid%22:110025000%7D&offset=";
    // size 20
    String mvMidUrl = "&size=";
    String mvTailUrl = "&v=4&rn=640*540";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FloatingActionButton fab;
    private RecyclerView mvRv;
    private ArrayList<MvBean> list;
    protected boolean hasMore = true;

    protected boolean refresh;
    private MvAdapter mMvAdapter;
    private RequestQueue mRequestQueue;

    protected int lastVisibleItem;

    private int mOffset;
    private int mSize;

    public String getUrl(int offset, int size) {
        String url = mvHeadUrl + offset + mvMidUrl + size + mvTailUrl;

        return url;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_mv;
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mvRv = (RecyclerView) findViewById(R.id.mv_rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);


    }


    public void setData(ArrayList<MvBean> dataList) {
        if (refresh) {
            refresh = false;
            list.clear();
            mOffset = 0;
        }
        if (dataList.size() > 0) {
            hasMore = true;
        } else {
            hasMore = false;
        }
        mOffset += dataList.size();
        list.addAll(dataList);
        mMvAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void initDatas() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvRv.scrollToPosition(0);
            }
        });
        list = new ArrayList<>();
        mMvAdapter = new MvAdapter(this);

        mRequestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(getUrl(0, 20), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
// 数据开头结尾就是[] 用typetoken
                Type type = new TypeToken<ArrayList<MvBean>>() {
                }.getType();

                Gson gson = new Gson();
                list = gson.fromJson(response, type);

                mMvAdapter.setDatas(list);
                mvRv.setAdapter(mMvAdapter);

                mvRv.setLayoutManager(new LinearLayoutManager(MvActivity.this, LinearLayoutManager.VERTICAL, false));

                mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//
            }
        });
        mRequestQueue.add(stringRequest);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {
                                                         refresh = true;

                                                     }
                                                 }
        );

        mvRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MvActivity.this).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItem + 1 == mMvAdapter.getItemCount()) && hasMore) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (!hasMore) {
                        Log.d("aaa", "ignore manually update!");
                    } else {
                        mOffset += 20;
//这里多线程也要手动控制isLoadingMore
                        StringRequest stringRequest = new StringRequest(getUrl(mOffset, 20), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
// 数据开头结尾就是[] 用typetoken
                                Type type = new TypeToken<ArrayList<MvBean>>() {
                                }.getType();
                                Gson gson = new Gson();
                                list = gson.fromJson(response, type);

                                setData(list);
                                mMvAdapter.setDatas(list);
                                mvRv.setAdapter(mMvAdapter);
                                mSwipeRefreshLayout.setRefreshing(false);
                                mvRv.setLayoutManager(new LinearLayoutManager(MvActivity.this, LinearLayoutManager.VERTICAL, false));

                                mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//                mMvAdapter.setOnItemClick(new OnItemClick() {
//                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    @Override
//                    public void onClick(String url, String hdUrl, String thumb, String title, int pos) {
//                        Intent intent = new Intent(MvActivity.this, MainActivity.class);
//                        intent.putExtra("url", url);
//                        intent.putExtra("hdUrl", hdUrl);
//                        intent.putExtra("pos", pos);
//                        intent.putExtra("thumb", thumb);
//                        intent.putExtra("title", title);
//
//                        startActivity(intent);
//                    }

//                });
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//
                            }
                        });
                        mRequestQueue.add(stringRequest);
                        hasMore = false;
                    }
                }
                Glide.with(MvActivity.this).pauseRequests();
            }
        });


    }


}
