package com.example.qianggedemac.cem.find;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.BuildConfig;
import com.example.qianggedemac.cem.R;


/**
 * Created by dllo on 2016/12/20.
 */

public class FindRvAdapter extends RecyclerView.Adapter<FindRvAdapter.FindViewHolder> {

    private FindTopBean mFindTopBean;
    private FindTodayBean mFindTodayBean;
    private Context mContext;
    View target;

    private OnFindClickListener mOnFindClickListener;

    public void setOnFindClickListener(OnFindClickListener onFindClickListener) {
        mOnFindClickListener = onFindClickListener;
    }

    public FindRvAdapter(Context context) {
        mContext = context;
    }

    public void setFindTodayBean(FindTodayBean findTodayBean) {
        mFindTodayBean = findTodayBean;
        notifyDataSetChanged();
    }

    public void addFindTodayBean(FindTodayBean findTodayBean) {
        this.mFindTodayBean.getData().addData(findTodayBean.getData().getFeeds());
        notifyDataSetChanged();
    }

    public void setFindTopBean(FindTopBean findTopBean) {
        mFindTopBean = findTopBean;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (mFindTopBean == null) {
            return mFindTodayBean.getData().getFeeds().get(position).getStyle();
        } else {

            return position == 0 ? 1 : mFindTodayBean.getData().getFeeds().get(position - 1).getStyle();
        }
    }

    @Override
    public FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FindViewHolder mViewHolder = null;
        switch (viewType) {
            case 1:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_find_head, parent, false);
                mViewHolder = new FindViewHolder(view1);
                break;
            case 2:
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_find_one_pic, parent, false);
                mViewHolder = new FindViewHolder(view2);
                break;
            case 3:
                View view3 = LayoutInflater.from(mContext).inflate(R.layout.item_find_three_pic, parent, false);
                mViewHolder = new FindViewHolder(view3);
                break;
            case 4:
                View view4 = LayoutInflater.from(mContext).inflate(R.layout.item_three_more, parent, false);
                mViewHolder = new FindViewHolder(view4);
                break;
            default:
                View view7 = LayoutInflater.from(mContext).inflate(R.layout.item_find_seven, parent, false);
                mViewHolder = new FindViewHolder(view7);
                break;

//
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final FindViewHolder holder, int position) {
        int type = getItemViewType(position);

        target = holder.itemView;

        if (type != 1) {
            position = position - 1;
        }
        switch (type) {
//四个图标
            case 1:
                if (mFindTopBean != null) {
                    Glide.with(mContext).load(mFindTopBean.getData().get(0).getImage().getUrl()).into(holder.findHeaderTop);
                    Glide.with(mContext).load(mFindTopBean.getData().get(1).getImage().getUrl()).into(holder.findHeaderNew);
                    Glide.with(mContext).load(mFindTopBean.getData().get(2).getImage().getUrl()).into(holder.findHeaderStore);
                    Glide.with(mContext).load(mFindTopBean.getData().get(3).getImage().getUrl()).into(holder.findHeaderHouse);
//
                    holder.findHeaderTop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnFindClickListener.findTopClick(mFindTopBean.getData().get(0).getTitle());
                        }
                    });
                    holder.findHeaderNew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnFindClickListener.findTopClick(mFindTopBean.getData().get(1).getTitle());
                        }
                    });
                    holder.findHeaderStore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("FindRvAdapter", mFindTopBean.getData().get(2).getTitle());
                            mOnFindClickListener.findTopClick(mFindTopBean.getData().get(2).getTitle());

                        }
                    });
                    holder.findHeaderHouse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnFindClickListener.findTopClick(mFindTopBean.getData().get(3).getTitle());
                        }
                    });

                }
                break;
