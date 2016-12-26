package com.example.qianggedemac.cem.film.refreshablewaitfragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.film.wait.NearBean1;
import com.example.qianggedemac.cem.film.wait.RecommedBean;
import com.example.qianggedemac.cem.film.wait.WishBean;
import com.example.qianggedemac.cem.find.FindTodayBean;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefreshableWaitFragment extends BaseFragment {


    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RefreshWaitAdapter mRefreshWaitAdapter = new RefreshWaitAdapter();
    private List<HeaderNameBean> mDataList;
    private List<String> mMovieIds;
    int count = 12;
    private String mNewUrl;
    private String mIds;

    @Override
    protected int setLayout() {
        return R.layout.fragment_refreshable_wait;
    }

    @Override
    protected void initView(View view) {
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)view.findViewById(R.id.refreshable_wait_fragment_rv);
        mNewUrl = UrlTools.MOVIE_WAIT_LISTVIEW;

    }

    @Override
    protected void initData() {
        /**
         * 解析网络数据
         */
       parseInternetData();


        mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mPullLoadMoreRecyclerView.setPullRefreshEnable(true);


        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
           mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
            //上拉
            @Override
            public void onLoadMore() {
                Log.d("RefreshableWaitFragment", "in");
                OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_LISTVIEW_MORE , NearBean1.class, new NetCallBack<NearBean1>() {
                    @Override
                    public void onResponse(NearBean1 bean) {
//                        Log.d("安防科技三大", "bean.getData().getMovieIds().size():" + bean.getData().getMovieIds().size());
//                        Log.d("安防科技三大", "bean.getData().getMovieIds().get(0):" + bean.getData().getMovieIds().get(0));
                        mMovieIds = new ArrayList<String>();
                       // Log.d(\"大大发生的", "bean.getData().getComing().size():" + bean.getData().getComing().size());
                        mIds = "";
                        for (int i = count; i < count + 12; i++) {
                            int id = bean.getData().getMovieIds().get(i);
                            mIds += String.valueOf(id) + ",";
                            Log.d("嘎多分散", mIds);
                        }
                        mIds = mIds.substring(0, mIds.length() - 1);
                        //TODO 网络请求
                        count += 12;

                        mNewUrl = UrlTools.MOVIE_WAIT_NEW_URL;

                        OkHttpManager.getInstance().get(mNewUrl + mIds, NearBean.class, new NetCallBack<NearBean>() {
                            @Override
                            public void onResponse(NearBean bean) {
                                Log.d("gadfgfds", "bean.getData().getMovies().size():" + bean.getData().getMovies().size());
                                mRefreshWaitAdapter.addFindTodayBean(bean);

                                NearBean nearBean = mRefreshWaitAdapter.getNearBean();

                                mDataList.clear();
                                for (int i = 0; i < nearBean.getData().getMovies().size(); i++) {
                                    HeaderNameBean nameBean = new HeaderNameBean();
                                    String name0 = nearBean.getData().getMovies().get(i).getComingTitle();
                                    nameBean.setName(name0);
                                    mDataList.add(nameBean);
                                    Log.d("afasdhj", "mDataList.size():" + mDataList.size());
                                }

                                /**
                                 * recycleView绑定适配器
                                 */
                                mPullLoadMoreRecyclerView.addItemDecoration(new SectionDecoration(mDataList,mContext, new SectionDecoration.DecorationCallback() {
                                    //返回标记id (即每一项对应的标志性的字符串)
                                    @Override
                                    public String getGroupId(int position) {
                                        Log.d("fadsaf", "mDataList.size():" + mDataList.size());
                                        if(mDataList.get(position).getName()!=null) {
                                            return mDataList.get(position).getName();
                                        }
                                        return "-1";
                                    }

                                    //获取同组中的第一个内容
                                    @Override
                                    public String getGroupFirstLine(int position) {
                                        if(mDataList.get(position).getName()!=null) {
                                            return mDataList.get(position).getName();
                                        }
                                        return "";
                                    }
                                }));

                            }

                            @Override
                            public void onError(Exception e) {
                                Log.d("RefreshableWaitFragment", e.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("RefreshableWaitFragment", e.getMessage());
                    }
                });

            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }



    private void initAdapter() {
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setAdapter(mRefreshWaitAdapter);
    }

    private void parseInternetData() {
//        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_RECOMMENDATION, RecommedBean.class, new NetCallBack<RecommedBean>() {
//
//
//            @Override
//            public void onResponse(RecommedBean bean) {
//              mRefreshWaitAdapter.setRecommedBean(bean);
//                Log.d("ahughalkghj", "bean.getData().size():" + bean.getData().size());
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });



//        OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_WISH, WishBean.class, new NetCallBack<WishBean>() {
//
//            @Override
//            public void onResponse(WishBean bean) {
//                mRefreshWaitAdapter.setWishBean(bean);
//                Log.d("gdgsd", "bean.getData().getComing().size():" + bean.getData().getComing().size());
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });




        OkHttpManager.getInstance().get(mNewUrl, NearBean.class, new NetCallBack<NearBean>() {
            @Override
            public void onResponse(NearBean bean) {

                mRefreshWaitAdapter.setNearBean(bean);
                Log.d("ahughalkghj", "bean.getData().getComing().size():" + bean.getData().getComing().size());
                setPullAction(bean);
            }

            @Override
            public void onError(Exception e) {
                Log.d("RefreshableWaitFragment", e.getMessage());
            }
        });
    }

    private void setPullAction(NearBean bean) {
        mDataList = new ArrayList<>();

        for (int i = 0; i < bean.getData().getMovies().size(); i++) {
            HeaderNameBean nameBean = new HeaderNameBean();
            String name0 = bean.getData().getMovies().get(i).getComingTitle();
            nameBean.setName(name0);
            mDataList.add(nameBean);
            Log.d("afasdhj", "mDataList.size():" + mDataList.size());
        }
//        HeaderNameBean headerNameBean = new HeaderNameBean();
//        headerNameBean.setName("近期最收期待");
//        mDataList.add(0,headerNameBean);
//        HeaderNameBean headerNameBean1 = new HeaderNameBean();
//        headerNameBean1.setName("预告片推荐");
//        mDataList.add(0,headerNameBean1);


        /**
         * recycleView绑定适配器
         */
        mPullLoadMoreRecyclerView.addItemDecoration(new SectionDecoration(mDataList,mContext, new SectionDecoration.DecorationCallback() {
            //返回标记id (即每一项对应的标志性的字符串)
            @Override
            public String getGroupId(int position) {
                Log.d("fadsaf", "mDataList.size():" + mDataList.size());
                if(mDataList.get(position).getName()!=null) {
                    return mDataList.get(position).getName();
                }
                return "-1";
            }

            //获取同组中的第一个内容
            @Override
            public String getGroupFirstLine(int position) {
                if(mDataList.get(position).getName()!=null) {
                    return mDataList.get(position).getName();
                }
                return "";
            }
        }));

        initAdapter();

    }

}
