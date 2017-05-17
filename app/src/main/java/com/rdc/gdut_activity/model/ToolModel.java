package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.contract.ToolContract;
import com.rdc.gdut_activity.utils.OkHttpResultCallback;
import com.rdc.gdut_activity.utils.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by PC on 2017/5/17.
 */

public class ToolModel {
    private Map<String,String> mBodyMap;
    private ToolContract.Presenter mPresenter;

    public ToolModel(ToolContract.Presenter presenter){
        mBodyMap = new HashMap<>();
        mPresenter = presenter;
    }

//    public void getStudent(String userId){
//        BmobQuery<Student> query = new BmobQuery<>();
//        query.getObject(userId, new QueryListener<Student>() {
//            @Override
//            public void done(Student student, BmobException e) {
//                if (e == null){
//                    mPresenter.getStudentSuccess(student);
//                }else {
//                    mPresenter.getError(e.getMessage());
//                }
//            }
//        });
//    }

    public void loginSystem(String studengtId,String pwd){
        mBodyMap.clear();
        mBodyMap.put(Constant.LOGIN_BODY_NAME_ACCOUNT,studengtId);
        mBodyMap.put(Constant.LOGIN_BODY_NAME_PWD,pwd);
        mBodyMap.put(Constant.LOGIN_BODY_NAME_VERIFYCODE,Constant.LOGIN_BODY_VALUE_VERIFYCODE);
        OkHttpUtil.getInstance().postAsync(Constant.EDUCATION_SYSTEM_LOGIN_URL, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mPresenter.getError(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                mPresenter.loginSystemSuccess();
            }
        },mBodyMap,null);
    }



}
