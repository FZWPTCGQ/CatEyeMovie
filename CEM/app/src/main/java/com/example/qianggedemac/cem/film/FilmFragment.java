package com.example.qianggedemac.cem.film;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.film.findfilm.FindFilmFragment;
import com.example.qianggedemac.cem.film.hot.HotFragment;
import com.example.qianggedemac.cem.film.wait.WaitFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment {


    private MyPagerAdapter mAdapter;

   private  List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.fragment_film;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {



        View decorView = getActivity().getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mFragments.add(new FindFilmFragment());
        mFragments.add(new HotFragment());
        mFragments.add(new WaitFragment());
        mAdapter = new MyPagerAdapter(getChildFragmentManager(),mFragments);
        vp.setAdapter(mAdapter);
        SlidingTabLayout tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);
        tabLayout_10.setViewPager(vp);
        


    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;
        public MyPagerAdapter(FragmentManager fm,List<Fragment> mFragments) {
            super(fm);
            this.mFragments = mFragments;
            mTitles = new ArrayList<>();
            mTitles.add("热映");
            mTitles.add("待映");
            mTitles.add("找片");
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
