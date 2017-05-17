package com.rdc.gdut_activity.presenter;

import com.rdc.gdut_activity.contract.ToolContract;
import com.rdc.gdut_activity.model.ToolModel;

/**
 * Created by PC on 2017/5/17.
 */

public class ToolPresenterImpl implements ToolContract.Presenter{
    private ToolContract.View mView;
    private ToolModel mModel;

    public ToolPresenterImpl(ToolContract.View view){
        mView = view;
        mModel = new ToolModel(this);
    }

    //view
    @Override
    public void loginSystem(String studentId, String pwd) {
        mModel.loginSystem(studentId,pwd);
    }


    //model
    @Override
    public void loginSystemSuccess() {
        mView.loginSystemSuccess();
    }

    @Override
    public void getError(String s) {
        mView.getError(s);
    }

    @Override
    public void loginSystemFailure(String msg) {
        mView.loginSystemFailure(msg);
    }
}
