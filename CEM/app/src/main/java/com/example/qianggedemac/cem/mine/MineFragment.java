package com.example.qianggedemac.cem.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    private LinearLayout vipLinearLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        vipLinearLayout = (LinearLayout) view.findViewById(R.id.main_my_accomplishments);

    }

    @Override
    protected void initData() {
        vipLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,QrCodeActivity.class);
                startActivity(intent);
            }
        });

    }

}
