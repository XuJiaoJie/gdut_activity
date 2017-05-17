package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.ScoreBean;

import java.util.List;

/**
 * Created by PC on 2017/5/16.
 */

public interface ScoreContract {

    interface View{
        void querySuccess(List<ScoreBean.RowsBean> list);

        void queryError(String s);
    }

    interface Presenter{
        //view
        void queryScore(String time);

        //model
        void querySuccess(byte[] bytes);

        void queryError(String s);

    }

}
