package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.Publisher;
import com.rdc.gdut_activity.utils.GsonUtil;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.PushListener;

public class PushActivity extends BaseActivity {


    @InjectView(R.id.sp_activity)
    Spinner mSpinner;
    @InjectView(R.id.et_push_msg)
    EditText mEtPushMsg;
    @InjectView(R.id.btn_push)
    Button mBtnPush;


    @InjectView(R.id.top_bar)
    TopBar mTopBar;    private static final String TAG = "PushActivity";
    private List<ActivityInfoBean> mBeanList;
    private List<String> mTitleList;

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
        BmobQuery<ActivityInfoBean> eq1 = new BmobQuery<>();
        eq1.addWhereEqualTo("mPublisher", publisher.getObjectId());
        BmobQuery<ActivityInfoBean> eq2 = new BmobQuery<>();
        eq2.addWhereEqualTo("mCheckStatus", "审核通过");

        List<BmobQuery<ActivityInfoBean>> andQuery = new ArrayList<>();
        andQuery.add(eq1);
        andQuery.add(eq2);

        BmobQuery<ActivityInfoBean> query = new BmobQuery<>();
        query.and(andQuery);
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
                R.layout.spinner_list_item, mTitleList);
        mSpinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void initView() {
        mTopBar.setButtonBackground(R.drawable.icon_back, 0);
    }


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
        pushToStudent(activityInfoBean, msg);
        showToast("推送成功");
    }

    private void pushToStudent(ActivityInfoBean activityInfoBean, String msg) {
        BmobPushManager manager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        List<String> channels = new ArrayList<>();
        channels.add(activityInfoBean.getObjectId());
        query.addWhereEqualTo("channels", channels);
        manager.setQuery(query);
//        MessageBean messageBean = new MessageBean();
//        messageBean.setMessage(msg);
//        messageBean.setTime(System.currentTimeMillis() + "");
//        messageBean.setIcon(activityInfoBean.getPublisherIconUrl());
//        messageBean.setObjectid(activityInfoBean.getObjectId());
//        String message= GsonUtil.gsonToJson(messageBean);
        manager.pushMessage(msg, new PushListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("推送成功!");
                } else {
                    showToast("推送失败!" + e.toString());
                }
            }
        });

    }


}
