package com.example.qianggedemac.cem.film;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.activity.MainActivity;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.film.city.CityFragment;
import com.example.qianggedemac.cem.film.findfilm.MoiveFindFragment;
import com.example.qianggedemac.cem.film.hot.HotFragment;
import com.example.qianggedemac.cem.film.refreshablewaitfragment.RefreshableWaitFragment;
import com.example.qianggedemac.cem.film.wait.WaitFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment implements View.OnClickListener {


    private MyPagerAdapter mAdapter;

   private  List<Fragment> mFragments = new ArrayList<>();
    private TextView mLocationTextView;
    private MainActivity mMainActivity;
    private CityFragment mCityFragment;

    @Override
    protected int setLayout() {
        return R.layout.fragment_film;
    }

    @Override
    protected void initView(View view) {
        mLocationTextView = (TextView)view.findViewById(R.id.fragment_film_address_tv);
        mCityFragment = new CityFragment();
    }

    @Override
    protected void initData() {
        View decorView = getActivity().getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.fragment_film_vp);
        mFragments.add(new HotFragment());
        mFragments.add(new RefreshableWaitFragment());
        mFragments.add(new MoiveFindFragment());
        mAdapter = new MyPagerAdapter(getChildFragmentManager(),mFragments);
        vp.setAdapter(mAdapter);
        SlidingTabLayout tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);
        tabLayout_10.setViewPager(vp);
        mLocationTextView.setOnClickListener(this);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("cityName", Context.MODE_PRIVATE);
        String textView = sharedPreferences.getString("cityName","大连");
        mLocationTextView.setText(textView);
        mCityFragment.setOnSelectCity(new CityFragment.OnSelectCity() {
            @Override
            public void selectCityName(String name, String cityId) {
                mLocationTextView.setText(name);
                SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("cityName",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("cityName",name);
                editor.putString("cityId",cityId);
                editor.commit();
            }
        });


    }

    @Override
    public void onClick(View view) {
        mMainActivity = (MainActivity) getActivity();
        switch (view.getId()){
            case R.id.fragment_film_address_tv:
                CityFragment cityFragment = new CityFragment();
                mMainActivity.jumpFragment(cityFragment);
                break;
        }
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
