package com.rdc.gdut_activity.ui.viewinterface;

import com.rdc.gdut_activity.bean.ClassBean;

import java.util.List;

/**
 * Created by ThatNight on 2017.5.13.
 */

public interface ISelectClassView {
    void loginSuccess();

    void loginFailed();

    void getSuccess(List<ClassBean> classBean);

    void getFailed();

    void selectSuccess(String response);

    void selectFailed();

    void refreshing(boolean isRefreshing);

}
