package com.example.qianggedemac.cem.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/27.
 */

public class SearchDetailAdapter extends BaseAdapter {
    private SearchHeaderBean mSearchHeaderBean;
    private Context mContext;

    public SearchDetailAdapter(Context context) {
        mContext = context;
    }
    public void clear(){
        mSearchHeaderBean.getData().get(1).getList().clear();
    }

    public void setSearchHeaderBean(SearchHeaderBean searchHeaderBean) {
        mSearchHeaderBean = searchHeaderBean;
        notifyDataSetChanged();
    }
    public void addSearchHeaderBean(SearchHeaderBean searchHeaderBean){
        mSearchHeaderBean.getData().get(1).getList().addAll(searchHeaderBean.getData().get(0).getList());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSearchHeaderBean.getData().get(1).getList().size();
    }

    @Override
    public Object getItem(int i) {
        return mSearchHeaderBean.getData().get(1).getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       SDViewHolder sdViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.search_detail_two_detail_item,viewGroup,false);
            sdViewHolder = new SDViewHolder(view);
            view.setTag(sdViewHolder);
        }else{
            sdViewHolder = (SDViewHolder) view.getTag();
        }
        sdViewHolder.mTextViewTilte.setText(mSearchHeaderBean.getData().get(1).getList().get(i).getTitle());
        sdViewHolder.mTextViewVideoCount.setText(mSearchHeaderBean.getData().get(1).getList().get(i).getViewCount() + "");
        sdViewHolder.mTextViewCommentCount.setText(mSearchHeaderBean.getData().get(1).getList().get(i).getCommentCount() + "");
        return view;
    }
    public class SDViewHolder{
        TextView mTextViewTilte,mTextViewVideoCount,mTextViewCommentCount;
        public SDViewHolder(View view){
            mTextViewTilte = (TextView)view.findViewById(R.id.search_detail_two_detail_item_title_tv);
            mTextViewVideoCount = (TextView)view.findViewById(R.id.search_detail_two_detail_item_liulan_tv);
            mTextViewCommentCount = (TextView)view.findViewById(R.id.search_detail_two_detail_item_pinglun_tv);

        }
    }
}
