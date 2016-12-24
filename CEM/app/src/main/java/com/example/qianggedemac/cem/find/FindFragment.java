package com.example.qianggedemac.cem.find;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.DividerItemDecoration;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.example.qianggedemac.cem.view.ClearEditText;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import static android.widget.RelativeLayout.ALIGN_LEFT;

/**
 * 发现界面
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    private PullLoadMoreRecyclerView findRv;
    private FindRvAdapter mAdapter;
    private int count = 0;
    private ImageView loadingImg;
    private ImageView loadingBefore;
    private RotateAnimation mAnimation;// 刷新动画

    private RelativeLayout searchRl;// 搜索栏
    private AnimatorSet searchSet;// 搜索栏组合动画
    private AnimatorSet tvSet;
    private View target; // 动画目标
    private ImageView searchIv;
    private TextView findSearchTv;// 取消的字
    private TextView testTv;// 下面的布局 以后得换
    private Boolean isNext = false; // 判断是否是下一次
    private ClearEditText mClearEditText;


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

        // 搜索动画
        searchRl = (RelativeLayout) view.findViewById(R.id.find_search_layout);// 搜索的布局
        testTv = (TextView) view.findViewById(R.id.invisible_tv);// 下面的布局 以后得换
        mClearEditText = (ClearEditText) view.findViewById(R.id.search_clear_et);
        mClearEditText.setFocusable(false);

        searchIv = (ImageView) view.findViewById(R.id.find_search_iv);
        findSearchTv = (TextView) view.findViewById(R.id.find_search_tv);
    }

    @Override
    protected void initData() {
        //动画
        startAnim();
        //网络请求
        initNetWork();
        // 刷新更多
        pullToRefresh();
        // 点击事件
        setClick(this, searchRl, mClearEditText, findSearchTv);

    }


    public void setClick(View.OnClickListener onClickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }
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

    @Override
    public void onClick(View view) {
        target = mClearEditText;
        searchSet = new AnimatorSet();
        tvSet = new AnimatorSet();


        switch (view.getId()) {

            case R.id.find_search_layout:
                if (!isNext) {
                    findRv.setVisibility(View.GONE);
                    testTv.setVisibility(View.VISIBLE);
                    isNext = true;
                } else {
                    findRv.setVisibility(View.VISIBLE);
                    testTv.setVisibility(View.GONE);
                    isNext = false;
                }

                break;
            case R.id.search_clear_et:


                if (!isNext) {
                    findRv.setVisibility(View.GONE);
                    testTv.setVisibility(View.VISIBLE);
//                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(target, "scaleX", 1, 0.7f);
//                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(target,"translationX", 1, -100);
//                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(target,"translationX", 1, -1.0f);
//                    searchSet.play(animator1).after(animator2);

//
//                    searchSet.playTogether(ObjectAnimator.ofFloat(target, "scaleX", 1, 0.9f),
//                            ObjectAnimator.ofFloat(target, "pivotX", 0.0f),
//                            ObjectAnimator.ofFloat(target, "translationX", 1, 0.0f));
//                    mClearEditText.setTextAlignment(5);
//                    searchSet.setTarget(target);
//                    searchSet.setDuration(200);
//                    searchSet.start();
//                    searchIv.setVisibility(View.GONE);

                    mClearEditText.setScaleX(0.9f);
                    mClearEditText.setPivotX(0.0f);
                    searchIv.setVisibility(View.GONE);

                    tvSet.playTogether(ObjectAnimator.ofFloat(findSearchTv, "alpha", 0f, 1f),
                            ObjectAnimator.ofFloat(target, "translationX", findSearchTv.getTranslationX(), 0.0f));

//                    ObjectAnimator.ofFloat(findSearchTv,View.TRANSLATION_X,300,0.6f)
                    findSearchTv.setVisibility(View.VISIBLE);
                    findSearchTv.setTextColor(Color.WHITE);
                    tvSet.setTarget(findSearchTv);
                    tvSet.setDuration(200);
                    tvSet.start();
                    mClearEditText.setFocusable(true);
                    mClearEditText.setEnabled(true);
//                    mClearEditText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) mClearEditText.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                    isNext = true;
                } else {

//                    findRv.setVisibility(View.VISIBLE);
//                    testTv.setVisibility(View.GONE);
//                    enlarge();
//
//
//                    findSearchTv.setVisibility(View.GONE);

//                    mClearEditText.
                }
                break;
            case R.id.find_search_tv:
                findRv.setVisibility(View.VISIBLE);
                testTv.setVisibility(View.GONE);
                enlarge();
                findSearchTv.setVisibility(View.GONE);
                isNext = false;

                InputMethodManager imm = (InputMethodManager) mClearEditText.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                String a = mClearEditText.getText().toString();
                if (!a.isEmpty()){
                    String b = a.replace(a,"");
                    mClearEditText.setText(b);
                }
                break;
        }
    }

    private void enlarge() {
        searchSet.playTogether(ObjectAnimator.ofFloat(target, "pivotX", 0.0f),
                ObjectAnimator.ofFloat(target, "scaleX", 0.9f, 1));

//                   ObjectAnimator.ofFloat(target, "scaleX", 0, 1)
        mClearEditText.setHint("找电影、影人、影院");
        mClearEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        searchSet.setTarget(target);
        searchSet.setDuration(200);
        searchSet.start();
    }
}
