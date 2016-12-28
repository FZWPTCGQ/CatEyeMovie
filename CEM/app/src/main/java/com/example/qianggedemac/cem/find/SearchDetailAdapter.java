package com.example.qianggedemac.cem.find;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

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
    private ListView mListViewZero;
    private ListView mListViewOne;


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
                mListViewZero = holder.getView(R.id.search_layout_one_item_lv);
                SearchDetailOneAdapter searchDetailOneAdapter = new SearchDetailOneAdapter();
                searchDetailOneAdapter.setSearchDetailBean(mSearchDetailBean);
                Log.d("SearchDetailAdapter", "mSearchDetailBean.getData().get(0).getList().size():" + mSearchDetailBean.getData().get(0).getList().size());
                mListViewZero.setAdapter(searchDetailOneAdapter);
                break;
            case TYPE_ONE:
                mListViewOne = holder.getView(R.id.search_layout_two_item_lv);
                SearchDetailTwoAdapter searchDetailTwoAdapter = new SearchDetailTwoAdapter();
                searchDetailTwoAdapter.setSearchDetailBean(mSearchDetailBean);
             //   Log.d("SearchDetailAdapter", "mSearchDetailBean.getData().get(1).getLists().size():" + mSearchDetailBean.getData().get(1).getLists().size());
                mListViewOne.setAdapter(searchDetailTwoAdapter);
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
        return 3;
    }
}
