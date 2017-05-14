package com.rdc.gdut_activity.ui;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.presenter.LoginPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;

import butterknife.ButterKnife;
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
    private Dialog mDialog;


    @Override
    protected void initView() {
        mEtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mBtnLoginLogin.performClick();
                    return true;
                }
                return false;
            }
        });
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
    public void loginSuccess(int permission) {
        Intent intent = new Intent();
        if (Constant.USER_STUDENT == permission) {
            intent.setClass(this, MainActivity.class);
        } else if (Constant.USER_PUBLISHER == permission) {
            intent.setClass(this, PublisherMainActivity.class);
        } else if (Constant.USER_ADMIN == permission) {
            intent.setClass(this, VerifyActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(String error) {
        //showToast("用户名或密码错误!");
        showToast(error);
    }

    @Override
    public void showProgress(boolean isVisiable) {
        if (mDialog == null) {
            mDialog = new Dialog(this, R.style.ActionProgressStyle);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null);
            Window dialogWindow = mDialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams wl = dialogWindow.getAttributes();
            wl.y = 20;
            dialogWindow.setAttributes(wl);
            mDialog.setContentView(view, wl);
            mDialog.setCanceledOnTouchOutside(false);
        }
        if (isVisiable) {
            mBtnLoginLogin.setEnabled(false);
            mDialog.show();
        } else {
            mBtnLoginLogin.setEnabled(true);
            mDialog.dismiss();
        }
    }

    @Override
    public String getUserPhone() {
        return mEtUsername.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return mEtPassword.getText().toString();
    }

    @OnClick({R.id.btn_login_login, R.id.btn_login_forget, R.id.btn_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                hideInputSoft(mEtPassword);
                if (checkUserInfo()) {
                    mLoginPresenter.login();
                } else {
                    showToast("请输入正确的用户名和密码!");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}

