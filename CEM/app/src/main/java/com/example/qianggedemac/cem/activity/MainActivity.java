package com.example.qianggedemac.cem.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.cinema.CinemaFragment;
import com.example.qianggedemac.cem.film.FilmFragment;
import com.example.qianggedemac.cem.find.FindFragment;
import com.example.qianggedemac.cem.mine.MineFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {



    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonFilm, mRadioButtonCinema, mRadioButtonFind, mRadioButtonMine;    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        /**
         * 初始化组件
         */
        addViews();

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fl, fragment);
        fragmentTransaction.commit();
    }

    private void addViews() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_rg);
        mRadioButtonFilm = (RadioButton) findViewById(R.id.main_film_rb);
        mRadioButtonCinema = (RadioButton) findViewById(R.id.main_cinema_rb);
        mRadioButtonFind = (RadioButton) findViewById(R.id.main_find_rb);
        mRadioButtonMine = (RadioButton) findViewById(R.id.main_mine_rb);
    }

    @Override
    protected void initDatas() {
         replaceFragment(new FilmFragment());
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.main_film_rb:
                  replaceFragment(new FilmFragment());
                mRadioButtonFilm.setTextColor(Color.RED);
                mRadioButtonCinema.setTextColor(Color.GRAY);
                mRadioButtonFind.setTextColor(Color.GRAY);
                mRadioButtonMine.setTextColor(Color.GRAY);
                break;
            case R.id.main_cinema_rb:
                replaceFragment(new CinemaFragment());
                 mRadioButtonFilm.setTextColor(Color.GRAY);
                mRadioButtonCinema.setTextColor(Color.RED);
                mRadioButtonFind.setTextColor(Color.GRAY);
                mRadioButtonMine.setTextColor(Color.GRAY);
                break;
            case R.id.main_find_rb:

                replaceFragment(new FindFragment());
                mRadioButtonFilm.setTextColor(Color.GRAY);
                mRadioButtonCinema.setTextColor(Color.GRAY);
                mRadioButtonFind.setTextColor(Color.RED);
                mRadioButtonMine.setTextColor(Color.GRAY);
                break;
            case R.id.main_mine_rb:

                replaceFragment(new MineFragment());
                mRadioButtonFilm.setTextColor(Color.GRAY);
                mRadioButtonCinema.setTextColor(Color.GRAY);
                mRadioButtonFind.setTextColor(Color.GRAY);
                mRadioButtonMine.setTextColor(Color.RED);
                break;
        }

    }
}
