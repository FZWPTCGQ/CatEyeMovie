package com.example.qianggedemac.cem.baseclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by qianggedemac on 16/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initViews();
        initDatas();
    }
    protected abstract int setLayout();
    protected abstract void initViews();
    protected abstract void initDatas();
    public   <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }
}
