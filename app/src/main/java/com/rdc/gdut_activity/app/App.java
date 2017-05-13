package com.rdc.gdut_activity.app;

import android.app.Application;

import com.rdc.gdut_activity.constant.Constant;

import cn.bmob.v3.Bmob;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
    }

    private void initBmob() {
        Bmob.initialize(this, Constant.APPLICATION_ID);
    }


}
