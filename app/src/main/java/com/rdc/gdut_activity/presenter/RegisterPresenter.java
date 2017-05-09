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
        mUserModel.registerUser(mRegisterView.getUserPhone(), mRegisterView.getUserPassword(), new RegisterUserModel.OnRegisterListener() {
            @Override
            public void registerSuccess() {
                mRegisterView.registerSuccess();
            }

            @Override
            public void registerFailed(String error) {
                mRegisterView.registerFailed(error);
            }
        });
    }
}
