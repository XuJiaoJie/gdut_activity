package com.rdc.gdut_activity.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.fragment.ReleasedFragment;

import java.util.ArrayList;
import java.util.List;

public class ReleasedActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<ActivityInfoBean> mActivityInfoBeen;

    private void initTestData() {
        mActivityInfoBeen = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActivityInfoBean activityInfoBean = new ActivityInfoBean("" + i, "" + 1, "" + i, "" + i, "" + 1, "" + i, "" + i, "" + 1);
            mActivityInfoBeen.add(activityInfoBean);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_released);
        initTestData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ActivityInfoBean activityInfoBean = mActivityInfoBeen.get(position);
                return ReleasedFragment.newInstance(activityInfoBean);
            }

            @Override
            public int getCount() {
                return mActivityInfoBeen.size();
            }
        });

//        Fragment fragment = fragmentManager.findFragmentById(R.id.released_fragment_container);
//        if (fragment == null) {
//            fragment = new ReleasedFragment();
//            fragmentManager.beginTransaction()
//                    .add(R.id.released_fragment_container, fragment)
//                    .commit();
//        }
    }
}
