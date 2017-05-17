package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.SignUpBean;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.contract.SignUpContract;
import com.rdc.gdut_activity.listener.IHttpCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class SignUpModel implements SignUpContract.Model {

    @Override
    public void signUp(final ActivityInfoBean activityInfoBean, Map<String, String> map, final IHttpCallBack httpCallBack) {
        Student student = BmobUser.getCurrentUser(Student.class);
        SignUpBean signUpBean = new SignUpBean();
        signUpBean.setStudent(student);
        signUpBean.setActivityBean(activityInfoBean);
        signUpBean.setFormMap(map);
        signUpBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    setPushChannel(activityInfoBean);
                    httpCallBack.onResponse(null);
                } else {
                    httpCallBack.onError(e.toString());
                }
            }
        });
    }

    private void setPushChannel(ActivityInfoBean activityInfoBean) {
        BmobInstallation installation = BmobInstallation.getCurrentInstallation();
        installation.subscribe(activityInfoBean.getObjectId());
        installation.save();
    }

    @Override
    public void checkSignUp(String activityId, final IHttpCallBack httpCallBack) {
        Student student = BmobUser.getCurrentUser(Student.class);
        String userId = student.getObjectId();
        BmobQuery<SignUpBean> query = new BmobQuery<>();
        query.addWhereEqualTo("mStudent", userId);
        BmobQuery<SignUpBean> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("mActivityBean", activityId);

        List<BmobQuery<SignUpBean>> andQuerys = new ArrayList<>();
        andQuerys.add(query);
        andQuerys.add(query1);

        BmobQuery<SignUpBean> andQuery = new BmobQuery<>();
        andQuery.and(andQuerys);
        andQuery.findObjects(new FindListener<SignUpBean>() {
            @Override
            public void done(List<SignUpBean> list, BmobException e) {
                if (e == null) {
                    httpCallBack.onResponse(list.get(0).getObjectId());
                } else {
                    httpCallBack.onError(e.toString());
                }
            }
        });
    }

    @Override
    public void updateSignUp(String signupId, Map<String, String> map, final IHttpCallBack httpCallBack) {
        SignUpBean signUpBean = new SignUpBean();
        signUpBean.setFormMap(map);
        signUpBean.update(signupId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    httpCallBack.onResponse(null);
                } else {
                    httpCallBack.onError(e.toString());
                }
            }
        });
    }
}
