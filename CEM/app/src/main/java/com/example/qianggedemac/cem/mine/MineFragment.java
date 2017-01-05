package com.example.qianggedemac.cem.mine;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.activity.MainActivity;
import com.example.qianggedemac.cem.baseclass.BaseFragment;
import com.example.qianggedemac.cem.mine.login.LoginFragment;
import com.example.qianggedemac.cem.mine.login.LoginModel;
import com.example.qianggedemac.cem.mine.login.LoginPresenter;
import com.example.qianggedemac.cem.mine.login.LoginTool;
import com.example.qianggedemac.cem.mine.login.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout vipLinearLayout;
    private LinearLayout mLinearLayoutCollection;
    private CollectDetailFragment mCollectDetailFragment;
    private TextView mTextViewLogin;
    private MainActivity mMainActivity;
    private LoginFragment mLoginFragment;
    private Button mQuitBtn;
    private ImageView mIconTv;
    private LinearLayout mLoginLl;
    private LoginSuccessBroadcastReceiver mReceiver;
    private LinearLayout mMember;


    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        vipLinearLayout = (LinearLayout) view.findViewById(R.id.main_my_accomplishments);
        mLinearLayoutCollection = (LinearLayout) view.findViewById(R.id.main_my_collection);
        mTextViewLogin = (TextView) view.findViewById(R.id.main_Sign_in);
        mQuitBtn = (Button)view.findViewById(R.id.fragment_mine_quit_btn);
        mIconTv = (ImageView)view.findViewById(R.id.main_head_portrait);
        mLoginLl = (LinearLayout)view.findViewById(R.id.fragment_mine_login_ll);
        mMember = (LinearLayout) view.findViewById(R.id.mine_member_conter);
    }

    @Override
    protected void initData() {
        /**
         * 注册广播
         */
        mReceiver = new LoginSuccessBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("sendInfo");
        mContext.registerReceiver(mReceiver,filter);
        //BMob初始化
        Bmob.initialize(mContext, LoginTool.APP_ID);
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if (myUser != null){
            //允许用户使用应用
            mTextViewLogin.setText(myUser.getUsername());
            Glide.with(mContext).load(myUser.getIcon()).into(mIconTv);
            mQuitBtn.setVisibility(View.VISIBLE);
            mLoginLl.setClickable(false);
        }else{
            //缓存用户对象为空时,可打开用户注册界面
            mTextViewLogin.setText("立即登录");
            mIconTv.setImageResource(R.mipmap.acm);
            mQuitBtn.setVisibility(View.GONE);
            mLoginLl.setClickable(true);
        }
        /**
         *
         */
        vipLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QrCodeActivity.class);
                startActivity(intent);

            }
        });
        mMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(mContext,MvActivity.class);
              //  startActivity(intent);
            }
        });
        mLinearLayoutCollection.setOnClickListener(this);
        mTextViewLogin.setOnClickListener(this);
        mQuitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mMainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            //收藏
            case R.id.main_my_collection:
                Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                mCollectDetailFragment = new CollectDetailFragment();

                mMainActivity.jumpFragment(mCollectDetailFragment);
                break;
            //登录按钮
            case R.id.main_Sign_in:
                Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                mLoginFragment = new LoginFragment();
                LoginModel loginModel = new LoginModel();
                LoginPresenter loginPresenter = new LoginPresenter(mLoginFragment,loginModel);
                mLoginFragment.setPresenter(loginPresenter);
                loginModel.setPresenter(loginPresenter);
                mMainActivity.jumpFragment(mLoginFragment);
                break;
            //退出内容
            case R.id.fragment_mine_quit_btn:
                //清除缓存用户对象
                BmobUser.logOut();
                mTextViewLogin.setText("登录");
                mIconTv.setImageResource(R.mipmap.acm);
                mQuitBtn.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext.unregisterReceiver(mReceiver);
    }

    public class LoginSuccessBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String objectId = intent.getStringExtra("objectId");
            if (objectId != null){
                mLoginLl.setClickable(false);
                BmobQuery<MyUser> bmobQuery = new BmobQuery<>();
                bmobQuery.getObject(objectId, new QueryListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null){
                            mTextViewLogin.setText(myUser.getUsername());
                            Glide.with(mContext).load(myUser.getIcon()).into(mIconTv);
                        }else{
                            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mQuitBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}
