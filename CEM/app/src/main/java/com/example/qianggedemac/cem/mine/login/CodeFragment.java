package com.example.qianggedemac.cem.mine.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeFragment extends BaseFragment implements View.OnClickListener {


    private EditText mCodeEt;
    private Button mCodeCheckBtn;
    private String mPhoneNum;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           int event = msg.arg1;
           int result = msg.arg2;
            if (result == SMSSDK.RESULT_COMPLETE){
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    Toast.makeText(mContext, "提交验证码成功", Toast.LENGTH_SHORT).show();
                    PasswordFragment passwordFragment = new PasswordFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("phoneNum",mPhoneNum);
                    passwordFragment.setArguments(bundle);
                    FragmentManager manager = getChildFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.register_fl,passwordFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    //发广播通知文字颜色
                    Intent intent = new Intent("colorChanged");
                    intent.putExtra("color",true);
                    mContext.sendBroadcast(intent);
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(mContext, "正在获取验证码", Toast.LENGTH_SHORT).show();
                }else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }
            }

        }
    };

    @Override
    protected int setLayout() {
        return R.layout.fragment_code;
    }

    @Override
    protected void initView(View view) {
        mCodeEt = (EditText) view.findViewById(R.id.code_et);
        mCodeCheckBtn = (Button)view.findViewById(R.id.code_check_btn);
        mCodeCheckBtn.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        SMSSDK.unregisterAllEventHandler();
        super.onDetach();
    }

    @Override
    protected void initData() {
      Bundle arguments = getArguments();
        mPhoneNum = arguments.getString("phoneNum");
        //Mob初始化
        SMSSDK.initSDK(mContext,LoginTool.APP_KEY,LoginTool.APP_SECRETE);
        final EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                super.afterEvent(i, i1, o);
                Message message = new Message();
                message.arg1 = i;
                message.arg2 = i1;
                message.obj = o;
                mHandler.sendMessage(message);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View view) {
        //将受到的验证码和手机号提交再次核对
        SMSSDK.submitVerificationCode("86",mPhoneNum,mCodeEt.getText().toString());
    }
}
