package com.rdc.gdut_activity.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by ThatNight on 2017.4.25.
 */

public class User extends BmobUser {

    private boolean sex;
    private String grade;
    private String college;

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
