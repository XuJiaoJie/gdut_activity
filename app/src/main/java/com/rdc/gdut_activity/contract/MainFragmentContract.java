package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.ActivityInfoBean;

import java.util.List;

/**
 * Created by zjz on 2017/5/16.
 */

public interface MainFragmentContract {
    interface View{
            void onRefreshSuccess(List<ActivityInfoBean> list);

            void onRefreshError(String s);

            void onLoadMoreSuccess(List<ActivityInfoBean> list);

            void onLoadMoreError(String s);
    }

    interface Model{
        void refreshData(String type);

        void loadMoreData(String type);

        void mainFragmentPass(String objectId);

        void mainFragmentFailure(String objectId,String reason);
    }

    interface Presenter{
        //view
        void onRefresh(String type);

        void onLoadMore(String type);

        void mainFragmentPass(String objectId);

        void mainFragmentFailure(String objectId,String reason);

        //model
        void refreshDataSuccess(List<ActivityInfoBean> list);

        void refreshDataError(String s);

        void loadMoreDataSuccess(List<ActivityInfoBean> list);

        void loadMoreDataErroe(String s);

        void mainFragmentSuccess();

        void mainFragmentError(String s);
    }
}
