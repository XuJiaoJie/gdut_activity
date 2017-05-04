package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.util.Log;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;


public class VerifyFragment extends BaseFragment {
    private static final String TAG = "VerifyFragment";
    private String mType; //Fragment类型标志，如审核和未审核

    /**
     *创建Fragment，并传入参数
     */
    public static VerifyFragment newInstance(String type){
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentType",type);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_verify;
    }

    @Override
    protected void initData(Bundle bundle) {
        mType = bundle.getString("fragmentType");
        Log.e(TAG, "initData: "+ mType);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
