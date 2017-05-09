package com.rdc.gdut_activity.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by ThatNight on 2017.4.25.
 */

public class User extends BmobUser {

    private String schoolNumber; //学号
    private boolean sex;        //性别
    private String grade;       //年级
    private String college;     //学院
    private int permission;     //用户权限
    private String icon;        //头像地址
    private BmobFile iconFile;  //头像文件

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
