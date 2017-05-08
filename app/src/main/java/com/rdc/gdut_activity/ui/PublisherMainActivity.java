package com.rdc.gdut_activity.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.model.PublisherDataModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PublisherMainActivity extends BaseActivity {

    @InjectView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @InjectView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_publisher_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

        mFragments = PublisherDataModel.getFragment("");
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bindFragment(tab.getPosition());
//
//                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//                    if (i == tab.getPosition()) {
//                        // TODO: 2017/5/6 0006 此处应该设置为选中时图标 但是目前暂未确定资源
//                        mTabLayout.getTabAt(i).setCustomView(PublisherDataModel.getTabItemView(PublisherMainActivity.this, i));
////                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable())
//                    } else {
//                        mTabLayout.getTabAt(i).setCustomView(PublisherDataModel.getTabItemView(PublisherMainActivity.this, i));
//
//                    }
//                }

            }
            // TODO: 2017/5/6 0006 Fragment 视图待绑定

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(PublisherDataModel.getTabItemView(this, i)));
        }

    }

    @Override
    protected void initListener() {

    }

    private void bindFragment(int position) {
        // TODO: 2017/5/8 0008 到这里再初始化也可以
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragments[0];
                break;
            case 1:
                fragment = mFragments[1];
                break;
            case 2:
                fragment = mFragments[2];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
