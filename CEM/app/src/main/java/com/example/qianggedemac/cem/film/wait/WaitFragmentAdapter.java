package com.example.qianggedemac.cem.film.wait;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;

import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by qianggedemac on 16/12/22.
 */


public class WaitFragmentAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

//    private Context mContext;
//
//    private RecommedBean mRecommedBean;
//    private WishBean mWishBean;
//    private NearBean1 mNearBean;
//    private static final int TYPE_ONE = 1;
//    private static final int TYPE_TWO = 2;
//    private static final int TYPE_THREE = 3;
//    private static final int COUNT = 100;
//    //  private BodyHolderRecommend mBodyHolderRecommend;
//    private BodyHolderWish mBodyHolderWish;
//    private BodyHolderNear mBodyHolderNear;
//    private BodyHolderRecommend mMBodyHolderRecommend;
//    private List<String> mLists;
//    private Map<String, List<NearBean1.DataBean.ComingBean>> mMap;
//
//    public void setLists(List<String> lists) {
//        mLists = lists;
//        notifyDataSetChanged();
//    }
//
//    public void setMap(Map<String, List<NearBean1.DataBean.ComingBean>> map) {
//        mMap = map;
//        notifyDataSetChanged();
//    }
//
//    public WaitFragmentAdapter(Context context) {
//        mContext = context;
//    }


//        mNearBean = nearBean;
//        Log.d("数据", "mNearBean.getData().getComing().size():" + mNearBean.getData().getComing().size());
//        notifyDataSetChanged();
//    }
//
//    public void setRecommedBean(RecommedBean recommedBean) {
//        mRecommedBean = recommedBean;
//    }
//
//    public void setWishBean(WishBean wishBean) {
//        mWishBean = wishBean;
//    }
//
//
//    @Override
//    public long getHeaderId(int position) {

//
//
//
//        if (position == 0){

//            return 1000;
//        } else if (position == 1) {
//            return 999;
//        } else {
//            String date = mNearBean.getData().getComing().get(position - 2).getComingTitle();
//            Log.d("发撒上的", "date.length():" + date.length());
//            int a = date.indexOf(" ");
//            Log.d("发撒上的", "a:" + a);
//            String day = date.replace(" ", "");
//            String dayMonth = day.replace("月", "");
//            String dayDay = dayMonth.replace("日", "");
//            String dayWeek = dayDay.substring(0, dayDay.length() - 2);
//            Log.d("日期", "天" + dayWeek);
//            Long l = Long.parseLong(dayWeek);
//            Log.d("日期", "l:" + l);
//            return Long.parseLong(dayWeek);
//        }

//    }
//
//    @Override
//    public int getCount() {
//        return mNearBean.getData().getComing().size();
//    }

//
//        return mNearBean.getData().getComing().get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {

