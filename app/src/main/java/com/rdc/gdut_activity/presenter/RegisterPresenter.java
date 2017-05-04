package com.rdc.gdut_activity.presenter;

import android.util.Log;

import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.model.RegisterUserModel;
import com.rdc.gdut_activity.ui.viewinterface.IRegisterView;

/**
 * Created by ThatNight on 2017.5.3.
 */

public class RegisterPresenter {
    private IRegisterView mRegisterView;
    private RegisterUserModel mUserModel;

    public RegisterPresenter(IRegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
        mUserModel = new RegisterUserModel();
    }

    public void register() {
        mUserModel.registerUser(mRegisterView.getUserPhone(), mRegisterView.getUserPassword(), new RegisterUserModel.OnRegisterListener() {
            @Override
            public void registerSuccess(User user) {
                mRegisterView.registerSuccess(user);
            }

            @Override
            public void registerFailed(String error) {
                Log.d("login", "loginfailed: " + error);
                mRegisterView.registerFailed(error);
            }
        });
    }
}
