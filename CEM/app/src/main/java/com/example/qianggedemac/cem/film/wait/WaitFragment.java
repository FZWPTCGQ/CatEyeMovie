package com.example.qianggedemac.cem.film.wait;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitFragment extends BaseFragment {


    private StickyListHeadersListView mStickyListHeadersListView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_wait;
    }

    @Override
    protected void initView(View view) {
        mStickyListHeadersListView = (StickyListHeadersListView)view.findViewById(R.id.fragment_wait_stickyListHeaderListView);

    }

    @Override
    protected void initData() {

    }

}
