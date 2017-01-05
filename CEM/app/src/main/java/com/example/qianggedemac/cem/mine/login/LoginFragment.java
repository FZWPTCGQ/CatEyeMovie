package com.example.qianggedemac.cem.mine.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.activity.MainActivity;
import com.example.qianggedemac.cem.baseclass.BaseFragment;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, LoginContract.View {


    private TextView mMRegisterTv;
    private EditText mMPhoneEt;
    private EditText mMPasswordEt;
    private Button mLoginBtn;
    private ImageView mMLoginHeadIv;
    private String mPhoneNum;
    private String mPassword;
    private MainActivity mActivity;
    private RegisterFragment mRegisterFragment;
    private LoginContract.Presenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view) {
        mMRegisterTv = (TextView)view.findViewById(R.id.register_tv);
        mMPhoneEt = (EditText)view.findViewById(R.id.fragment_mine_login_phone_et);
        mMPasswordEt = (EditText)view.findViewById(R.id.fragment_mine_login_password_et);
        mLoginBtn = (Button)view.findViewById(R.id.fragment_mine_login_btn);
        mMLoginHeadIv = (ImageView)view.findViewById(R.id.fragment_mine_login_head_iv);
        mMRegisterTv.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        //Bmob初始化
        Bmob.initialize(mContext,LoginTool.APP_ID);
        //监听账号输入框的状态
        mMPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMLoginHeadIv.setImageResource(R.mipmap.acm);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  mMLoginHeadIv.setImageResource(R.mipmap.acm);
            }

            @Override
            public void afterTextChanged(Editable editable) {
              //判断手机号码符合11位,在Bmob上查询用户信息
                if (editable.length() == 11){
                    //查询用户是否存在
                    BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                    query.addWhereEqualTo("username",editable);
                    query.findObjects(new FindListener<MyUser>() {
                        @Override
                        public void done(List<MyUser> list, BmobException e) {
                            if (e == null){
                                for (MyUser user : list) {
                                    String icon = user.getIcon();
                                    Glide.with(mContext).load(icon).into(mMLoginHeadIv);
                                }
                            }else{
                                mMLoginHeadIv.setImageResource(R.mipmap.acm);
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        mPhoneNum = mMPhoneEt.getText().toString();
        mPassword = mMPasswordEt.getText().toString();
        switch (view.getId()){
            case R.id.register_tv:
                mActivity = (MainActivity) mContext;
                mRegisterFragment = new RegisterFragment();
                mActivity.jumpFragment(mRegisterFragment);
                break;
            case R.id.fragment_mine_login_btn:
                //将值传到Presenter
                mPresenter.login(mPhoneNum,mPassword);
                break;
        }
    }

    @Override
    public void showEmptyMsg() {
        Toast.makeText(mContext, "手机号码/密码为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
      getActivity().onBackPressed();
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
    mPresenter = presenter;
    }
}
