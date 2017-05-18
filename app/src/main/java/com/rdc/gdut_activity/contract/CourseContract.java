package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.CourseBean;

import java.util.List;

public class CourseContract {
    public interface View {
        void onQuerySuccess(List<CourseBean> beanList);

        void onQueryFailed(String msg);
    }

    public interface Presenter {
        void queryCourseList(int week);

        void onQuerySuccess(List<CourseBean> beanList);

        void onQueryFailed(String msg);
    }

    public interface Model {
        void queryCourse(Presenter presenter, int week);
    }
}