package com.example.qianggedemac.cem.find;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.OnItemClickListener;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by qianggedemac on 16/12/26.
 */

public class FindSearchAdapter extends RecyclerView.Adapter<CommonVH> {
    private FindSearchBean mFindSearchBean;
    private int layouts[] = {R.layout.find_search_detail_layout};
    private int h;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    public void setFindSearchBean(FindSearchBean findSearchBean) {
        mFindSearchBean = findSearchBean;
        List<Integer> heights = new ArrayList<>();
        Random random = new Random();
//        for (int i = 0; i < mFindSearchBean.getData().size(); i++) {
//            h = random.nextInt()
//        }
        notifyDataSetChanged();
    }



    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,layouts[viewType]);
    }

    @Override
    public void onBindViewHolder(final CommonVH holder, final int position) {
        holder.setText(R.id.find_search_detail_layout_tv,mFindSearchBean.getData().get(position).getNm()).setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyApp.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
//                int p = holder.getLayoutPosition();
//                FindSearchBean.DataBean findSearchBean = mFindSearchBean.getData().get(position);
//                mOnItemClickListener.onMethodClick(p,findSearchBean);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
