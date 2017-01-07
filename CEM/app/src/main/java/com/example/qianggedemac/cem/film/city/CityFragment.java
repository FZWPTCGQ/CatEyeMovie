package com.example.qianggedemac.cem.film.city;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityFragment extends BaseFragment {

    private SideBar mSideBar;
    private Gson mGson;
    private RecyclerView mRecyclerView;
    private ScrollSpeedLinearLayoutManger mManager;
    private CityAdapter mAdapter;
    private TitleItemDecoration mDecoration;
    private List<CityBean.CtsBean> mDatas;
    private ArrayList<CityBean.CtsBean> mCtsBeanArrayList;
    private OnSelectCity mOnSelectCity;
    private TextView mBuddle;

    public void setOnSelectCity(OnSelectCity onSelectCity) {
        mOnSelectCity = onSelectCity;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mSideBar = (SideBar) view.findViewById(R.id.sidebar);
        mBuddle = (TextView) view.findViewById(R.id.bubble);
    }

    @Override
    protected void initData() {
        mSideBar.setOnMoveSideBar(new SideBar.OnMoveSideBar() {
            @Override
            public void onMove(float cellHeight, String character, int index) {
                //傍边的红色的提示字母
                mBuddle.setVisibility(View.VISIBLE);
                mBuddle.setY(cellHeight * index);
                mBuddle.setText(character);
                //优化部分
                long startTime = System.currentTimeMillis();
                int currentPosition = getFirstLetterCount(character);
                if (currentPosition != mCtsBeanArrayList.size()){
                    mRecyclerView.smoothScrollToPosition(currentPosition + 3);
                }
            }

            @Override
            public void onKeyDown(float cellHeight, String character, int index) {
                mBuddle.setVisibility(View.VISIBLE);
                mBuddle.setY(cellHeight * index);
                mBuddle.setText(character);
                if (character.equals("#")){
                    mRecyclerView.scrollToPosition(0);
                    return;
                }
                int currentPosition = getFirstLetterCount(character);
                if (currentPosition != mCtsBeanArrayList.size()){
                    mRecyclerView.scrollToPosition(currentPosition + 4);
                }
            }

            @Override
            public void onKeyUp(float cellHeight, String character, int index) {
                mBuddle.setVisibility(View.INVISIBLE);
                int currentPosition = getFirstLetterCount(character);
                if (currentPosition != mCtsBeanArrayList.size()){

                }
            }
        });

        getData();
        mRecyclerView.setLayoutManager(mManager = new ScrollSpeedLinearLayoutManger(mContext));
        mManager.setSpeedFast();
        mAdapter = new CityAdapter();
        mAdapter.setDatas(getData());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnSelectCity(new OnSelectCity() {
            @Override
            public void selectCityName(String name, String cityId) {

                EventBus.getDefault().post(new CityMessage(cityId,name));
                mOnSelectCity.selectCityName(name,cityId);
                getActivity().onBackPressed();
            }
        });
        mRecyclerView.addItemDecoration(mDecoration = new TitleItemDecoration(mContext, mCtsBeanArrayList));
        //如果add两个，那么按照先后顺序，依次渲染。
        // mRecyclerView.addItemDecoration(new TitleItemDecoration2(this, mCtsBeanArrayList));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

    }
    private ArrayList<CityBean.CtsBean> getData() {
        //获取
        InputStream inputStream = mContext.getClassLoader().getResourceAsStream("assets/" + "cities.json");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mGson = new Gson();
        CityBean cityBean = mGson.fromJson(stringBuilder.toString(),CityBean.class);
        Toast.makeText(mContext, "cityBean.getCts().get(1).getId():" + cityBean.getCts().get(1).getId(), Toast.LENGTH_SHORT).show();
        mCtsBeanArrayList = new ArrayList<>();
        for (int i = 0; i < cityBean.getCts().size(); i++) {
            String firstLetter = cityBean.getCts().get(i).getPy().substring(0,1).toUpperCase();
            cityBean.getCts().get(i).setFirstLetter(firstLetter);
            mCtsBeanArrayList.add(cityBean.getCts().get(i));
        }
        Collections.sort(mCtsBeanArrayList,new PinyinComparator());
        for (int i = 0; i < cityBean.getCts().size(); i++) {

        }
        CityBean.CtsBean hotBean = new CityBean.CtsBean();
        hotBean.setFirstLetter("   #");
        mCtsBeanArrayList.add(0,hotBean);
        mCtsBeanArrayList.add(1,hotBean);
        mCtsBeanArrayList.add(2,hotBean);
        return mCtsBeanArrayList;
    }
    /**
     * 获取当前字母之前的position
     */
    private int getFirstLetterCount(String character){
        int count = 0;
        for (int i = 0; i < mCtsBeanArrayList.size(); i++) {
            if (!character.equals(mCtsBeanArrayList.get(i).getFirstLetter())){
                count++;
            }else{
                break;
            }
        }
        return count;
    }
    public interface OnSelectCity{
        void selectCityName(String name,String cityId);
    }

}
