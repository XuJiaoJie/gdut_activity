package com.rdc.gdut_activity.presenter;

import com.rdc.gdut_activity.bean.CourseBean;
import com.rdc.gdut_activity.contract.CourseContract;
import com.rdc.gdut_activity.model.CourseModelImpl;

import java.util.List;

/**
 * Created by Administrator on 2017/05/18
 */

public class CoursePresenterImpl implements CourseContract.Presenter {

    private CourseContract.View mView;
    private CourseContract.Model mModel;

    public CoursePresenterImpl(CourseContract.View view) {
        mView = view;
        mModel = new CourseModelImpl();
    }

    @Override
    public void queryCourseList(int week) {
        mModel.queryCourse(this, week);
    }

    @Override
    public void onQuerySuccess(List<CourseBean> beanList) {
        mView.onQuerySuccess(beanList);
    }

    @Override
    public void onQueryFailed(String msg) {
        mView.onQueryFailed(msg);
    }
}