//
//        return mNearBean.getData().getComing().get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TYPE_ONE;
//        } else if (position == 1) {
//            return TYPE_TWO;
//        } else {
//            return TYPE_THREE;
//        }
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return COUNT;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        mMBodyHolderRecommend = null;
//        mBodyHolderWish = null;
//        mBodyHolderNear = null;
//        int type = getItemViewType(i);
//        switch (type) {
//            case TYPE_ONE:
//                if (view == null) {
//                    view = LayoutInflater.from(mContext).inflate(R.layout.wait_fragment_adapter_item_one, viewGroup, false);
//                    mMBodyHolderRecommend = new BodyHolderRecommend(view);
//                    view.setTag(mMBodyHolderRecommend);
//                } else {
//                    mMBodyHolderRecommend = (BodyHolderRecommend) view.getTag();
//                }
//                final WaitRecommedAdapter waitRecommedAdapter = new WaitRecommedAdapter(mContext);
//
//                mMBodyHolderRecommend.mRecyclerViewRecommend.setAdapter(waitRecommedAdapter);
//                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//                mMBodyHolderRecommend.mRecyclerViewRecommend.setLayoutManager(manager);
//                OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_RECOMMENDATION, RecommedBean.class, new NetCallBack<RecommedBean>() {
//                    @Override
//                    public void onResponse(RecommedBean bean) {
//                        waitRecommedAdapter.setRecommedBean(bean);
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
//
//                break;
//            case TYPE_TWO:
//                if (view == null) {
//                    view = LayoutInflater.from(mContext).inflate(R.layout.wait_fragment_adapter_item_two, viewGroup, false);
//                    mBodyHolderWish = new BodyHolderWish(view);
//                    view.setTag(mBodyHolderWish);
//                } else {
//                    mBodyHolderWish = (BodyHolderWish) view.getTag();
//                }
//                final WaitWishAdapter waitWishAdapter = new WaitWishAdapter(mContext);
//                mBodyHolderWish.mRecyclerViewWish.setAdapter(waitWishAdapter);
//                LinearLayoutManager manager1 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//                mBodyHolderWish.mRecyclerViewWish.setLayoutManager(manager1);
//                OkHttpManager.getInstance().get(UrlTools.MOVIE_WAIT_WISH, WishBean.class, new NetCallBack<WishBean>() {
//                    @Override
//                    public void onResponse(WishBean bean) {
//                        waitWishAdapter.setWishBean(bean);
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
//
//                break;
//            case TYPE_THREE:
//                if (view == null) {
//                    view = LayoutInflater.from(mContext).inflate(R.layout.wait_fragment_adapter_item_three, viewGroup, false);
//                    mBodyHolderNear = new BodyHolderNear(view);
//                    view.setTag(mBodyHolderNear);
//                } else {
//                    mBodyHolderNear = (BodyHolderNear) view.getTag();
//                }
//                mBodyHolderNear.nmTvNear.setText(mNearBean.getData().getComing().get(i - 2).getNm());
//                mBodyHolderNear.wishTvNear.setText(mNearBean.getData().getComing().get(i - 2).getWish() + "人想看");
//                mBodyHolderNear.scmTvNear.setText(mNearBean.getData().getComing().get(i - 2).getScm());
//                mBodyHolderNear.starTvNear.setText(mNearBean.getData().getComing().get(i - 2).getStar());
//                String url = mNearBean.getData().getComing().get(i - 2).getImg();
//                String newUrl = url.replace("/w.h/", "/165.220/");
//                Glide.with(mContext).load(newUrl).into(mBodyHolderNear.imgIvNear);
//                break;
//        }
//        return view;
//    }
//
//    @Override
//    public View getHeaderView(int position, View convertView, ViewGroup parent) {
//        //int pos = position + 1;
//        HeaderViewHolder headerViewHolder = null;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.wait_fragment_adapter_item_header, parent, false);
//            headerViewHolder = new HeaderViewHolder(convertView);
//            convertView.setTag(headerViewHolder);
//        } else {
//            headerViewHolder = (HeaderViewHolder) convertView.getTag();
//        }
////        for (int i = 0; i < mNearBean.getData().getComing().size(); i++) {
////            mLists = new ArrayList<>();
////            String date = mNearBean.getData().getComing().get(i).getComingTitle();
////            mLists.add(date);
////            mMap.put(date,mNearBean.getData().getComing());
////
////        }
////        for (int i = mNearBean.getData().getComing().size(); i >2 ; i--) {
////
////                String date = mNearBean.getData().getComing().get(i-1).getComingTitle();
////                String nextDate = mNearBean.getData().getComing().get(i - 2).getComingTitle();
////                if (date.equals(nextDate)){
////                    mLists = new ArrayList<>();
////                    mLists.addAll(mNearBean.getData().getComing());
////                    Log.d("WaitFragmentAdapter", "lists.size():" + mLists.size());
////                    mMap = new HashMap<>();
////                    mMap.put(date, mLists);
////                }
////
////            }
//
//        if (position == 0) {
//            headerViewHolder.mTextViewHeader.setText("预告片推荐");
//        } else if (position == 1) {
//            headerViewHolder.mTextViewHeader.setText("近期最受期待");
//        } else {
//            headerViewHolder.mTextViewHeader.setText(mNearBean.getData().getComing().get(position - 2).getComingTitle());
//        }
//
//        return convertView;
//    }
//
//
//    public class HeaderViewHolder {
//        TextView mTextViewHeader;
//
//        public HeaderViewHolder(View view) {
//            mTextViewHeader = (TextView) view.findViewById(R.id.wait_fragment_adapter_item_header_tv);
//
//        }
//    }
//
//
//    public class BodyHolderRecommend {
//        RecyclerView mRecyclerViewRecommend;
//
//        public BodyHolderRecommend(View view) {
//            mRecyclerViewRecommend = (RecyclerView) view.findViewById(R.id.wait_fragment_adapter_item_one_rv);
//        }
//    }
//
//    public class BodyHolderWish {
//        RecyclerView mRecyclerViewWish;
//
//        public BodyHolderWish(View view) {
//            mRecyclerViewWish = (RecyclerView) view.findViewById(R.id.wait_fragment_adapter_item_two_rv);
//        }
//    }
//
//    public class BodyHolderNear {
//        ImageView imgIvNear, verIvNear, playIvNear;
//        TextView nmTvNear, wishTvNear, scmTvNear, starTvNear;
//
//        public BodyHolderNear(View view) {
//            playIvNear = (ImageView) view.findViewById(R.id.wait_item_three_playIv);
//            imgIvNear = (ImageView) view.findViewById(R.id.wait_item_three_img);
//            verIvNear = (ImageView) view.findViewById(R.id.wait_item_three_threeD_iv);
//            nmTvNear = (TextView) view.findViewById(R.id.wait_item_three_nm_tv);
//            wishTvNear = (TextView) view.findViewById(R.id.wait_item_three_wish_tv);
//            scmTvNear = (TextView) view.findViewById(R.id.wait_item_three_scm_tv);
//            starTvNear = (TextView) view.findViewById(R.id.wait_item_three_star_tv);
//        }
//    }
}
