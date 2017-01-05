package com.example.qianggedemac.cem.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.find.CollectionBean;
import com.example.qianggedemac.cem.tool.CollectionDBTool;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectDetailFragment extends BaseFragment implements View.OnClickListener {


    private ImageView mImageView;
    private CollectDetailAdapter mCollectDetailAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<CollectionBean> mCollectionBeen;

    @Override
    protected int setLayout() {
        return R.layout.fragment_collect_detail;
    }

    @Override
    protected void initView(View view) {
        mImageView = (ImageView)view.findViewById(R.id.collect_back_img);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.collect_body_rv);
        mCollectDetailAdapter = new CollectDetailAdapter(mContext);
        mCollectionBeen = new ArrayList<>();
    }

    @Override
    protected void initData() {
        mImageView.setOnClickListener(this);
        CollectionDBTool.getInstance().queryAllCollectBean(new CollectionDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<CollectionBean> collectionBeen) {

                for (int i = 0; i < collectionBeen.size(); i++) {
                    CollectionBean bean = new CollectionBean();
                    bean.setNickName(collectionBeen.get(i).getNickName());
                    bean.setFeedType(collectionBeen.get(i).getFeedType());
                    bean.setTargetId(collectionBeen.get(i).getTargetId());
                    bean.setUrlImg(collectionBeen.get(i).getUrlImg());
                    bean.setTime(collectionBeen.get(i).getTime());
                    bean.setTitle(collectionBeen.get(i).getTitle());
                    mCollectionBeen.add(bean);
                }
                mCollectDetailAdapter.setCollectionBeen(mCollectionBeen);
            }
        });
        mRecyclerView.setAdapter(mCollectDetailAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApp.getContext(),1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }


    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.collect_back_img:
               /**
                * 返回上一页
                */
             getActivity().onBackPressed();
               break;
       }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        CollectionDBTool.getInstance().queryAllCollectBean(new CollectionDBTool.OnQueryListener() {
//            @Override
//            public void onQuery(List<CollectionBean> collectionBeen) {
//
//                for (int i = 0; i < collectionBeen.size(); i++) {
//                    CollectionBean bean = new CollectionBean();
//                    bean.setNickName(collectionBeen.get(i).getNickName());
//                    bean.setFeedType(collectionBeen.get(i).getFeedType());
//                    bean.setTargetId(collectionBeen.get(i).getTargetId());
//                    bean.setUrlImg(collectionBeen.get(i).getUrlImg());
//                    bean.setTime(collectionBeen.get(i).getTime());
//                    bean.setTitle(collectionBeen.get(i).getTitle());
//                    mCollectionBeen.add(bean);
//                }
//                mCollectDetailAdapter.setCollectionBeen(mCollectionBeen);
//            }
//        });
//    }
}
