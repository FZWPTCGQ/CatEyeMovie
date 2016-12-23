package com.example.qianggedemac.cem.film.findfilm;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.UrlTools;
import com.example.qianggedemac.cem.tool.oktools.NetCallBack;
import com.example.qianggedemac.cem.tool.oktools.OkHttpManager;

/**
 * Created by qianggedemac on 16/12/23.
 */

public class MoiveFindAdapter extends RecyclerView.Adapter<CommonVH> {
    private Context mContext;
    private FindTypeBean mFindTypeBean;
    private ClassifyBean mClassifyBean;
    private RecyclerView mRecyclerView;
    /**
     * 加载不同行布局的数组
     */
    private int[] mItemLayouts = {R.layout.movie_find_zero, R.layout.movie_find_one, R.layout.movie_find_two,
            R.layout.movie_find_three, R.layout.movie_find_four};


    public MoiveFindAdapter(Context context) {
        mContext = context;
    }

    public void setFindTypeBean(FindTypeBean findTypeBean) {
        mFindTypeBean = findTypeBean;
        notifyDataSetChanged();
    }

    public void setClassifyBean(ClassifyBean classifyBean) {
        mClassifyBean = classifyBean;
        notifyDataSetChanged();
    }



    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,mItemLayouts[viewType]);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        int pos = getItemViewType(position);
        switch (pos) {
            case 0:
                if (mFindTypeBean != null) {
                    LinearLayout llType = holder.getView(R.id.movie_find_type);
                    LinearLayout llCenter = holder.getView(R.id.movie_find_where);
                    LinearLayout llAll = holder.getView(R.id.movie_find_when);
                    for (int j = 0; j < 3; j++) {
                        for (int i = 0; i < mFindTypeBean.getData().get(j).getTagList().size(); i++) {
                            Button button = new Button(holder.getItemView().getContext());
                            button.setBackground(mContext.getResources().getDrawable(R.drawable.button_style));
                            button.setPadding(5,0,5,0);
                            button.setTextSize(12f);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(5,0,5,0);//4个参数按顺序分别是左上右下
                            button.setLayoutParams(layoutParams);
                            button.setText(mFindTypeBean.getData().get(j).getTagList().get(i).getTagName());
                            if (j == 0){
                                llType.addView(button);
                            }else if (j == 1){
                                llCenter.addView(button);
                            }else {
                                llAll.addView(button);
                            }
                        }
                    }

                }
                break;
            case 1:
                if (mClassifyBean != null) {
                    holder.setText(R.id.movie_find_center_board_name_one, mClassifyBean.getData().get(0).getBoardName());
                    holder.setText(R.id.movie_find_center_movie_name_one, mClassifyBean.getData().get(0).getMovieName());
                    holder.setText(R.id.movie_find_center_board_name_two, mClassifyBean.getData().get(1).getBoardName());
                    holder.setText(R.id.movie_find_center_movie_name_two, mClassifyBean.getData().get(1).getMovieName());
                    holder.setText(R.id.movie_find_center_board_name_three, mClassifyBean.getData().get(2).getBoardName());
                    holder.setText(R.id.movie_find_center_movie_name_three, mClassifyBean.getData().get(2).getMovieName());
                    holder.setText(R.id.movie_find_center_board_name_four, mClassifyBean.getData().get(3).getBoardName());
                    holder.setText(R.id.movie_find_center_movie_name_four, mClassifyBean.getData().get(3).getMovieName());
                    holder.setImage(R.id.movie_find_center_movie_img_one, mClassifyBean.getData().get(0).getMovieImgs().get(1).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_two, mClassifyBean.getData().get(1).getMovieImgs().get(1).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_three, mClassifyBean.getData().get(2).getMovieImgs().get(1).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_four, mClassifyBean.getData().get(3).getMovieImgs().get(1).replace("w.h","165.220"));

                    holder.setImage(R.id.movie_find_center_movie_img_one_before, mClassifyBean.getData().get(0).getMovieImgs().get(0).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_two_before, mClassifyBean.getData().get(1).getMovieImgs().get(0).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_three_before, mClassifyBean.getData().get(2).getMovieImgs().get(0).replace("w.h","165.220"));
                    holder.setImage(R.id.movie_find_center_movie_img_four_before, mClassifyBean.getData().get(3).getMovieImgs().get(0).replace("w.h","165.220"));

                }
                break;
            case 2:
                break;
            case 3:
                mRecyclerView = holder.getView(R.id.movie_find_all_rv);
                 final FindAllAdapter adapter = new FindAllAdapter(mContext);
//                HttpUtil.getMovieFindAllPrize(new ResponseCallBack<MovieFindAllPrizeBean>() {
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                    @Override
//                    public void onResponse(MovieFindAllPrizeBean movieFindAllPrizeBean) {
//                        mRv.setAdapter(adapter);
//                        adapter.setMovieFindAllPrizeBean(movieFindAllPrizeBean);
//                        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getmContext(), LinearLayoutManager.HORIZONTAL, false);
//                        mRv.setLayoutManager(manager);
//                    }
               // });
                OkHttpManager.getInstance().get(UrlTools.MOVIE_FIND_ALL_PRIZE, AllPrizeBean.class, new NetCallBack<AllPrizeBean>() {
                    @Override
                    public void onResponse(AllPrizeBean bean) {
                        adapter.setMovieFindAllPrizeBean(bean);
                        mRecyclerView.setAdapter(adapter);
                        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                        mRecyclerView.setLayoutManager(manager);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            case 4:
                holder.setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("MovieFindAdapter", "点击了");
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 4){
            return 4;
        }else{
            return position;
        }
    }
}
