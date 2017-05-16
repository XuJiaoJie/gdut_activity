package com.rdc.gdut_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.gdut_activity.adapter.FragmentAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.fragment.MainFragment;
import com.rdc.gdut_activity.fragment.MessageFragment;
import com.rdc.gdut_activity.fragment.ToolFragment;
import com.rdc.gdut_activity.fragment.UserFragment;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.vp_main_vp)
    ViewPager mVpMainVp;
    @InjectView(R.id.tabLayout_main_tab)
    TabLayout mTabLayoutMainTab;
    @InjectView(R.id.topbar_activity_main)
    TopBar mTopBar;

    private String[] titles = new String[]{"主页", "工具", "消息", "我的"};

    private FragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragments;
    private List<String> mTitles;

    private int[] mImgs = new int[]{R.drawable.home_main_seletor, R.drawable.tool_main_selector,
            R.drawable.message_main_selector, R.drawable.user_main_selector};

    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

        mTitles = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mTitles.add(titles[i]);
        }

        mFragments = new ArrayList<>();
        mFragments.add(MainFragment.newInstance(0, "主界面"));
        mFragments.add(ToolFragment.newInstance(1, "小工具"));
        mFragments.add(MessageFragment.newInstance(2, "消息"));
        mFragments.add(UserFragment.newInstance(3, "个人中心"));


        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mVpMainVp.setAdapter(mFragmentAdapter);//给ViewPager设置适配器
        mTabLayoutMainTab.setupWithViewPager(mVpMainVp);//将TabLayout和ViewPager关联起来
        mTabLayoutMainTab.setSelectedTabIndicatorHeight(0);//不显示tab底部的横线

        for (int i = 0; i < mTitles.size(); i++) {
            //获得对应位置的Tab
            TabLayout.Tab itemTab = mTabLayoutMainTab.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_main_activity);
                TextView title = (TextView) itemTab.getCustomView().findViewById(R.id.tv_main_tab);
                title.setText(mTitles.get(i));
                ImageView image = (ImageView) itemTab.getCustomView().findViewById(R.id.iv_main_tab);
                image.setImageResource(mImgs[i]);
            }
        }
        mTabLayoutMainTab.getTabAt(0).getCustomView().setSelected(true);
        mTabLayoutMainTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initView() {
        mTopBar.setButtonBackground(0, R.drawable.home_main_selected);

        mVpMainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置topbar标题及按钮的图片
                if (position == 0) {
                    mTopBar.setTitle("主页");
                    mTopBar.setButtonBackground(0, R.drawable.home_main_selected);
                } else if (position == 1) {
                    mTopBar.setTitle("工具");
                    mTopBar.setButtonBackground(R.drawable.message_main_selected, R.drawable.home_main_selected);
                } else if (position == 2) {
                    mTopBar.setTitle("消息");
                    mTopBar.setButtonBackground(R.drawable.message_main_selected, 0);
                } else {
                    mTopBar.setTitle("个人中心");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initListener() {

        mTopBar.setOnTopbarClickListener(new TopBar.topbarClickListner() {
            @Override
            public void leftClick() {
                int position = mVpMainVp.getCurrentItem();
                switch (position) {
                    case 0:
                        MainFragment mainFragment = (MainFragment) mFragments.get(position);
                        mainFragment.topbarLeftButtonClick();
                        break;
                    case 1:
                        ToolFragment toolFragment = (ToolFragment) mFragments.get(position);
                        toolFragment.topbarLeftButtonClick();
                        break;
                    case 2:
                        MessageFragment messageFragment = (MessageFragment) mFragments.get(position);
                        messageFragment.topbarLeftButtonClick();
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void rightClick() {
                int position = mVpMainVp.getCurrentItem();
                switch (position) {
                    case 0:
                        MainFragment mainFragment = (MainFragment) mFragments.get(position);
                        mainFragment.topbarRightButtonClick();
                        break;
                    case 1:
                        ToolFragment toolFragment = (ToolFragment) mFragments.get(position);
                        toolFragment.topbarRightButtonClick();
                        break;
                    case 2:
                        MessageFragment messageFragment = (MessageFragment) mFragments.get(position);
                        messageFragment.topbarRightButtonClick();
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 返回键不销毁Activity
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
