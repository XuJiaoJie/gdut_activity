package com.rdc.gdut_activity.app;

import android.app.Application;

import com.rdc.gdut_activity.constant.Constant;

import cn.bmob.v3.Bmob;

/**
 * Created by ThatNight on 2017.4.26.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, Constant.APPLICATION_ID);
    }
}
