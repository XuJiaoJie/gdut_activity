package com.rdc.gdut_activity.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.SignUpAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.view.ScrollForListView;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.tb_sign_up)
    TopBar mTbSignUp;
    @InjectView(R.id.lv_sign_up)
    ScrollForListView mLvSignUp;
    Button mBtnSignUp;
    @InjectView(R.id.sc_sign_up)
    ScrollView mScSignUp;
    private Map<String, String> mFormMap;
    private List<String> mFormList;
    private SignUpAdapter mSignUpAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initData() {
        mFormMap = new HashMap<>();
        mFormList = new ArrayList<>();
        mFormList.add("学号");
        mFormList.add("学号");
        mFormList.add("学号");
        mFormList.add("学号");
        mFormList.add("学号");
        mFormList.add("学号");
        mFormList.add("学号");
        mSignUpAdapter = new SignUpAdapter(this, mFormList);
        ActivityInfoBean bean = new ActivityInfoBean();

    }

    @Override
    protected void initView() {
        mTbSignUp.setButtonBackground(R.drawable.iv_back,0);
        setFootView();
        mLvSignUp.setAdapter(mSignUpAdapter);
        mScSignUp.scrollTo(0, 0);
    }

    private void setFootView() {
        View footView = LayoutInflater.from(this).inflate(R.layout.item_list_foot_sign_up, null);
        mBtnSignUp = (Button) footView.findViewById(R.id.btn_sign_up);
        mLvSignUp.addFooterView(footView);
    }

    @Override
    protected void initListener() {
        mBtnSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                showToast("sadadsad");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
