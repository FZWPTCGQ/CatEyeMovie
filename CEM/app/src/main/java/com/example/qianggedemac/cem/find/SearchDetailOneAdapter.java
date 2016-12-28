package com.example.qianggedemac.cem.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/27.
 */

public class SearchDetailOneAdapter extends BaseAdapter {

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
        return mSearchDetailBean.getData().get(0).getList().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ZERO;
        } else {
            return TYPE_ONE;
        }

    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    @Override
    public Object getItem(int i) {

        return mSearchDetailBean.getData().get(0).getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SOViewHolder soViewHolder = null;
        ZViewHolder zViewHolder = null;
        int type = getItemViewType(i);
        switch (type) {
            case TYPE_ZERO:
               if (view == null){

                view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.s_o_layout, viewGroup, false);
                zViewHolder = new ZViewHolder(view);
                view.setTag(zViewHolder);
               }else{
                   zViewHolder = (ZViewHolder) view.getTag();
               }
                zViewHolder.mTextViewT.setText("电影/电视剧");
                break;
            case TYPE_ONE:
                if (view == null) {
                    view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.search_detail_one_detail_item, viewGroup, false);
                    soViewHolder = new SOViewHolder(view);
                    view.setTag(soViewHolder);
                } else {
                    soViewHolder = (SOViewHolder) view.getTag();
                }
                soViewHolder.mTextViewNm.setText(mSearchDetailBean.getData().get(0).getList().get(i - 1).getNm());
                soViewHolder.mTextViewEnm.setText(mSearchDetailBean.getData().get(0).getList().get(i - 1).getEnm());
                soViewHolder.mTextViewCat.setText(mSearchDetailBean.getData().get(0).getList().get(i - 1).getCat());
                soViewHolder.mTextViewPubDesc.setText(mSearchDetailBean.getData().get(0).getList().get(i - 1).getPubDesc());
                String url = mSearchDetailBean.getData().get(0).getList().get(i - 1).getImg().replace("/w.h/", "/165.220/");
                Glide.with(MyApp.getContext()).load(url).into(soViewHolder.mImageViewImg);
                break;
        }
        return view;
    }
    public class ZViewHolder{
        TextView mTextViewT;
        public ZViewHolder(View view){
            mTextViewT = (TextView)view.findViewById(R.id.s_o_layout_tv);
        }
    }

    public class SOViewHolder {
        ImageView mImageViewImg;
        TextView mTextViewNm, mTextViewEnm, mTextViewCat, mTextViewPubDesc;

        public SOViewHolder(View view) {
            mImageViewImg = (ImageView) view.findViewById(R.id.search_detail_one_detail_item_img_iv);
            mTextViewNm = (TextView) view.findViewById(R.id.search_detail_one_detail_item_nm_tv);
            mTextViewEnm = (TextView) view.findViewById(R.id.search_detail_one_detail_item_enm_tv);
            mTextViewCat = (TextView) view.findViewById(R.id.search_detail_one_detail_item_cat_tv);
            mTextViewPubDesc = (TextView) view.findViewById(R.id.search_detail_one_detail_item_pubDesc_tv);
        }
    }
}
