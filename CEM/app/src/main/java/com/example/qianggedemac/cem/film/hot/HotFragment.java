package com.example.qianggedemac.cem.film.hot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.tool.url.UrlTools;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {

    private HotFragmentBean mHotFragmentBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        /**
         * 轮播图数据
         */
     parseBanner();
        /**
         * listView数据
         */
        parseListView();

    }

    private void parseListView() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(UrlTools.TURN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void parseBanner() {

    }

}
