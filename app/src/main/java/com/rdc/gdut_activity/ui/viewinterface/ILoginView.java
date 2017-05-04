package com.rdc.gdut_activity.ui.viewinterface;

import com.rdc.gdut_activity.bean.User;

/**
 * Created by ThatNight on 2017.4.26.
 */

public interface ILoginView {
    void loginSuccess(User user);

    void loginFailed();

    void showProgress(int visibility);

    String getUserPhone();

    String getUserPassword();

}
