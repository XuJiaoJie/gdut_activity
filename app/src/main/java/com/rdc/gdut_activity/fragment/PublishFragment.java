package com.rdc.gdut_activity.fragment;

import android.os.Bundle;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;

public class PublishFragment extends BaseFragment{

    public static PublishFragment newInstance() {

        Bundle args = new Bundle();

        PublishFragment fragment = new PublishFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayoutResourceId() {

        return R.layout.fragment_publish;
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
