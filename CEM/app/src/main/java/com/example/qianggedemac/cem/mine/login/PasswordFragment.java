package com.example.qianggedemac.cem.mine.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends BaseFragment implements View.OnClickListener {

    private EditText mPwdEt;
    private EditText mPwdagainEt;
    private Button mPasswordBtn;
    private String mPhoneNum;
    private String mPwd;
    private String mPwdAgain;

    @Override
    protected int setLayout() {
        return R.layout.fragment_password;
    }

    @Override
    protected void initView(View view) {
        mPwdEt = (EditText)view.findViewById(R.id.password_et);
        mPwdagainEt = (EditText)view.findViewById(R.id.password_again_et);
        mPasswordBtn = (Button)view.findViewById(R.id.password_btn);

    }

    @Override
    protected void initData() {
      mPasswordBtn.setOnClickListener(this);
        Bmob.initialize(mContext,LoginTool.APP_ID);
    }

    @Override
    public void onClick(View view) {
     Bundle arguments = getArguments();
        mPhoneNum = arguments.getString("phoneNum");
        //获取输入框内容
        mPwd = mPwdEt.getText().toString();
        mPwdAgain = mPwdagainEt.getText().toString();
        //当输入框内容不为空时
        if (!mPwd.equals(null) && !mPwd.equals("")){
            if (!mPwd.equals(mPwdAgain)){
                Toast.makeText(mContext, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                mPwdEt.setText("");
                mPwdagainEt.setText("");
            }else{
                MyUser myUser = new MyUser();
                myUser.setUsername(mPhoneNum);
                myUser.setPassword(mPwd);
                //随机获取一张图片
                Random random = new Random();
                int num = random.nextInt(7);
                myUser.setIcon(LoginTool.icon[num]);
                myUser.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null){
                            Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                            MyUser user = BmobUser.getCurrentUser(MyUser.class);
                            Intent intent = new Intent("sendInfo");
                            intent.putExtra("objectId",user.getObjectId());
                            mContext.sendBroadcast(intent);
                            getActivity().onBackPressed();
                        }
                    }
                });
            }
        }
    }
}
