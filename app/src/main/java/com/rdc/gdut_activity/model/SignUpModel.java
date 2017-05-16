package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.SignUpBean;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.contract.SignUpContract;
import com.rdc.gdut_activity.listener.IHttpCallBack;

import java.util.Map;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class SignUpModel implements SignUpContract.Model {

    @Override
    public void signUp(ActivityInfoBean activityInfoBean, Map<String, String> map, final IHttpCallBack httpCallBack) {
        Student student = BmobUser.getCurrentUser(Student.class);
        SignUpBean signUpBean = new SignUpBean();
        signUpBean.setStudent(student);
        signUpBean.setActivityBean(activityInfoBean);
        signUpBean.setFormMap(map);
        signUpBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    httpCallBack.onSuccess();
                } else {
                    httpCallBack.onError(e.toString());
                }
            }
        });
    }
}
