package com.rdc.gdut_activity.ui;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.presenter.LoginPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ILoginView;
import com.rdc.gdut_activity.utils.CheckInfoUtil;
import com.rdc.gdut_activity.view.LoadingDialog;

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
    public void initData() {
        initPermission();
        mLoginPresenter = new LoginPresenter(this);
        mInstance = this;
    }

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
        showToast("用户名或密码错误!");
    }

    @Override
    public void showProgress(boolean isVisiable) {
        if (mDialog == null) {
            mDialog = new LoadingDialog(this, Constant.LOADING_STYLE);
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
        String toast = CheckInfoUtil.checkLogin(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString());
        if (toast != null) {
            showToast(toast);
        } else {
            isRight = true;
        }
        return isRight;
    }

    /**
     * 申请权限
     */
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "权限申请失败,部分功能无法使用!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

