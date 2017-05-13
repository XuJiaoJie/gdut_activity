package com.rdc.gdut_activity.model;

import android.util.Log;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.PublishContract;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/05/12
 */

public class PublishModelImpl implements PublishContract.Model {

    private static final String TAG = "PublishModelImpl";

    public void uploadData(final PublishContract.Presenter publishPresenter, ActivityInfoBean bean) {
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    publishPresenter.onSuccess(objectId);
                } else {
                    publishPresenter.onFailed(e.getMessage());
                    Log.e(TAG, "exception" + e.getMessage(), e);
                }
            }
        });
    }

}