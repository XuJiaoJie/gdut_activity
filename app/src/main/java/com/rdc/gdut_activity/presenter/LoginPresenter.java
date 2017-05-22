package com.rdc.gdut_activity.presenter;

import android.util.Log;

import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.model.LoginUserModel;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;

import cn.bmob.v3.BmobUser;

/**
 * Created by ThatNight on 2017.4.26.
 */

public class LoginPresenter {
    private ILoginView mLoginView;
    private LoginUserModel mUserModel;

    public LoginPresenter(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
        mUserModel = new LoginUserModel();
    }

    public void login() {
        mLoginView.showProgress(true);
        mUserModel.loginUser(mLoginView.getUserPhone(), mLoginView.getUserPassword(), new LoginUserModel.OnLoginListener() {
            @Override
            public void loginSuccess() {
                User user = BmobUser.getCurrentUser(User.class);
                int permission = user.getPermission();
                mLoginView.showProgress(false);
                mLoginView.loginSuccess(permission);
            }

            @Override
            public void loginFailed(String error) {
                Log.d("login", "loginFailed: " + error);
                if (error.contains("101")) {
                    error = "登录失败,账号或者密码输入错误!";
                } else if (error.contains("9016")) {
                    error = "无网络连接，请检查您的手机网络.";
                } else if (error.contains("9019")) {
                    error = "登录失败,格式不正确!";
                }
                mLoginView.showProgress(false);
                mLoginView.loginFailed(error);
            }
        });
    }
}
