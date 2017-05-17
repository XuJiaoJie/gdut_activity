package com.rdc.gdut_activity.presenter;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.VerifyContract;
import com.rdc.gdut_activity.model.VerifyModelImpl;

import java.util.List;

/**
 * Created by PC on 2017/5/13.
 */

public class VerifyPresenterImpl implements VerifyContract.Presenter {
    private VerifyContract.View mView;
    private VerifyContract.DetailView mDetailView;
    private VerifyContract.model mModel = new VerifyModelImpl(this);

    public VerifyPresenterImpl(VerifyContract.View view){
        mView = view;
    }

    public VerifyPresenterImpl(VerifyContract.DetailView detailView){
        mDetailView = detailView;
    }

    /**
     * view调用
     */
    @Override
    public void onRefersh(String para,String type) {
        mModel.refreshData(para,type);
    }

    @Override
    public void onLoadMore(String para,String type) {
        mModel.loadMoreData(para,type);
    }

    @Override
    public void verifyPass(String objectId) {
        mModel.verifyPass(objectId);
    }

    @Override
    public void verifyFailure(String objectId, String reason) {
        mModel.verifyFailure(objectId,reason);
    }


    /**
     * model调用
     */
    @Override
    public void refreshDataSuccess(List<ActivityInfoBean> list) {
        mView.onRefreshSuccess(list);
    }

    @Override
    public void refreshDataError(String s) {
        mView.onRefreshError(s);
    }

    @Override
    public void loadMoreDataSuccess(List<ActivityInfoBean> list) {
        mView.onLoadMoreSuccess(list);
    }

    @Override
    public void loadMoreDataErroe(String s) {
        mView.onLoadMoreError(s);
    }

    @Override
    public void verifySuccess() {
        mDetailView.verifySuccess();
    }

    @Override
    public void verifyError(String s) {
        mDetailView.verifyError(s);
    }




}
