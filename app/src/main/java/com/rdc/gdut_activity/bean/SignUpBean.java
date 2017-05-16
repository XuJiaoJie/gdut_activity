package com.rdc.gdut_activity.bean;

import java.util.Map;

import cn.bmob.v3.BmobObject;

/**
 * 报名表
 * Created by ThatNight on 2017.5.16.
 */
public class SignUpBean extends BmobObject {
    private Map<String, String> mFormMap;
    private ActivityInfoBean mActivityBean;
    private Student mStudent;

    public Map<String, String> getFormMap() {
        return mFormMap;
    }

    public void setFormMap(Map<String, String> formMap) {
        mFormMap = formMap;
    }

    public ActivityInfoBean getActivityBean() {
        return mActivityBean;
    }

    public void setActivityBean(ActivityInfoBean activityBean) {
        mActivityBean = activityBean;
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        mStudent = student;
    }
}
