package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.utils.OkHttpResultCallback;
import com.rdc.gdut_activity.utils.OkHttpUtil;

import java.util.Map;

import cn.bmob.v3.BmobUser;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class SelectClassModel {

    public void selectClass(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }

    public void getClasses(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }

    public void queryClass(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }

    public void login(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        Student student = BmobUser.getCurrentUser(Student.class);
        // TODO: 2017.5.14 查询登录用户的广工账号
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }

    public void getOwnClass(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }


}
