package com.rdc.gdut_activity.bean;

/**
 * Created by ThatNight on 2017.5.12.
 */

public class ClassBean {
    private String className;
    private String classNum;            //课程代码
    private String classTime;
    private String classTeacher;
    private String classPeople;         //限选人数
    private String classType;           //课程类型
    private String classSelectPeople;   //已选人数

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getClassPeople() {
        return classPeople;
    }

    public void setClassPeople(String classPeople) {
        this.classPeople = classPeople;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassSelectPeople() {
        return classSelectPeople;
    }

    public void setClassSelectPeople(String classSelectPeople) {
        this.classSelectPeople = classSelectPeople;
    }
}
