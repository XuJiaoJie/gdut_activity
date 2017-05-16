package com.rdc.gdut_activity.presenter;

import com.rdc.gdut_activity.contract.SignUpContract;
import com.rdc.gdut_activity.listener.IHttpCallBack;
import com.rdc.gdut_activity.model.SignUpModel;

import java.util.Map;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpModel mModel;
    private SignUpContract.View mView;

    public SignUpPresenter(SignUpContract.View view) {
        mView = view;
        mModel = new SignUpModel();
    }

    @Override
    public void signUp(Map<String, String> map) {
        mView.showProgress(true);
        mModel.signUp(mView.getActivityBean(), map, new IHttpCallBack() {
            @Override
            public void onError(String error) {
                mView.showProgress(false);
                mView.signUpFailed(error);
            }

            @Override
            public void onSuccess() {
                mView.showProgress(false);
                mView.signUpSuccess();
            }
        });
    }
}
