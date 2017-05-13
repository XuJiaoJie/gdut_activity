package com.rdc.gdut_activity.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.VerifyViewPagerAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.fragment.VerifyFragment;
import com.rdc.gdut_activity.view.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


public class VerifyActivity extends BaseActivity {
    private static final String TAG = "VerifyActivity";
    @InjectView(R.id.nts_verify_tab)
    NavigationTabStrip mNtsVerifyTab;
    @InjectView(R.id.vp_verify_content)
    ViewPager mVpVerifyContent;

    private List<Fragment> mFragmentList;
    private VerifyFragment mVerifyNotFragment,mVerifyPassFragment;
    private VerifyViewPagerAdapter mVerifyViewPagerAdapter;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_verify;
    }

    @Override
    public void initData() {
        mVerifyNotFragment = VerifyFragment.newInstance("未审核");
        mVerifyPassFragment = VerifyFragment.newInstance("已审核");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mVerifyNotFragment);
        mFragmentList.add(mVerifyPassFragment);
        mVerifyViewPagerAdapter = new VerifyViewPagerAdapter(this.getSupportFragmentManager(),mFragmentList);
    }

    @Override
    public void initView() {
        mVpVerifyContent.setAdapter(mVerifyViewPagerAdapter);
        mNtsVerifyTab.setViewPager(mVpVerifyContent,0);
    }

    @Override
    protected void initListener() {

    }
}
