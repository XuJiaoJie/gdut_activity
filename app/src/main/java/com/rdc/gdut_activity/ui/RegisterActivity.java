package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.presenter.RegisterPresenter;
import com.rdc.gdut_activity.ui.viewinterface.IRegisterView;

import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView {


    @InjectView(R.id.et_register_username)
    EditText mEtUsername;
    @InjectView(R.id.et_register_password)
    EditText mEtPassword;
    @InjectView(R.id.et_register_password_again)
    EditText mEtPasswordAgain;
    @InjectView(R.id.btn_register_register)
    Button mBtnRegister;
    private RegisterPresenter mPresenter;

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        mPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.btn_register_register)
    public void onViewClicked() {
        if (checkUserInfo()) {
            mPresenter.register();
        } else {
            showToast("请检查用户名和密码!");
        }
    }

    private boolean checkUserInfo() {
        boolean isRight = false;
        String userName = mEtUsername.getText().toString().trim();
        String userPassword = mEtPassword.getText().toString().trim();
        String userPasswordAgain = mEtPasswordAgain.getText().toString().trim();
        if ("".equals(userName) || "".equals(userPassword) || "".equals(userPasswordAgain)) {
            isRight = false;
        } else {
            if (userPassword.equals(userPasswordAgain)) {
                isRight = true;
            }
        }
        return isRight;
    }

    @Override
    public String getUserPhone() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mEtUsername.getText().toString();
    }

    @Override
    public void registerSuccess(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_info", user);
        intent.putExtras(bundle);
        startActivity(intent);
        LoginActivity.mInstance.finish();
        finish();
    }

    @Override
    public void registerFailed(String error) {

    }
}
