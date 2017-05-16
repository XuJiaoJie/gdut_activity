package com.rdc.gdut_activity.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.SignUpAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.contract.SignUpContract;
import com.rdc.gdut_activity.presenter.SignUpPresenter;
import com.rdc.gdut_activity.view.LoadingDialog;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, SignUpContract.View {

    @InjectView(R.id.tb_sign_up)
    TopBar mTbSignUp;
    @InjectView(R.id.lv_sign_up)
    ListView mLvSignUp;
    @InjectView(R.id.sc_sign_up)
    ScrollView mScSignUp;
    Button mBtnSignUp;

    private Map<String, String> mFormMap;
    private List<String> mFormList;
    private SignUpAdapter mSignUpAdapter;
    private LoadingDialog mDialog;
    private SignUpPresenter mSignUpPresenter;
    private ActivityInfoBean mActivityInfoBean;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initData() {
        mFormMap = new HashMap<>();
        mFormList = new ArrayList<>();
        mFormList.add("1");
        mFormList.add("2");
        mFormList.add("3");
        mFormList.add("4");
        mFormList.add("5");
        mFormList.add("6");
        mFormList.add("7");
        mFormList.add("8");
        mFormList.add("9");
        mFormList.add("10");
        mSignUpAdapter = new SignUpAdapter(this, mFormList);
        mActivityInfoBean = new ActivityInfoBean();
        mActivityInfoBean.setObjectId("9a96ba3a7c");
        mSignUpPresenter = new SignUpPresenter(this);
    }

    @Override
    protected void initView() {
        mTbSignUp.setButtonBackground(R.drawable.iv_back, 0);
        setFootView();
        mLvSignUp.setAdapter(mSignUpAdapter);
        mScSignUp.scrollTo(0, 0);
    }

    private void setFootView() {
        View footView = LayoutInflater.from(this).inflate(R.layout.item_list_foot_sign_up, null);
        mBtnSignUp = (Button) footView.findViewById(R.id.btn_sign_up);
        mLvSignUp.addFooterView(footView);
    }

    @Override
    protected void initListener() {
        mBtnSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                if (checkInfoSuccess()) {
                    mSignUpPresenter.signUp(mFormMap);
                } else {
                    showToast("请填写完全部信息!");
                }
                break;
        }

    }

    /**
     * 检测是否为空
     *
     * @return
     */
    private boolean checkInfoSuccess() {
        mFormMap = mSignUpAdapter.getFormMap();
        if (mFormMap.size() < mFormList.size()) {
            return false;
        }
        for (String value : mFormMap.values()) {
            Log.d("value", "checkInfoSuccess: " + value);
            if ("".equals(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void signUpSuccess() {
        showToast("报名成功!");
    }

    @Override
    public void signUpFailed(String error) {
        showToast("报名失败!");
    }

    @Override
    public void showProgress(boolean isShow) {
        if (mDialog == null) {
            mDialog = new LoadingDialog(this, Constant.LOADING_STYLE);
        }
        if (isShow) {
            mBtnSignUp.setEnabled(false);
            mDialog.show();
        } else {
            mDialog.dismiss();
            mBtnSignUp.setEnabled(true);
        }
    }

    @Override
    public ActivityInfoBean getActivityBean() {
        if (mActivityInfoBean != null) {
            return mActivityInfoBean;
        }
        return null;
    }
}
