package com.example.qianggedemac.cem.find;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;

/**
 * Created by qianggedemac on 16/12/27.
 */

public class SearchDetailAdapter extends RecyclerView.Adapter<CommonVH> {
    private SearchDetailBean mSearchDetailBean;
    private int layoutParams[] = {R.layout.search_layout_one_item,R.layout.search_layout_two_item};
    private static final int TYPE_ZERO = 0;
    private static final int TYPE_ONE = 1;


    public void setSearchDetailBean(SearchDetailBean searchDetailBean) {
        mSearchDetailBean = searchDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,layoutParams[viewType]);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_ZERO:

                break;
            case TYPE_ONE:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_ZERO;
        }else{
            return TYPE_ONE;
        }
    }

    @Override
    public int getItemCount() {
        return mSearchDetailBean.getData().size();
    }
}
