package com.rdc.gdut_activity.bean;

/**
 * Created by ThatNight on 2017.5.12.
 */

public class Student extends User {
    private String schoolNumber; //学号
    private boolean sex;        //性别
    private String grade;       //年级
    private String college;     //学院
    private String major;
    private String mSchoolNumber;
    private String mSchoolPassword;

    private String age;
    private String area;
    private String gdutPassword;

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getmSchoolNumber() {
        return mSchoolNumber;
    }

    public void setmSchoolNumber(String mSchoolNumber) {
        this.mSchoolNumber = mSchoolNumber;
    }

    public String getmSchoolPassword() {
        return mSchoolPassword;
    }

    public void setmSchoolPassword(String mSchoolPassword) {
        this.mSchoolPassword = mSchoolPassword;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGdutPassword() {
        return gdutPassword;
    }

    @Override
    public void setPassword(String password) {
        this.gdutPassword = password;
    }

    @Override
    public String toString() {
        return "姓名：" + getUsername() + "年龄：" + getAge();
    }
}
