package com.rdc.gdut_activity.bean;

import com.google.gson.Gson;

/**
 * Created by PC on 2017/5/18.
 */

public class LoginBackBean {

    /**
     * status : y
     * msg : /login!welcome.action
     */

    private String status;
    private String msg;

    public static LoginBackBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBackBean.class);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
