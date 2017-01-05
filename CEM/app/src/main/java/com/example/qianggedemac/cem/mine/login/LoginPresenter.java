package com.example.qianggedemac.cem.mine.login;

import android.text.TextUtils;

/**
 * Created by qianggedemac on 17/1/5.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private LoginContract.Model mModel;

    public LoginPresenter(LoginContract.View view, LoginContract.Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void login(String userName, String password) {
        if (checkIsEmpty(userName,password)){
            mView.showEmptyMsg();
        }else{
            mModel.login(userName, password);
        }
    }

    @Override
    public boolean checkIsEmpty(String userName, String password) {
        return (TextUtils.isEmpty(userName)) || (TextUtils.isEmpty(password));
    }

    @Override
    public void loginSuccess() {
      mView.loginSuccess();
    }

    @Override
    public void loginError(Exception exception) {
     if (exception == null){
         mView.loginError("登录失败");
     }else{
         mView.loginError(exception.getMessage());
     }
    }
}
