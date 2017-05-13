package com.rdc.gdut_activity.presenter;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.PublishContract;
import com.rdc.gdut_activity.model.PublishModelImpl;

/**
* Created by Administrator on 2017/05/12
*/

public class PublishPresenterImpl implements PublishContract.Presenter{

    private PublishContract.View mView;
    private PublishContract.Model mModel;

    public PublishPresenterImpl(PublishContract.View view) {
        mView = view;
        mModel = new PublishModelImpl();
    }

    @Override
    public void uploadData(ActivityInfoBean bean) {
        mModel.uploadData(this,bean);
    }

    @Override
    public void onSuccess(String objId) {
        mView.onSuccess(objId);
    }

    @Override
    public void onFailed(String errorMsg) {
        mView.onFailed(errorMsg);
    }
}