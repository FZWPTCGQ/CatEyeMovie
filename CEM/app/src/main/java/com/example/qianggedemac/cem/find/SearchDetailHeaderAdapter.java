package com.example.qianggedemac.cem.find;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/29.
 */

public class SearchDetailHeaderAdapter extends RecyclerView.Adapter<CommonVH> {
    private SearchHeaderBean mSearchHeaderBean;
    private int layoutType[] = {R.layout.s_o_layout,R.layout.search_detail_one_detail_item};
    private static final int TYPE_ZERO = 0;
    private static final int TYPE_ONE = 1;
    public void setSearchHeaderBean(SearchHeaderBean searchHeaderBean) {
        mSearchHeaderBean = searchHeaderBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,layoutType[viewType]);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_ZERO:
                holder.setText(R.id.s_o_layout_tv,"电影/电视剧");
                break;
            case TYPE_ONE:
                holder.setText(R.id.search_detail_one_detail_item_nm_tv,mSearchHeaderBean.getData().get(0).getList().get(position - 1).getNm());
                holder.setText(R.id.search_detail_one_detail_item_enm_tv,mSearchHeaderBean.getData().get(0).getList().get(position - 1).getEnm());
                holder.setText(R.id.search_detail_one_detail_item_cat_tv,mSearchHeaderBean.getData().get(0).getList().get(position - 1).getCat());
                holder.setText(R.id.search_detail_one_detail_item_pubDesc_tv,mSearchHeaderBean.getData().get(0).getList().get(position - 1).getPubDesc());
                String url = mSearchHeaderBean.getData().get(0).getList().get(position - 1).getImg();
                String newUrl = url.replace("/w.h/","/165.220/");
                ImageView imageView = holder.getView(R.id.search_detail_one_detail_item_img_iv);
                Glide.with(MyApp.getContext()).load(newUrl).into(imageView);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_ZERO;
        }else {
            return TYPE_ONE;
        }
    }

    @Override
    public int getItemCount() {
        return mSearchHeaderBean.getData().get(0).getList().size() + 1;
    }
}
