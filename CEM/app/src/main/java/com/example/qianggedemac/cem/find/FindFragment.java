package com.example.qianggedemac.cem.find;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.DividerItemDecoration;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;
import com.example.qianggedemac.cem.view.ClearEditText;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import static android.widget.RelativeLayout.ALIGN_LEFT;

/**
 * 发现界面
 */
public class FindFragment extends BaseFragment implements View.OnClickListener, OnFindClickListener {

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
    // private TextView testTv;// 下面的布局 以后得换
    private Boolean isNext = false; // 判断是否是下一次
    private ClearEditText mClearEditText;
    private LinearLayout mLinearLayout;


    private RecyclerView mRecyclerView;
    private FindSearchAdapter mFindSearchAdapter;
    private StringBuilder mStringBuilder;

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        // mStringBuilder = new StringBuilder(UrlTools.SEARCH_KEY);
        loadingImg = (ImageView) view.findViewById(R.id.loading_img);
        loadingBefore = (ImageView) view.findViewById(R.id.loading_before);
        findRv = (PullLoadMoreRecyclerView) view.findViewById(R.id.find_pull_rv);
        mAdapter = new FindRvAdapter(mContext);
        findRv.setAdapter(mAdapter);
        findRv.setLinearLayout();
        findRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        // 搜索动画
        searchRl = (RelativeLayout) view.findViewById(R.id.find_search_layout);// 搜索的布局

        mClearEditText = (ClearEditText) view.findViewById(R.id.search_clear_et);
        mClearEditText.setFocusable(false);


        searchIv = (ImageView) view.findViewById(R.id.find_search_iv);
        findSearchTv = (TextView) view.findViewById(R.id.find_search_tv);
        /**
         * 搜索里的内容
         */
        mLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_find_search_detail_rv);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.invisible_search_rv);
        mFindSearchAdapter = new FindSearchAdapter();
        OkHttpManager.getInstance().get(UrlTools.HOT_SEARCH, FindSearchBean.class, new NetCallBack<FindSearchBean>() {
            @Override
            public void onResponse(FindSearchBean bean) {
                mFindSearchAdapter.setFindSearchBean(bean);
                mRecyclerView.setAdapter(mFindSearchAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(manager);
            }

            @Override
            public void onError(Exception e) {
                Log.d("数据", e.getMessage());
            }
        });

        String content = mClearEditText.getText().toString();

        //int a = UrlTools.SEARCH_KEY.indexOf("参数",0);
        //  String newUrl = UrlTools.SEARCH_KEY.replace("参数", content);
        // Log.d("网址", newUrl);

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
        // 点进详情
        mAdapter.setOnFindClickListener(this);
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
            /**
             * 整个搜索栏的点击
             */
            case R.id.find_search_layout:
                break;
            /**
             * 搜索的edit_text的点击
             */
            case R.id.search_clear_et:
                if (!isNext) {

//                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(target, "scaleX", 1, 0.7f);
//                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(target,"translationX", 1, -100);
//                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(target,"translationX", 1, -1.0f);
//                    searchSet.play(animator1).after(animator2);

//                    searchSet.playTogether(ObjectAnimator.ofFloat(target, "scaleX", 1, 0.9f),
//                            ObjectAnimator.ofFloat(target, "pivotX", 0.0f),
//                            ObjectAnimator.ofFloat(target, "translationX", 1, 0.0f));
//                    mClearEditText.setTextAlignment(5);
//                    searchSet.setTarget(target);
//                    searchSet.setDuration(200);
//                    searchSet.start();
//                    searchIv.setVisibility(View.GONE);
                    findRv.setVisibility(View.GONE);
                    mClearEditText.setScaleX(0.9f);//收缩
                    mClearEditText.setPivotX(0.0f);//以x轴转动
                    searchIv.setVisibility(View.GONE);

                    tvSet.playTogether(ObjectAnimator.ofFloat(findSearchTv, "alpha", 0f, 1f),
                            ObjectAnimator.ofFloat(target, "translationX", findSearchTv.getTranslationX(), 0.0f));
                    // 取消键的出现操作
                    findSearchTv.setVisibility(View.VISIBLE);
                    findSearchTv.setTextColor(Color.WHITE);

                    mLinearLayout.setVisibility(View.VISIBLE);
                    tvSet.setTarget(findSearchTv);
                    tvSet.setDuration(200);
                    tvSet.start();
                    //此时et可打字
                    mClearEditText.setEnabled(true);
                    mClearEditText.setFocusable(true);
                    mClearEditText.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager) mClearEditText.getContext().getSystemService(MyApp.getContext().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                    mClearEditText.setSelection(mClearEditText.getText().length());
                    mClearEditText.setImeOptions(EditorInfo.IME_ACTION_SEND);// 软键盘回车搜索

                    mClearEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                            // 当回车键按下时
                            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                                String content = mClearEditText.getText().toString();
//                                int a = mStringBuilder.indexOf("参数",0);
//                                String newUrl = mStringBuilder.replace(a,a+2,content).toString();
                                // Log.d("爱过等各大", newUrl);
                                if (!content.isEmpty()) {
                                    Intent intent = new Intent(MyApp.getContext(), SearchDetailActivity.class);
                                    String newUrl = UrlTools.SEARCH_KEY_BEFORE + content + UrlTools.SEARCH_KEY_AFTER;

                                    startActivity(intent);
                                }

                                Toast.makeText(mContext, mClearEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                                return true;

                            }
                            return false;
                        }
                    });
                    isNext = true;
                }
                break;
            case R.id.find_search_tv:
                findRv.setVisibility(View.VISIBLE);

                enlarge();
                findSearchTv.setVisibility(View.GONE);
                mLinearLayout.setVisibility(View.GONE);
                mClearEditText.setFocusable(false);
                mClearEditText.setFocusableInTouchMode(false);

                InputMethodManager imm = (InputMethodManager) mClearEditText.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                String a = mClearEditText.getText().toString();
                if (!a.isEmpty()) {
                    String b = a.replace(a, "");
                    mClearEditText.setText(b);

                }

                isNext = false;
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

    @Override
    public void findTopClick(String name) {

    Intent intent = new Intent(mContext,FindDetailActivity.class);
    intent.putExtra("title",name);
    mContext.startActivity(intent);
    }

    @Override
    public void findClick(int targetID, int feedType, String nickName, String urlImg, String title) {

        Intent intent = new Intent(mContext, FindDetailActivity.class);
        intent.putExtra("targetID", targetID);
        intent.putExtra("feedType", feedType);
        intent.putExtra("nickName", nickName);
        intent.putExtra("urlImg", urlImg);
        intent.putExtra("Title", title);

        mContext.startActivity(intent);

    }

}
