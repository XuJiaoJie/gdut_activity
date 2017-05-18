package com.rdc.gdut_activity.model;

import com.rdc.gdut_activity.bean.CourseBean;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.contract.CourseContract;
import com.rdc.gdut_activity.utils.GsonUtil;
import com.rdc.gdut_activity.utils.OkHttpResultCallback;
import com.rdc.gdut_activity.utils.OkHttpUtil;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/05/18
 */

public class CourseModelImpl implements CourseContract.Model {
    private static final String TAG = "CourseModelImpl";

    @Override
    public void queryCourse(final CourseContract.Presenter presenter, int week) {

        OkHttpUtil.getInstance().getAsync(Constant.GET_COURSE_URL + week, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.onQueryFailed(e.getMessage());
            }

            @Override
            public void onResponse(byte[] bytes) {
                String str = new String(bytes);
                final ArrayList<CourseBean> courseBeanList = GsonUtil.parseCourse(str);
                presenter.onQuerySuccess(courseBeanList);
            }
        });
    }


}