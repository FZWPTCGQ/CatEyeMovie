package com.example.qianggedemac.cem.mine.login;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.mob.commons.SMSSDK;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener {


    private EditText mPhoneEt;
    private Button mSendCodeBtn;
    private TextView mPhoneTv;
    private TextView mCodeTv;
    private TextView mPasswordTv;
    private boolean mColor;
    private ColorNotifyBroadcastReceiver mReceiver;

    @Override
    protected int setLayout() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView(View view) {
        mPhoneEt = (EditText) view.findViewById(R.id.phone_et);
        mSendCodeBtn = (Button) view.findViewById(R.id.send_code_btn);
        mPhoneTv = (TextView) view.findViewById(R.id.fragment_mine_register_phone_tv);
        mCodeTv = (TextView) view.findViewById(R.id.fragment_mine_register_code_tv);
        mPasswordTv = (TextView) view.findViewById(R.id.fragment_mine_register_password_tv);

    }

    @Override
    protected void initData() {
        //使"输入手机号"字变色,表示为当前状态
        mPhoneTv.setTextColor(0xfff27f78);
        mCodeTv.setTextColor(0xff757575);
        mPasswordTv.setTextColor(0xff757575);
        //BMob初始化
        Bmob.initialize(mContext, LoginTool.APP_ID);
        //Mob初始化
        cn.smssdk.SMSSDK.initSDK(mContext, LoginTool.APP_KEY, LoginTool.APP_SECRETE);
        //广播注册
        mReceiver = new ColorNotifyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("colorChanged");
        mContext.registerReceiver(mReceiver, intentFilter);

        mSendCodeBtn.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext.unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View view) {
        final String phoneNum = mPhoneEt.getText().toString();
        if (LoginTool.judgePhoneNum(phoneNum)) {
            BmobQuery<MyUser> query = new BmobQuery<>();
            query.addWhereEqualTo("username", phoneNum);
            query.findObjects(new FindListener<MyUser>() {
                @Override
                public void done(List<MyUser> list, BmobException e) {
                    if (e == null) {
                        cn.smssdk.SMSSDK.getVerificationCode("86", phoneNum);
                        CodeFragment codeFragment = new CodeFragment();
                        /**
                         * 利用bundle来实现fragment之间的传值
                         */
                        Bundle bundle = new Bundle();
                        bundle.putString("phoneNum",phoneNum);
                        codeFragment.setArguments(bundle);
                        FragmentManager manager = getChildFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.register_fl,codeFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        mPhoneTv.setTextColor(0xff757575);
                        mCodeTv.setTextColor(0xfff27f78);
                        mPasswordTv.setTextColor(0xff757575);
                    }else{
                        Toast.makeText(mContext, "该手机号码已经注册过", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mContext, "发送失败", Toast.LENGTH_SHORT).show();
        }
    }

    public class ColorNotifyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mColor = intent.getBooleanExtra("color", false);
            if (mColor) {
                mPhoneTv.setTextColor(0xff757575);
                mCodeTv.setTextColor(0xff757575);
                mPasswordTv.setTextColor(0xfff27f78);
            }
        }
    }

}
