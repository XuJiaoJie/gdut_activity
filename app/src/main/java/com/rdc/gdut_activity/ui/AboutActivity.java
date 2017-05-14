package com.rdc.gdut_activity.ui;

import android.os.Bundle;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AboutActivity extends BaseActivity implements TopBar.topbarClickListner {

    @InjectView(R.id.tb_about)
    TopBar tbAbout;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tbAbout.setButtonBackground(R.drawable.icon_back, 0);
        tbAbout.setOnTopbarClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
