package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.constant.Constant;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by ThatNight on 2017.4.25.
 */

public class RegisterUserModel {

    public void registerUser(String userPhone, String userPassword, final OnRegisterListener onRegisterListener) {
        User user = new User();
        user.setUsername(userPhone);
        user.setPassword(userPassword);
        user.setPermission(Constant.USER_STUDENT);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    onRegisterListener.registerSuccess();
                } else {
                    onRegisterListener.registerFailed(e.toString());
                }
            }
        });
    }

    public interface OnRegisterListener {
        void registerSuccess();

        void registerFailed(String error);
    }

}
