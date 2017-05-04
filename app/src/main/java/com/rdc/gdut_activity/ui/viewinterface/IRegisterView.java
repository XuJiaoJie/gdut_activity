package com.rdc.gdut_activity.ui.viewinterface;

import com.rdc.gdut_activity.bean.User;

/**
 * Created by ThatNight on 2017.5.3.
 */

public interface IRegisterView {
    String getUserPhone();

    String getUserPassword();

    void registerSuccess(User user);

    void registerFailed(String error);
}
