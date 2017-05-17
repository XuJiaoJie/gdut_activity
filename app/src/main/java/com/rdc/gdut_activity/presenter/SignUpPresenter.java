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
            public void onResponse(String response) {
                mView.showProgress(false);
                mView.signUpSuccess();
            }
        });
    }

    @Override
    public void signUped(String activityId) {
        mModel.checkSignUp(activityId, new IHttpCallBack() {
            @Override
            public void onError(String error) {
                mView.setIsSign(false);
            }

            @Override
            public void onResponse(String response) {
                mView.setIsSign(true);
                mView.isSignUp(response);
            }
        });
    }

    @Override
    public void updateSignUp(Map<String, String> map) {
        mModel.updateSignUp(mView.getSignupId(), map, new IHttpCallBack() {
            @Override
            public void onError(String error) {

            }

            @Override
            public void onResponse(String response) {
                mView.signUpSuccess();
            }
        });
    }


}
