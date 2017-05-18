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

        void refreshDataByName(String type,String name);

        void loadMoreData(String type);

        void loadaMoreDataByName(String type,String name);

    }

    interface Presenter{
        //view
        void onRefresh(String type);

        void onLoadMore(String type);

        void onRefreshByName(String type , String name);

        void onLoadMoreByName(String type,String name);

        //model
        void refreshDataSuccess(List<ActivityInfoBean> list);

        void refreshDataError(String s);

        void loadMoreDataSuccess(List<ActivityInfoBean> list);

        void loadMoreDataError(String s);


    }
}
