package com.example.qianggedemac.cem.mine.login;

import android.content.Intent;

import com.example.qianggedemac.cem.tool.myapp.MyApp;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by qianggedemac on 17/1/5.
 */

public class LoginModel implements LoginContract.Model{
    private LoginContract.Presenter mPresenter;
    //进行耗时操作
    @Override
    public void login(final String userName, final String password) {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                 query.addWhereEqualTo("username",userName);
                 query.findObjects(new FindListener<MyUser>() {
                     @Override
                     public void done(List<MyUser> list, BmobException e) {
                         if (e == null){
                             //查询用户成功
                             //使用手机号码 + 密码登录
                             BmobUser.loginByAccount(userName, password, new LogInListener<MyUser>() {
                                 @Override
                                 public void done(MyUser myUser, BmobException e) {
                                     if (myUser != null){
                                         mPresenter.loginSuccess();
                                         MyUser user = BmobUser.getCurrentUser(MyUser.class);
                                         Intent intent = new Intent("sendInfo");
                                         intent.putExtra("objectId",user.getObjectId());
                                         MyApp.getContext().sendBroadcast(intent);
                                     }else{
                                         //自定义登录错误提示信息
                                         Exception exception = new Exception("用户名/密码错误");
                                         mPresenter.loginError(exception);
                                     }
                                 }
                             });
                         }else{
                             //查询用户失败
                             Exception exception = new Exception("查询用户失败");
                             mPresenter.loginError(exception);
                         }
                     }
                 });
             }
         }).start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
    mPresenter = presenter;
    }


}
