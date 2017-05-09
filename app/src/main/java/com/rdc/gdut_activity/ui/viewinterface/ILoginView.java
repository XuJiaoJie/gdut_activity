package com.rdc.gdut_activity.ui.viewinterface;

/**
 * Created by ThatNight on 2017.4.26.
 */

public interface ILoginView {
    void loginSuccess(int permission);

    void loginFailed(String error);

    void showProgress(int visibility);

    String getUserPhone();

    String getUserPassword();

}
