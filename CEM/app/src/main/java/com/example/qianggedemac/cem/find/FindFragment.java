package com.example.qianggedemac.cem.find;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.DividerItemDecoration;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 发现界面
 */
public class FindFragment extends BaseFragment {


//    // 发现 -> 今天
//    public final String FIND_TODAY = "http://api.maoyan.com/sns/v5/feed.json?";
//    //发现 -> 上面四个图标
//    public final String FIND_TOP = "http://api.maoyan.com/sns/v2/buttons.json?utm_term=7.5.0&utm_medium=android";

    private PullLoadMoreRecyclerView findRv;
    private FindRvAdapter mAdapter;
    private int count = 0;
    private ImageView loadingImg;
    private ImageView loadingBefore;
    private RotateAnimation mAnimation;// 刷新动画


    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        loadingImg = (ImageView) view.findViewById(R.id.loading_img);
        loadingBefore = (ImageView) view.findViewById(R.id.loading_before);
        findRv = (PullLoadMoreRecyclerView) view.findViewById(R.id.find_pull_rv);
        mAdapter = new FindRvAdapter(mContext);

        findRv.setAdapter(mAdapter);
        findRv.setLinearLayout();
        findRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    protected void initData() {
        //动画
        startAnim();
        //网络请求
        initNetWork();
        // 刷新更多
        pullToRefresh();


    }


    private void pullToRefresh() {
        findRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                startAnim();
                initNetWork();
                findRv.setPullLoadMoreCompleted();
//                findRv.setFooterViewBackgroundColor(R.color.cardview_shadow_start_color);
            }


            @Override
            public void onLoadMore() {
                startAnim();
                count += 10;

                getFindToday(count, 10, new NetCallBack<FindTodayBean>() {
                    @Override
                    public void onResponse(FindTodayBean bean) {
                        mAdapter.addFindTodayBean(bean);
                        loadingImg.clearAnimation();
                        loadingImg.setVisibility(View.GONE);
                        loadingBefore.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                findRv.setPullLoadMoreCompleted();
            }
        });

    }

    private void initNetWork() {
        getFindToday(0, 10, new NetCallBack<FindTodayBean>() {
            @Override
            public void onResponse(FindTodayBean bean) {
                mAdapter.setFindTodayBean(bean);
                loadingImg.clearAnimation();
                loadingImg.setVisibility(View.GONE);
                loadingBefore.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(mContext, "请检查网络重试!", Toast.LENGTH_SHORT).show();
            }
        });

        OkHttpManager.getInstance().get(UrlTools.FIND_TOP, FindTopBean.class, new NetCallBack<FindTopBean>() {
            @Override
            public void onResponse(FindTopBean bean) {
                mAdapter.setFindTopBean(bean);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }


    public void getFindToday(int offset, int limit, NetCallBack<FindTodayBean> netCallback) {
        String url = UrlTools.FIND_TODAY + "offset=" + offset + "&limit=" + limit;
        OkHttpManager.getInstance().get(url, FindTodayBean.class, netCallback);
    }

    public void startAnim() {

        loadingImg.setVisibility(View.VISIBLE);
        loadingBefore.setVisibility(View.VISIBLE);

        mAnimation = new RotateAnimation(0, 7200, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        mAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        //设置播放时间
        mAnimation.setDuration(10000);
        //设置重复次数
        mAnimation.setRepeatCount(30);
        //动画重复播放的模式
        mAnimation.setRepeatMode(Animation.RESTART);
        //动画播放完毕后，组件停留在动画结束的位置上
        mAnimation.setFillAfter(true);
        loadingImg.startAnimation(mAnimation);
    }
}
