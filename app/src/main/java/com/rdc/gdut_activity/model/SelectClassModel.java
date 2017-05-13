package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.utils.OkHttpResultCallback;
import com.rdc.gdut_activity.utils.OkHttpUtil;

import java.util.Map;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class SelectClassModel {

    public void selectClass() {

    }

    public void getClasses(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers) {
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }

    public void queryClass() {

    }

    public void login(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, Map<String, String> headers){
        OkHttpUtil.getInstance().postAsync(url, okHttpResultCallback, params, headers);
    }


}
