package com.rdc.gdut_activity.ui;

import android.app.Dialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.presenter.RegisterPresenter;
import com.rdc.gdut_activity.ui.viewinterface.IRegisterView;
import com.rdc.gdut_activity.utils.CheckInfoUtil;
import com.rdc.gdut_activity.view.LoadingDialog;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView, TopBar.topbarClickListner {


    @InjectView(R.id.et_register_username)
    EditText mEtUsername;
    @InjectView(R.id.et_register_password)
    EditText mEtPassword;
    @InjectView(R.id.et_register_password_again)
    EditText mEtPasswordAgain;
    @InjectView(R.id.btn_register_register)
    Button mBtnRegister;
    @InjectView(R.id.tb_register)
    TopBar mTbRegister;
    private RegisterPresenter mPresenter;
    private Dialog mDialog;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mTbRegister.setButtonBackground(R.drawable.iv_back,0);
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
        mTbRegister.setOnTopbarClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.btn_register_register)
    public void onViewClicked() {
        hideInputSoft(mEtPasswordAgain);
        if (checkUserInfo()) {
            mPresenter.register();
        }
    }

    private boolean checkUserInfo() {
        boolean isRight = false;
        String userName = mEtUsername.getText().toString().trim();
        String userPassword = mEtPassword.getText().toString();
        String userPasswordAgain = mEtPasswordAgain.getText().toString();
        String toast = CheckInfoUtil.checkRegister(userName, userPassword, userPasswordAgain);
        if (toast != null) {
            showToast(toast);
        } else {
            isRight = true;
        }
        return isRight;
    }

    @Override
    public String getUserPhone() {
        return mEtUsername.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return mEtPasswordAgain.getText().toString();
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

    @Override
    public void showProgress(boolean isVisiable) {
        if (mDialog == null) {
            mDialog = new LoadingDialog(this, Constant.LOADING_STYLE);
        }
        if (isVisiable) {
            mBtnRegister.setEnabled(false);
            mDialog.show();
        } else {
            mBtnRegister.setEnabled(true);
            mDialog.dismiss();
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
