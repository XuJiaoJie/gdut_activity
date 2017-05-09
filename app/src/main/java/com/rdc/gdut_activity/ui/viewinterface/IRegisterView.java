package com.rdc.gdut_activity.ui.viewinterface;

/**
 * Created by ThatNight on 2017.5.3.
 */

public interface IRegisterView {
    String getUserPhone();

    String getUserPassword();

    void registerSuccess();

    void registerFailed(String error);
}
