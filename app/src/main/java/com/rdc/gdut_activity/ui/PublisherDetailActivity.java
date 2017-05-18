package com.rdc.gdut_activity.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.view.CircleImageView;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.InjectView;
import butterknife.OnClick;

public class PublisherDetailActivity extends BaseActivity {


    @InjectView(R.id.top_bar)
    TopBar mTopBar;
    @InjectView(R.id.civ_publisher_icon)
    CircleImageView mCivPublisherIcon;
    @InjectView(R.id.ll_publisher_icon)
    LinearLayout mLlPublisherIcon;
    @InjectView(R.id.tv_publisher_name)
    TextView mTvPublisherName;
    @InjectView(R.id.ll_publisher_name)
    LinearLayout mLlPublisherName;
    @InjectView(R.id.tv_publisher_introduction)
    TextView mTvPublisherIntroduction;
    @InjectView(R.id.ll_publisher_introduction)
    LinearLayout mLlPublisherIntroduction;
    @InjectView(R.id.tv_person_to_contact)
    TextView mTvPersonToContact;
    @InjectView(R.id.ll_person_to_contact)
    LinearLayout mLlPersonToContact;
    @InjectView(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @InjectView(R.id.ll_publisher_phone_number)
    LinearLayout mLlPublisherPhoneNumber;
    @InjectView(R.id.tv_email)
    TextView mTvEmail;
    @InjectView(R.id.ll_email)
    LinearLayout mLlEmail;
    @InjectView(R.id.tv_publisher_password)
    TextView mTvPublisherPassword;
    @InjectView(R.id.ll_publisher_password)
    LinearLayout mLlPublisherPassword;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_publisher_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.ll_publisher_icon, R.id.ll_publisher_name, R.id.ll_publisher_introduction, R.id.ll_person_to_contact, R.id.ll_publisher_phone_number, R.id.ll_email, R.id.ll_publisher_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_publisher_icon:
                break;
            case R.id.ll_publisher_name:

                break;
            case R.id.ll_publisher_introduction:
                break;
            case R.id.ll_person_to_contact:
                break;
            case R.id.ll_publisher_phone_number:
                break;
            case R.id.ll_email:
                break;
            case R.id.ll_publisher_password:
                break;
        }
    }
}
