package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.Publisher;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PushActivity extends BaseActivity {


    @InjectView(R.id.sp_activity)
    Spinner mSpinner;
    @InjectView(R.id.et_push_msg)
    EditText mEtPushMsg;
    @InjectView(R.id.btn_push)
    Button mBtnPush;

    private static final String TAG = "PushActivity";
    private List<ActivityInfoBean> mBeanList;
    private List<String> mTitleList;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, PushActivity.class);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_push;
    }

    @Override
    protected void initData() {
        mTitleList = new ArrayList<>();

        Publisher publisher = BmobUser.getCurrentUser(Publisher.class);
        BmobQuery<ActivityInfoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("mPublisher", publisher.getObjectId());
        query.findObjects(new FindListener<ActivityInfoBean>() {
            @Override
            public void done(List<ActivityInfoBean> list, BmobException e) {
                if (e == null) {
                    mBeanList = list;
                    updateData();
                } else {
                    showToast("获取数据出错" + e.getMessage());
                    Log.e(TAG, "done: ", e);
                }
            }
        });
    }

    private void updateData() {
        for (ActivityInfoBean bean : mBeanList) {
            mTitleList.add(bean.getActivityName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mTitleList);
        mSpinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.btn_push)
    public void onViewClicked() {
        String msg = mEtPushMsg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            showToast("请输入推送内容");
            return;
        }
        int position = mSpinner.getSelectedItemPosition();
        ActivityInfoBean activityInfoBean = mBeanList.get(position);
        // TODO: 2017/5/17 0017 推送
    }


}
