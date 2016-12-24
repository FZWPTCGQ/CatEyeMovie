package com.example.qianggedemac.cem.film.refreshablewaitfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.film.wait.NearBean1;
import com.example.qianggedemac.cem.film.wait.RecommedBean;
import com.example.qianggedemac.cem.film.wait.WishBean;
import com.example.qianggedemac.cem.find.FindTodayBean;
import com.example.qianggedemac.cem.tool.CommonVH;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/24.
 */

public class RefreshWaitAdapter extends RecyclerView.Adapter<CommonVH>{
    private RecommedBean mRecommedBean;
    private WishBean mWishBean;
    private NearBean mNearBean;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 2;
    private int mLayoutInt[] = {R.layout.wait_fragment_adapter_item_one,
            R.layout.wait_fragment_adapter_item_two,
            R.layout.wait_fragment_adapter_item_three};


    public void setRecommedBean(RecommedBean recommedBean) {
        mRecommedBean = recommedBean;
        notifyDataSetChanged();
    }

    public void setWishBean(WishBean wishBean) {
        mWishBean = wishBean;
        Log.d("agfdgfd", "wishBean.getData().getComing().size():" + wishBean.getData().getComing().size());

        notifyDataSetChanged();
    }

    public void setNearBean(NearBean nearBean) {
        mNearBean = nearBean;
        notifyDataSetChanged();
    }
    public void addFindTodayBean(NearBean nearBean) {
        Log.d("Sysout", "size():-1" + mNearBean.getData().getMovies().size());
        mNearBean.getData().getMovies().addAll(nearBean.getData().getMovies());
//        mNearBean.getData().addData(nearBean.getData().getMovies());
        Log.d("Sysout", "size():-2" + mNearBean.getData().getMovies().size());
        notifyDataSetChanged();
    }

    public NearBean getNearBean(){
        return mNearBean;
    }



    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,mLayoutInt[viewType]);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
     int type = getItemViewType(position);
        switch (type){
            case TYPE_ONE:
               RecyclerView recyclerViewRecommend = holder.getView(R.id.wait_fragment_adapter_item_one_rv);
                WaitRefreshRecommedAdapter waitRefreshRecommedAdapter = new WaitRefreshRecommedAdapter(MyApp.getContext());
                waitRefreshRecommedAdapter.setRecommedBean(mRecommedBean);
                recyclerViewRecommend.setAdapter(waitRefreshRecommedAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApp.getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewRecommend.setLayoutManager(linearLayoutManager);
                break;
            case TYPE_TWO:
                RecyclerView recyclerViewWish = holder.getView(R.id.wait_fragment_adapter_item_two_rv);
                WaitWishRefreshAdapter waitWishRefreshAdapter = new WaitWishRefreshAdapter(MyApp.getContext());
                waitWishRefreshAdapter.setWishBean(mWishBean);
                recyclerViewWish.setAdapter(waitWishRefreshAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewWish.setLayoutManager(manager);
                break;
            case TYPE_THREE:
               // verIvNear = (ImageView)view.findViewById(R.id.wait_item_three_threeD_iv);
                Log.d("adgadfg", "mNearBean.getData().getMovies().size():" + mNearBean.getData().getMovies().size());
                holder.setText(R.id.wait_item_three_nm_tv,mNearBean.getData().getMovies().get(position).getNm());
                Log.d("gfddf", mNearBean.getData().getMovies().get(position).getNm());
                holder.setText(R.id.wait_item_three_wish_tv,mNearBean.getData().getMovies().get(position).getWish() + "人想看");
                holder.setText(R.id.wait_item_three_scm_tv,mNearBean.getData().getMovies().get(position).getScm());
                holder.setText(R.id.wait_item_three_star_tv,mNearBean.getData().getMovies().get(position).getStar());
                String url = mNearBean.getData().getMovies().get(position).getImg();
                String newUrl = url.replace("/w.h/","/165.220/");
                holder.setImage(R.id.wait_item_three_img,newUrl);
                //设置点击事件
                ImageView imageViewPlayIv = holder.getView(R.id.wait_item_three_playIv);
                imageViewPlayIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MyApp.getContext(), "播放按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        //Log.d("大小", "mNearBean.getData().getComing().size():" + mNearBean.getData().getComing().size());
        int position = 0;
        Log.d("adfadfas", "mNearBean.getData().getMovies().size():" + mNearBean.getData().getMovies().size());


        return  mNearBean.getData().getMovies().size();

    }
    @Override
    public int getItemViewType(int position) {


            return TYPE_THREE;

    }

}
