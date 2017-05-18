package com.rdc.gdut_activity.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.FragmentAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.fragment.PublishFragment;
import com.rdc.gdut_activity.fragment.UserFragment;
import com.rdc.gdut_activity.fragment.VerifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PublisherMainActivity extends BaseActivity {

    @InjectView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    @InjectView(R.id.vp_main_vp)
    ViewPager mVpMainVp;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.iv_push)
    ImageView mIvPush;

    private static final String TAG = "PublisherMainActivity";
    private int[] mTabRes = new int[]{
            R.drawable.selector_released_activity,
            R.drawable.selector_publish_activity,
            R.drawable.selector_publisher};
    private long mLastPressed;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_publisher_main;
    }

    @Override
    protected void initData() {
        List<String> titleList = new ArrayList<>();
        titleList.add("已发布");
        titleList.add("发布");
        titleList.add("我");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(VerifyFragment.newInstance("已发布"));
        fragmentList.add(PublishFragment.newInstance());
        fragmentList.add(UserFragment.newInstance());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mVpMainVp.setAdapter(fragmentAdapter);
        mVpMainVp.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mVpMainVp);

        for (int i = 0; i < titleList.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_publisher_tab);
                TextView title = (TextView) itemTab.getCustomView().findViewById(R.id.tv_item_name);
                ImageView ivIcon = (ImageView) itemTab.getCustomView().findViewById(R.id.iv_user_icon);
                title.setText(titleList.get(i));
                ivIcon.setImageResource(mTabRes[i]);
            }
        }
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastPressed > 2000) {
            showToast("再按一次退出");
            mLastPressed = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @OnClick(R.id.iv_push)
    public void onViewClicked() {
        startActivity(PushActivity.newIntent(this));
    }
}
