package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.ActivityInfoBean;

public interface PublishContract {

    public interface View {
        void onSuccess(String objId);

        void onFailed(String errorMsg);
    }

    public interface Presenter {
        void uploadData(ActivityInfoBean bean);

        void onSuccess(String objId);

        void onFailed(String errorMsg);
    }

    public interface Model {
        void uploadData(Presenter publishPresenter, ActivityInfoBean bean);
    }
}