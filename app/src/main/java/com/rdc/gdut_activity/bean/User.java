package com.rdc.gdut_activity.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by ThatNight on 2017.4.25.
 */

public class User extends BmobUser {

    protected Integer permission;     //用户权限
    protected String icon;        //头像地址
    protected BmobFile iconFile;  //头像文件

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BmobFile getIconFile() {
        return iconFile;
    }

    public void setIconFile(BmobFile iconFile) {
        this.iconFile = iconFile;
    }
}
