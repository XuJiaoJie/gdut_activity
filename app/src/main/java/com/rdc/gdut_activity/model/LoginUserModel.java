package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.utils.EncryptUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by ThatNight on 2017.4.25.
 */

public class LoginUserModel {

    public void loginUser(String userPhone, String userPassword, final OnLoginListener onLoginListener) {
        String hexoPassword = EncryptUtil.getInstance().encrypt(userPassword);
        BmobUser.loginByAccount(userPhone, hexoPassword, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    onLoginListener.loginSuccess();
                } else {
                    onLoginListener.loginFailed(e.toString());
                }
            }
        });
    }

    public interface OnLoginListener {
        void loginSuccess();

        void loginFailed(String error);
    }

}
