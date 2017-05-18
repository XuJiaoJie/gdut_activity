package com.rdc.gdut_activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.contract.ToolContract;
import com.rdc.gdut_activity.presenter.ToolPresenterImpl;
import com.rdc.gdut_activity.ui.CourseActivity;
import com.rdc.gdut_activity.ui.ScoreActivity;
import com.rdc.gdut_activity.ui.SelectClassActivity;
import com.rdc.gdut_activity.ui.UserGDUTActivity;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by zjz on 2017/5/5.
 */

public class ToolFragment extends BaseFragment implements ToolContract.View {
    private static final String TAG = "ToolFragment";
    @InjectView(R.id.ll_tool_score)
    LinearLayout mLlToolScore;
    @InjectView(R.id.ll_tool_select_class)
    LinearLayout mLlToolSelectClass;
    @InjectView(R.id.ll_tool_book)
    LinearLayout mLlToolBook;
    @InjectView(R.id.ll_tool_course)
    LinearLayout mLlToolCourse;
    private int mTitle;
    private String mMessage;
    private ToolContract.Presenter mPresenter;
    private int mSelected;

    /**
     * 用来获取fragment实例的方法，这里可以让Activity给fragment设置参数,参数可以在下面的initData方法中的bundle中取出
     */
    public static ToolFragment newInstance(int title, String message) {
        ToolFragment toolFragment = new ToolFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("title", title);
        bundle.putString("message", message);
        toolFragment.setArguments(bundle);
        return toolFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_tool;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mPresenter = new ToolPresenterImpl(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    private void setParams(Bundle bundle) {
        mTitle = bundle.getInt("title");
        mMessage = bundle.getString("message");
    }

    public void topbarLeftButtonClick() {
        Toast.makeText(getActivity(), "工具左按钮", Toast.LENGTH_SHORT).show();
    }

    public void topbarRightButtonClick() {
        Toast.makeText(getActivity(), "工具右按钮", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.ll_tool_score, R.id.ll_tool_select_class, R.id.ll_tool_book, R.id.ll_tool_course})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tool_score:
                checkStudentID(1);
                break;
            case R.id.ll_tool_select_class:
                checkStudentID(2);
                break;
            case R.id.ll_tool_book:
                showToast("该功能尚未开发");
                break;
            case R.id.ll_tool_course:
                checkStudentID(4);
                break;
        }
    }

    private void checkStudentID(int selected) {
        Student student = BmobUser.getCurrentUser(Student.class);
        String studentId = student.getmSchoolNumber();
        String studentPwd = student.getmSchoolPassword();
        Log.e(TAG, "checkStudentID: " + studentId + "   " + studentPwd);

        if (!TextUtils.isEmpty(studentId) && !TextUtils.isEmpty(studentPwd)) {
            Log.e(TAG, "checkStudentID: " + " presenter");
            mSelected = selected;
            mPresenter.loginSystem(studentId, studentPwd);
        } else {
            Log.e(TAG, "checkStudentID:          " + " intent");
            Intent intent = new Intent(mBaseActivity, UserGDUTActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void loginSystemSuccess() {
        if (mSelected == 1) {
            Intent intent = new Intent(mBaseActivity, ScoreActivity.class);
            startActivity(intent);
        } else if (mSelected == 2) {
            Intent intent = new Intent(mBaseActivity, SelectClassActivity.class);
            startActivity(intent);
        } else if (mSelected == 4) {
            Intent intent = CourseActivity.newIntent(mBaseActivity);
            startActivity(intent);
        }

    }

    @Override
    public void loginSystemFailure(String msg) {
        showToast(msg);
        Intent intent = new Intent(mBaseActivity, UserGDUTActivity.class);
        startActivity(intent);
    }

    @Override
    public void getError(String s) {
        showToast(s);
    }
}

