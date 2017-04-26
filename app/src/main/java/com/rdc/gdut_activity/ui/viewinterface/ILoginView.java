package com.rdc.gdut_activity.ui.viewinterface;

/**
 * Created by ThatNight on 2017.4.26.
 */

public interface ILoginView {
    void loginSuccess();

    void loginFailed();

    void showProgress(int visibility);

    String getUserPhone();

    String getUserPassword();

}
