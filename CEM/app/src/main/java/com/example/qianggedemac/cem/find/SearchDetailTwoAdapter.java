package com.example.qianggedemac.cem.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/27.
 */

public class SearchDetailTwoAdapter extends BaseAdapter {
    private SearchDetailBean mSearchDetailBean;
    private static final int TYPE_ZERO = 0;
    private static final int TYPE_ONE = 1;
    private static final int COUNT = 100;


    public void setSearchDetailBean(SearchDetailBean searchDetailBean) {
        mSearchDetailBean = searchDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSearchDetailBean.getData().get(1).getList().size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return mSearchDetailBean.getData().get(1).getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ZTViewHolder ztViewHolder = null;
        STViewHolder stViewHolder = null;
        int type = getItemViewType(i);
        switch (type){
            case TYPE_ZERO:
                if (view == null){
                    view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.s_o_layout,viewGroup,false);
                    ztViewHolder = new ZTViewHolder(view);
                    view.setTag(ztViewHolder);
                }else{
                    ztViewHolder = (ZTViewHolder) view.getTag();
                }
                ztViewHolder.mTextViewZT.setText("资讯");
                break;
            case TYPE_ONE:
                if (view == null){
                    view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.search_detail_two_detail_item,viewGroup,false);
                    stViewHolder = new STViewHolder(view);
                    view.setTag(stViewHolder);
                }else{
                    stViewHolder = (STViewHolder) view.getTag();
                }
                stViewHolder.mTextViewTitle.setText(mSearchDetailBean.getData().get(1).getList().get(i -1).getTitle());
                stViewHolder.mTextViewCommentCount.setText(mSearchDetailBean.getData().get(1).getList().get(i - 1).getCommentCount() + "");
                stViewHolder.mTextViewViewCountTv.setText(mSearchDetailBean.getData().get(1).getList().get(i - 1).getViewCount() + "");
                break;
        }
        return view;
    }
    public class ZTViewHolder{
        TextView mTextViewZT;
        public ZTViewHolder(View view){
            mTextViewZT = (TextView)view.findViewById(R.id.s_o_layout_tv);
        }
    }
    public class STViewHolder{
        TextView mTextViewTitle,mTextViewViewCountTv,mTextViewCommentCount;
        public STViewHolder(View view){
            mTextViewTitle = (TextView)view.findViewById(R.id.search_detail_two_detail_item_title_tv);
            mTextViewViewCountTv = (TextView)view.findViewById(R.id.search_detail_two_detail_item_liulan_tv);
            mTextViewCommentCount = (TextView)view.findViewById(R.id.search_detail_two_detail_item_pinglun_tv);

        }
    }
}
