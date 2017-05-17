package com.rdc.gdut_activity.utils;

/**
 * Created by ThatNight on 2017.5.17.
 */

public class CheckInfoUtil {

    /**
     * 检测登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    public static String checkLogin(String userName, String passWord) {
        if (userName.length() == 0 || passWord.length() == 0) {
            return "手机号和密码不能为空!";
        }
        if (userName.length() < 11) {
            return "请输入正确的手机号!";
        }
        if (passWord.length() < 6) {
            return "密码长度不得小于6!";
        }
        return null;
    }

    /**
     * 检测注册信息
     * @param userName
     * @param userPassword
     * @param userPasswordAgain
     * @return
     */
    public static String checkRegister(String userName, String userPassword, String userPasswordAgain) {
        if (userName.length() == 0 || userPassword.length() == 0 || userPasswordAgain.length() == 0) {
            return "手机号和密码不能为空!";
        }
        if (userName.length() < 11) {
            return "请输入正确的手机号!";
        }
        if (userPassword.length() < 6) {
            return "密码长度不得小于6!";
        }
        if (!userPassword.equals(userPasswordAgain)) {
            return "请确认两次输入的密码一致!";
        }
        return null;
    }


}
