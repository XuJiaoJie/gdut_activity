package com.rdc.gdut_activity.bean;

/**
 * Created by ThatNight on 2017.5.12.
 */

public class Student extends User {
    private String schoolNumber; //学号
    private boolean sex;        //性别
    private String grade;       //年级
    private String college;     //学院

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
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
