package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.presenter.LoginPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginView {


    @InjectView(R.id.et_login_username)
    EditText mEtUsername;
    @InjectView(R.id.et_login_password)
    EditText mEtPassword;
    @InjectView(R.id.btn_login_login)
    Button mBtnLoginLogin;
    @InjectView(R.id.btn_login_forget)
    Button mBtnLoginForget;
    @InjectView(R.id.btn_login_register)
    Button mBtnLoginRegister;
    private LoginPresenter mLoginPresenter;
    public static LoginActivity mInstance = null;

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mLoginPresenter = new LoginPresenter(this);
        mInstance = this;
    }

    @Override
    public void loginSuccess(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_info", user);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void showProgress(int visibility) {
    }

    @Override
    public String getUserPhone() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mEtPassword.getText().toString();
    }

    @OnClick({R.id.btn_login_login, R.id.btn_login_forget, R.id.btn_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                if (checkUserInfo()) {
                    mLoginPresenter.login();
                } else {
                    showToast("请检查用户名和密码!");
                }
                break;
            case R.id.btn_login_forget:
                break;
            case R.id.btn_login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean checkUserInfo() {
        boolean isRight = false;
        if ("".equals(mEtUsername.getText().toString().trim()) || "".equals(mEtPassword.getText().toString().trim())) {
            isRight = false;
        } else {
            isRight = true;
        }
        return isRight;
    }
}

