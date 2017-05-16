package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.ActivityInfoBean;

import java.util.List;

/**
 * Created by PC on 2017/5/13.
 */

public interface VerifyContract {

    interface View{
        void onRefreshSuccess(List<ActivityInfoBean> list);

        void onRefreshError(String s);

        void onLoadMoreSuccess(List<ActivityInfoBean> list);

        void onLoadMoreError(String s);
    }

    interface DetailView{
        void verifySuccess();

        void verifyError(String s);
    }

    interface Presenter{
        //view
        void onRefersh(String type);

        void onLoadMore(String type);

        void verifyPass(String objectId);

        void verifyFailure(String objectId,String reason);

        //model
        void refreshDataSuccess(List<ActivityInfoBean> list);

        void refreshDataError(String s);

        void loadMoreDataSuccess(List<ActivityInfoBean> list);

        void loadMoreDataErroe(String s);

        void verifySuccess();

        void verifyError(String s);
    }

    interface model{
        void refreshData(String type);

        void loadMoreData(String type);

        void verifyPass(String objectId);

        void verifyFailure(String objectId,String reason);
    }

}
