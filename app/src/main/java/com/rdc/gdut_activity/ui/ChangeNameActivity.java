package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.InjectView;

public class ChangeNameActivity extends BaseActivity implements TopBar.topbarClickListner {

    @InjectView(R.id.et_user_name)
    EditText etUserName;
    @InjectView(R.id.tb_change_name)
    TopBar tbChangeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tbChangeName.setButtonBackground(R.drawable.icon_back, R.drawable.ic_check_white_18dp);
        tbChangeName.setOnTopbarClickListener(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        etUserName.setText(name);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Intent intent = new Intent();
        intent.putExtra("username", etUserName.getText().toString());
        setResult(1, intent);
        finish();
    }
}
