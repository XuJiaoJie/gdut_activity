package com.rdc.gdut_activity.presenter;

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
        mRegisterView.showProgress(true);
        mUserModel.registerUser(mRegisterView.getUserPhone(),mRegisterView.getUserPassword(), new RegisterUserModel.OnRegisterListener() {
            @Override
            public void registerSuccess() {
                mRegisterView.showProgress(false);
                mRegisterView.registerSuccess();
            }

            @Override
            public void registerFailed(String error) {
                mRegisterView.showProgress(false);
                if (error.contains("202")) {
                    error = "该手机号已被注册!";
                } else if (error.contains("9016")) {
                    error = "无网络连接，请检查您的手机网络.";
                }
                mRegisterView.registerFailed(error);
            }
        });
    }
}
