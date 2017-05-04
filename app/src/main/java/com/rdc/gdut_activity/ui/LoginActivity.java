package com.rdc.gdut_activity.ui;

import android.widget.Button;
import android.widget.EditText;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.presenter.LoginPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginView {


    @InjectView(R.id.et_login_username)
    EditText etUsername;
    @InjectView(R.id.et_login_password)
    EditText etPassword;
    @InjectView(R.id.btn_login_login)
    Button btnLoginLogin;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void showProgress(int visibility) {
    }

    @Override
    public String getUserPhone() {
        return etUsername.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return etPassword.getText().toString();
    }

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {
        mLoginPresenter.login();
    }
}

