package com.rdc.gdut_activity.presenter;

import android.util.Log;
import android.view.View;

import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.model.LoginUserModel;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;

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
        mLoginView.showProgress(View.VISIBLE);
        mUserModel.loginUser(mLoginView.getUserPhone(), mLoginView.getUserPassword(), new LoginUserModel.OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mLoginView.showProgress(View.INVISIBLE);
                Log.d("login", "loginSuccess: " + user.getUsername());
            }

            @Override
            public void loginFailed(String error) {
                Log.d("login", "loginfailed: " + error);
            }
        });
    }
}
