package com.rdc.gdut_activity.presenter;

import android.util.Log;

import com.rdc.gdut_activity.bean.ClassBean;
import com.rdc.gdut_activity.bean.ClassHome;
import com.rdc.gdut_activity.bean.RowsBean;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.model.SelectClassModel;
import com.rdc.gdut_activity.ui.viewinterface.ISelectClassView;
import com.rdc.gdut_activity.utils.GsonUtil;
import com.rdc.gdut_activity.utils.OkHttpResultCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;
import okhttp3.Call;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class SelectClassPresenter {
    private SelectClassModel mClassModel;
    private ISelectClassView mSelectClassView;
    private Map<String, String> mParamsMap;

    public SelectClassPresenter(ISelectClassView selectClassView) {
        mClassModel = new SelectClassModel();
        mSelectClassView = selectClassView;
        mParamsMap = new HashMap<>();
    }

    /**
     * 获取选课
     *
     * @param page
     */
    public void getClasses(String page) {
        mParamsMap.clear();
        mParamsMap.put("page", page);
        mParamsMap.put("rows", "150");
        mParamsMap.put("sort", "kcrwdm");
        mParamsMap.put("order", "asc");
        mClassModel.getClasses(Constant.URL_SELECTCLASS_GETCLASS_LIST, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mSelectClassView.getFailed();
            }

            @Override
            public void onResponse(byte[] bytes) {
                returnClasses(bytes, Constant.MODE_NORMAL);
            }
        }, mParamsMap, null);
    }

    /**
     * 选课
     *
     * @param classCode
     * @param className
     */
    public void selectClass(String classCode, String className) {
        mParamsMap.clear();
        mParamsMap.put("kcrwdm", classCode);
        try {
            className = URLEncoder.encode(className, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mParamsMap.put("kcmc", className);
        mClassModel.selectClass(Constant.URL_SELECTCLASS_SELECTCLASS, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mSelectClassView.selectFailed();
            }

            @Override
            public void onResponse(byte[] bytes) {
                String response = new String(bytes);
                mSelectClassView.selectSuccess(response);
            }
        }, mParamsMap, null);
    }

    /**
     * 查询
     *
     * @param searchValue
     */
    public void queryClass(String searchValue) {
        mParamsMap.clear();
        mParamsMap.put("searchKey", "kcmc");
        mParamsMap.put("searchValue", searchValue);
        mParamsMap.put("page", "1");
        mParamsMap.put("rows", "50");
        mParamsMap.put("sort", "kcrwdm");
        mParamsMap.put("order", "asc");
        mClassModel.queryClass(Constant.URL_SELECTCLASS_GETCLASS_LIST, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(byte[] bytes) {
                returnClasses(bytes, Constant.MODE_NORMAL);
            }
        }, mParamsMap, null);
    }

    /**
     * 获取已选的课
     */
    public void getOwnClass() {
        mParamsMap.clear();
        mParamsMap.put("sort", "kcrwdm");
        mParamsMap.put("order", "asc");
        mClassModel.getOwnClass(Constant.URL_SELECTCLASS_GETOWN_CLASS, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(byte[] bytes) {
                returnClasses(bytes, Constant.MODE_OWN);
            }
        }, mParamsMap, null);
    }

    /**
     * 登录
     */
    public void login() {
        mParamsMap.clear();
        Student student = BmobUser.getCurrentUser(Student.class);

        mParamsMap.put("verifycode", "");
        mClassModel.login(Constant.EDUCATION_SYSTEM_LOGIN_URL, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mSelectClassView.loginFailed();
            }

            @Override
            public void onResponse(byte[] bytes) {
                String respone = new String(bytes);
                Log.d("onResponse", "onResponse: " + respone);
                mSelectClassView.loginSuccess();
            }
        }, mParamsMap, null);
    }

    /**
     * 返回课表信息
     *
     * @param bytes
     */
    private void returnClasses(byte[] bytes, int mode) {
        String respone = new String(bytes);
        Log.d("onResponse", "onResponse: " + respone);
        List<RowsBean> list = null;
        if (mode == Constant.MODE_NORMAL) {
            list = getRowsBean(respone);
        } else if (mode == Constant.MODE_OWN) {
            list = getOwnBean(respone);
        }
        List<ClassBean> classes = new ArrayList<ClassBean>();
        for (RowsBean bean : list) {
            ClassBean classBean = new ClassBean();
            classBean.setClassName(bean.getKcmc());
            classBean.setClassNum(bean.getKcdm());
            classBean.setClassTime(bean.getXmmc());
            classBean.setClassPeople(bean.getPkrs());
            classBean.setClassTeacher(bean.getTeaxm());
            classBean.setClassSelectPeople(bean.getJxbrs());
            classes.add(classBean);
        }
        mSelectClassView.getSuccess(classes);
    }

    private List<RowsBean> getOwnBean(String respone) {
        return GsonUtil.gsonToList(respone, RowsBean.class);
    }

    private List<RowsBean> getRowsBean(String respone) {
        ClassHome home = GsonUtil.gsonToBean(respone, ClassHome.class);
        return home.getRows();
    }
}