// 一个图的
            case 2:
                if (mFindTodayBean != null && mFindTopBean != null) {
                    holder.findOneCommentCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getCommentCount()));
                    if (mFindTodayBean.getData().getFeeds().get(position).getUser() != null) {
                        holder.findOneNickNameTv.setText(mFindTodayBean.getData().getFeeds().get(position).getUser().getNickName());
                    } else {
                        holder.findOneNickNameTv.setText("");
                    }
                    holder.findOneTitleTv.setText(mFindTodayBean.getData().getFeeds().get(position).getTitle());
                    holder.findOneViewCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getViewCount()));
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(0).getUrl()).into(holder.findOneImage);
                    final int detailPos = position;
                    holder.findOneItemRL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnFindClickListener != null && mFindTodayBean.getData().getFeeds().get(detailPos).getUser() != null) {
                                mOnFindClickListener.findClick(
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getTargetId(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getFeedType(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getUser().getNickName(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getUrl(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getTitle());
                            }

                        }
                    });

                }
                break;
            // 三个图的
            case 3:
                if (mFindTodayBean != null && mFindTopBean != null) {
                    holder.findThreeCommentCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getCommentCount()));
                    if (mFindTodayBean.getData().getFeeds().get(position).getUser() != null) {
                        holder.findThreeNickNameTv.setText(mFindTodayBean.getData().getFeeds().get(position).getUser().getNickName());
                    }else {
                        holder.findThreeNickNameTv.setText("1小时前");
                    }
                    holder.findThreeTitleTv.setText(mFindTodayBean.getData().getFeeds().get(position).getTitle());
                    holder.findThreeViewCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getViewCount()));
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(0).getUrl()).into(holder.findThreeImagesOneImg);
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(1).getUrl()).into(holder.findThreeImagesTwoImg);
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(2).getUrl()).into(holder.findThreeImagesThreeImg);

                    final int detailPos = position;
                    holder.findThreeItemLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnFindClickListener != null && mFindTodayBean.getData().getFeeds().get(detailPos).getUser() != null) {
                                mOnFindClickListener.findClick(
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getTargetId(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getFeedType(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getUser().getNickName(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getUrl(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getTitle());
                            }

                        }
                    });
                }
                break;
            // 三个不同图的
            case 4:
                if (mFindTodayBean != null && mFindTopBean != null) {
                    holder.findThreeMoreCommentCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getCommentCount()));
                    holder.findThreeMoreNickNameTv.setText(mFindTodayBean.getData().getFeeds().get(position).getUser().getNickName());
                    holder.findThreeMoreTitleTv.setText(mFindTodayBean.getData().getFeeds().get(position).getTitle());
                    holder.findThreeMoreViewCountTv.setText(String.valueOf(mFindTodayBean.getData().getFeeds().get(position).getViewCount()));
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(0).getUrl()).into(holder.findThreeMoreImagesOneImg);
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(1).getUrl()).into(holder.findThreeMoreImagesTwoImg);
//                    if (mFindTodayBean.getData().getFeeds().get(position).getImages().get(2).getUrl() != null){
//
//                        Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().get(position).getImages().get(2).getUrl()).into(holder.findThreeMoreImagesThreeImg);
//                    }else {
//                        holder.findThreeMoreImagesThreeImg.setImageResource(R.mipmap.icon);
//                    }

                    final int detailPos = position;
                    holder.findThreeMoreItemRl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnFindClickListener != null && mFindTodayBean.getData().getFeeds().get(detailPos).getUser() != null) {
                                mOnFindClickListener.findClick(
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getTargetId(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getFeedType(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getUser().getNickName(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getUrl(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getTitle());
                            }

                        }
                    });


                }
                break;
            // 另外一个图
            default:
                if (mFindTodayBean != null && mFindTopBean != null) {

                    holder.findSevenTitleTv.setText(mFindTodayBean.getData().getFeeds().get(position).getTitle());

                    if (mFindTodayBean.getData().getFeeds().get(position).getUser() != null) {
                        holder.findSevenNickNameTv.setText(mFindTodayBean.getData().getFeeds().get(position).getUser().getNickName());
                    } else {
                        holder.findSevenNickNameTv.setText("");
                    }
                    Glide.with(mContext).load(mFindTodayBean.getData().getFeeds().
                            get(position).getImages().get(0).getUrl()).into(holder.findSevenIv);

                    final int detailPos = position;
                    holder.findSevenItemLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnFindClickListener != null && mFindTodayBean.getData().getFeeds().get(detailPos).getUser() != null) {
                                mOnFindClickListener.findClick(
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getTargetId(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getFeedType(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getUser().getNickName(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getImages().get(0).getUrl(),
                                        mFindTodayBean.getData().getFeeds().get(detailPos).getTitle());
                            }

                        }
                    });


                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        int num = 0;
        if (mFindTopBean != null) {
            num += 1;// 四个图标
        }
        if (mFindTodayBean != null) {
            num += mFindTodayBean.getData().getFeeds().size();
        }
        return num;
    }

    public class FindViewHolder extends RecyclerView.ViewHolder {
        private TextView findThreeTitleTv;
        private ImageView findThreeImagesOneImg;
        private ImageView findThreeImagesTwoImg;
        private ImageView findThreeImagesThreeImg;
        private TextView findThreeNickNameTv;
        private TextView findThreeViewCountTv;
        private TextView findThreeCommentCountTv;

        private ImageView findOneImage;
        private TextView findOneTitleTv;
        private TextView findOneNickNameTv;
        private TextView findOneViewCountTv;
        private TextView findOneCommentCountTv;

        private TextView findThreeMoreTitleTv;
        private ImageView findThreeMoreImagesOneImg;
        private ImageView findThreeMoreImagesTwoImg;
        private ImageView findThreeMoreImagesThreeImg;
        private TextView findThreeMoreNickNameTv;
        private TextView findThreeMoreViewCountTv;
        private TextView findThreeMoreCommentCountTv;

        private ImageView findHeaderTop;
        private ImageView findHeaderNew;
        private ImageView findHeaderStore;
        private ImageView findHeaderHouse;
        private LinearLayout findThreeItemLl;
        private RelativeLayout findOneItemRL;
        private RelativeLayout findThreeMoreItemRl;

        private LinearLayout findSevenItemLl;
        private TextView findSevenTitleTv;
        private ImageView findSevenIv;
        private TextView findSevenNickNameTv;

        public FindViewHolder(View itemView) {
            super(itemView);

            findHeaderTop = (ImageView) itemView.findViewById(R.id.find_header_top);
            findHeaderNew = (ImageView) itemView.findViewById(R.id.find_header_new);
            findHeaderStore = (ImageView) itemView.findViewById(R.id.find_header_store);
            findHeaderHouse = (ImageView) itemView.findViewById(R.id.find_header_house);

            findOneItemRL = (RelativeLayout) itemView.findViewById(R.id.find_one_item_rl);
            findOneImage = (ImageView) itemView.findViewById(R.id.find_one_iv);
            findOneTitleTv = (TextView) itemView.findViewById(R.id.find_one_title_tv);
            findOneNickNameTv = (TextView) itemView.findViewById(R.id.find_one_nick_name_tv);
            findOneViewCountTv = (TextView) itemView.findViewById(R.id.find_one_view_count_tv);
            findOneCommentCountTv = (TextView) itemView.findViewById(R.id.find_one_comment_count_tv);

            findThreeItemLl = (LinearLayout) itemView.findViewById(R.id.find_three_item_ll);

            findThreeTitleTv = (TextView) itemView.findViewById(R.id.item_find_three_title_tv);
            findThreeImagesOneImg = (ImageView) itemView.findViewById(R.id.item_find_three_iv1);
            findThreeImagesTwoImg = (ImageView) itemView.findViewById(R.id.item_find_three_iv2);
            findThreeImagesThreeImg = (ImageView) itemView.findViewById(R.id.item_find_three_iv3);
            findThreeNickNameTv = (TextView) itemView.findViewById(R.id.item_find_third_nick_name_tv);
            findThreeViewCountTv = (TextView) itemView.findViewById(R.id.item_find_third_view_count_tv);
            findThreeCommentCountTv = (TextView) itemView.findViewById(R.id.item_find_third_comment_count_tv);


            findThreeMoreItemRl = (RelativeLayout) itemView.findViewById(R.id.find_three_more_item_rl);
            findThreeMoreTitleTv = (TextView) itemView.findViewById(R.id.find_three_more_title_tv);
            findThreeMoreImagesOneImg = (ImageView) itemView.findViewById(R.id.find_three_more_one_iv);
            findThreeMoreImagesTwoImg = (ImageView) itemView.findViewById(R.id.find_three_more_two_iv);
            findThreeMoreImagesThreeImg = (ImageView) itemView.findViewById(R.id.find_three_more_three_iv);
            findThreeMoreNickNameTv = (TextView) itemView.findViewById(R.id.find_three_more_nick_name_tv);
            findThreeMoreViewCountTv = (TextView) itemView.findViewById(R.id.find_three_more_view_count_tv);
            findThreeMoreCommentCountTv = (TextView) itemView.findViewById(R.id.find_three_more_comment_count_tv);

            findSevenItemLl = (LinearLayout) itemView.findViewById(R.id.find_seven_item_ll);
            findSevenTitleTv = (TextView) itemView.findViewById(R.id.find_seven_title_tv);
            findSevenIv = (ImageView) itemView.findViewById(R.id.find_seven_iv);
            findSevenNickNameTv = (TextView) itemView.findViewById(R.id.find_seven_nick_name_tv);
        }
    }


}
