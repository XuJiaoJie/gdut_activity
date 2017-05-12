package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
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
    public int setLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mEtPasswordAgain.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mBtnRegister.performClick();
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
    public void initData() {
        mPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.btn_register_register)
    public void onViewClicked() {
        hideInputSoft();
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
        return mEtUsername.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return mEtUsername.getText().toString();
    }

    @Override
    public void registerSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        LoginActivity.mInstance.finish();
        finish();
    }

    @Override
    public void registerFailed(String error) {
        showToast(error);
    }
}
