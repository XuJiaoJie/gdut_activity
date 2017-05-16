package com.rdc.gdut_activity.app;

import android.app.Application;

import com.rdc.gdut_activity.constant.Constant;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
    }

    private void initBmob() {
        Bmob.initialize(this, Constant.APPLICATION_ID);
        //推送初始化
        BmobInstallation.getCurrentInstallation().save();
        //启动推送服务
        BmobPush.startWork(this);
    }


}
