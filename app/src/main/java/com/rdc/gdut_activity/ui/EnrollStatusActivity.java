package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.SignUpBean;

import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;

public class EnrollStatusActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.rv_participant_list)
    RecyclerView mRvParticipantList;


    public static Intent newIntent(Context context) {
        return new Intent(context, EnrollStatusActivity.class);
    }


    @Override
    protected int setLayoutResID() {
        return R.layout.activity_status_enroll;
    }

    @Override
    protected void initData() {
        BmobQuery<SignUpBean> query = new BmobQuery<>();
//        query.addWhereEqualTo("")

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

}
