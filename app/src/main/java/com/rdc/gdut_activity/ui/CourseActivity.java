package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.CourseBean;
import com.rdc.gdut_activity.contract.CourseContract;
import com.rdc.gdut_activity.presenter.CoursePresenterImpl;
import com.rdc.gdut_activity.utils.ProgressDialogUtil;
import com.rdc.gdut_activity.utils.TimeUtil;
import com.rdc.gdut_activity.utils.UIUtil;
import com.rdc.gdut_activity.view.NiceSpinner;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class CourseActivity extends BaseActivity implements CourseContract.View {

    @InjectView(R.id.rv_course)
    RecyclerView mRvCourse;
    @InjectView(R.id.top_bar_query_course)
    TopBar mTopBarQueryCourse;
    @InjectView(R.id.ns_week_select)
    NiceSpinner mNsWeekSelect;
    private ArrayList<CourseBean> mCourseList;
    private CourseAdapter mCourseAdapter;
    private CourseContract.Presenter mPresenter;

    private static final String TAG = "CourseActivity";
    private static String mStartDate = "2017-02-20"; //学期开始时间

    public static Intent newIntent(Context context) {
        return new Intent(context, CourseActivity.class);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_course;
    }


    @Override
    protected void initData() {
        mPresenter = new CoursePresenterImpl(this);
    }

    @Override
    protected void initView() {
        mRvCourse.setLayoutManager(new LinearLayoutManager(this));
        mCourseList = new ArrayList<>();
        initWeekSpinner();
        mTopBarQueryCourse.setButtonBackground(R.drawable.icon_back, 0);
    }

    @Override
    protected void initListener() {
        mTopBarQueryCourse.setOnTopbarClickListener(new TopBar.topbarClickListner() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    private void initWeekSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter =
                ArrayAdapter.createFromResource(this, R.array.week_ary, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mNsWeekSelect.setAdapter(arrayAdapter);
        mNsWeekSelect.setTextColor(Color.BLACK);
        mNsWeekSelect.setSelectedIndex(TimeUtil.getCurrentWeek(mStartDate));
        mNsWeekSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mPresenter.queryCourseList(TimeUtil.getCurrentWeek(mStartDate));
                } else {
                    mPresenter.queryCourseList(position);
                }
                ProgressDialogUtil.showProgressDialog(CourseActivity.this, "获取数据中,请稍候...");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //首次进入之后获取当前周
        mPresenter.queryCourseList(TimeUtil.getCurrentWeek(mStartDate));
        ProgressDialogUtil.showProgressDialog(CourseActivity.this, "获取数据中,请稍候...");
    }

    private void updateUI() {
        ProgressDialogUtil.dismiss();
        if (mCourseAdapter == null) {
            mCourseAdapter = new CourseAdapter(mCourseList);
            mRvCourse.setAdapter(mCourseAdapter);
        } else {
            mCourseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onQuerySuccess(List<CourseBean> beanList) {
        mCourseList.clear();
        mCourseList.addAll(beanList);
        updateUI();
    }

    @Override
    public void onQueryFailed(String msg) {
        ProgressDialogUtil.dismiss();
        showToast(msg);
    }

    private class CourseHolder extends RecyclerView.ViewHolder {
        TextView mTvCourseName;
        TextView mTvCourseTime;
        TextView mTvClassroom;
        View mView;

        public CourseHolder(View itemView) {
            super(itemView);
            mTvCourseName = (TextView) itemView.findViewById(R.id.tv_course_name);
            mTvCourseTime = (TextView) itemView.findViewById(R.id.tv_course_time);
            mTvClassroom = (TextView) itemView.findViewById(R.id.tv_classroom);
            mView = itemView.findViewById(R.id.view_color);
        }

        public void bindView(CourseBean courseBean) {
            mTvCourseName.setText(courseBean.getKcmc());
            mTvClassroom.setText(courseBean.getJxcdmc());
            mTvCourseTime.setText("第 " + courseBean.getZc() + " 周\n" + "星期 " + courseBean.getXq() + "\n节次：" + courseBean.getJcdm());
            mView.setBackgroundColor(UIUtil.getRandomColor(CourseActivity.this));
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {
        private ArrayList<CourseBean> mCourseBeanList;

        public CourseAdapter(ArrayList<CourseBean> courseBeanList) {
            mCourseBeanList = courseBeanList;
        }

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.item_course, parent, false);
            return new CourseHolder(v);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            holder.bindView(mCourseBeanList.get(position));
        }

        @Override
        public int getItemCount() {
            return mCourseBeanList.size();
        }
    }

}
