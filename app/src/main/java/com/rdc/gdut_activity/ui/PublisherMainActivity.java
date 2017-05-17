package com.rdc.gdut_activity.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
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

public class PublisherMainActivity extends BaseActivity {

    @InjectView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    @InjectView(R.id.vp_main_vp)
    ViewPager mVpMainVp;

    private static final String TAG = "PublisherMainActivity";
    private FragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private int[] mTabRes = new int[]{R.drawable.selector_released_activity,
            R.drawable.selector_publish_activity,
            R.drawable.selector_publisher};
    private long mLastPressed;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_publisher_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_push, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_push:
                startActivity(PushActivity.newIntent(this));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        mTitleList = new ArrayList<>();
        mTitleList.add("已发布");
        mTitleList.add("发布");
        mTitleList.add("我");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(VerifyFragment.newInstance("已发布"));
        mFragmentList.add(PublishFragment.newInstance());
        mFragmentList.add(UserFragment.newInstance());

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        mVpMainVp.setAdapter(mFragmentAdapter);
        mVpMainVp.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mVpMainVp);

        for (int i = 0; i < mTitleList.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_publisher_tab);
                TextView title = (TextView) itemTab.getCustomView().findViewById(R.id.tv_item_name);
                ImageView ivIcon = (ImageView) itemTab.getCustomView().findViewById(R.id.iv_icon);
                title.setText(mTitleList.get(i));
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
}
