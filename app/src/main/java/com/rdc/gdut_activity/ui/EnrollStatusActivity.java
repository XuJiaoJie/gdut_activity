package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.EnrollStatusAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.SignUpBean;
import com.rdc.gdut_activity.view.TopBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class EnrollStatusActivity extends BaseActivity {

    @InjectView(R.id.rv_participant_list)
    RecyclerView mRecyclerView;
    @InjectView(R.id.top_bar)
    TopBar mTopBar;
    @InjectView(R.id.tv_enroll_count)
    TextView mTvEnrollCount;

    private static final String KEY_EXTRA = "EXTRA";
    private static final String TAG = "EnrollStatusActivity";
    private List<Map<String,String>> mStuList;
    private EnrollStatusAdapter mAdapter;

    public static Intent newIntent(Context context, ActivityInfoBean bean) {
        Intent intent = new Intent(context, EnrollStatusActivity.class);
        intent.putExtra(KEY_EXTRA, (Serializable) bean);
        return intent;
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_status_enroll;
    }

    @Override
    protected void initData() {
        mStuList = new ArrayList<>();
        ActivityInfoBean actInfoBean;
        if (getIntent() != null && getIntent().getExtras() != null) {
            actInfoBean = (ActivityInfoBean) getIntent().getSerializableExtra(KEY_EXTRA);
        } else {
            return;
        }

        BmobQuery<SignUpBean> query = new BmobQuery<>();
        query.addWhereEqualTo("mActivityBean", actInfoBean.getObjectId());
        query.include("mStudent.grade");
        query.findObjects(new FindListener<SignUpBean>() {
            @Override
            public void done(List<SignUpBean> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        mStuList.add(list.get(i).getFormMap());
                        Log.e(TAG, "done: " + mStuList.get(i));
                        updateData();
                    }
                    Log.e(TAG, "done: ");
                } else {
                    showToast("初始化数据出错" + e.getMessage());
                    Log.e(TAG, "done: ", e);
                }
            }
        });
    }

    private void updateData() {
        if (mAdapter == null) {
            mAdapter = new EnrollStatusAdapter(this);
        }
        mAdapter.updataData(mStuList);
        String str = "报名总人数：" + mStuList.size();
        mTvEnrollCount.setText(str);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EnrollStatusAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mTopBar.setButtonBackground(R.drawable.icon_back, 0);

    }

    @Override
    protected void initListener() {
        mTopBar.setOnTopbarClickListener(new TopBar.topbarClickListner() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

}
