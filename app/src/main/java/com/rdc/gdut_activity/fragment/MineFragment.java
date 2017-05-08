package com.rdc.gdut_activity.fragment;

import android.os.Bundle;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;

public class MineFragment extends BaseFragment {
    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
