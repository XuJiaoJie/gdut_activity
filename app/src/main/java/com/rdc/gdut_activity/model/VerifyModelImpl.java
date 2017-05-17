package com.rdc.gdut_activity.model;

import android.util.Log;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.VerifyContract;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by PC on 2017/5/13.
 */

public class VerifyModelImpl implements VerifyContract.model{
    private static final String TAG = "VerifyModelImpl";
    private int mPageVerify = 0;  //当前页数
    private int mPageVerifyNo = 0;  //当前页数
    private int mPagePublish = 0;  //当前页数
    private int mOnePageSum = 10; //一页的数据条数
    private VerifyContract.Presenter mPresenter;

    public VerifyModelImpl(VerifyContract.Presenter presenter){
        mPresenter = presenter;
    }

    //刷新数据
    @Override
    public void refreshData(String para,String type) {
        BmobQuery<ActivityInfoBean> query = new BmobQuery<>();
        if (para.equals("mCheckStatus")){
            if (type.equals("未审核")){
                query.addWhereEqualTo(para,type);
                mPageVerifyNo = 0;
            }else {
                String[] types = {"审核通过","审核不通过"};
                query.addWhereContainedIn(para, Arrays.asList(types));
                mPageVerify = 0;
            }
        }else {
            query.addWhereEqualTo(para,type);
            mPagePublish = 0;
        }
        query.order("-createdAt");
        query.setLimit(mOnePageSum);
        query.findObjects(new FindListener<ActivityInfoBean>() {
            @Override
            public void done(List<ActivityInfoBean> list, BmobException e) {
                if (e == null){
                    mPresenter.refreshDataSuccess(list);
                }else {
                    mPresenter.refreshDataError(e.getMessage());
                }
            }
        });
    }

    //加载更多数据
    @Override
    public void loadMoreData(String para,String type) {
        BmobQuery<ActivityInfoBean> query = new BmobQuery<>();
        if (para.equals("mCheckStatus")){
            if (type.equals("未审核")){
                query.addWhereEqualTo(para,type);
                mPageVerifyNo++;
                query.setSkip(mOnePageSum * mPageVerifyNo);
            }else {
                String[] types = {"审核通过","审核不通过"};
                query.addWhereContainedIn(para, Arrays.asList(types));
                mPageVerify++;
                query.setSkip(mOnePageSum * mPageVerify);
            }
        }else {
            query.addWhereEqualTo(para,type);
            mPagePublish++;
            query.setSkip(mOnePageSum * mPagePublish);
        }
        query.order("-createdAt");
        query.setLimit(mOnePageSum);
        query.findObjects(new FindListener<ActivityInfoBean>() {
            @Override
            public void done(List<ActivityInfoBean> list, BmobException e) {
                if (e == null){
                    mPresenter.loadMoreDataSuccess(list);
                }else {
                    mPresenter.loadMoreDataErroe(e.getMessage());
                }
            }
        });
    }

    //审核通过上传
    @Override
    public void verifyPass(String objectId) {
        ActivityInfoBean bean = new ActivityInfoBean();
        bean.setCheckStatus("审核通过");
        bean.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    mPresenter.verifySuccess();
                }else {
                    mPresenter.verifyError(e.getMessage());
                }
            }
        });
    }

    //审核失败上传
    @Override
    public void verifyFailure(String objectId, String reason) {
        ActivityInfoBean bean = new ActivityInfoBean();
        bean.setCheckStatus("审核不通过");
        bean.setCheckReason(reason);
        bean.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    mPresenter.verifySuccess();
                }else {
                    mPresenter.verifyError(e.getMessage());
                }
            }
        });
    }
}
