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

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.vp_main_vp)
    ViewPager mVpMainVp;
    @InjectView(R.id.tabLayout_main_tab)
    TabLayout mTabLayoutMainTab;

    private String[] titles = new String[]{"主页", "工具", "消息", "我的"};

    private FragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragments;
    private List<String> mTitles;

    private int[] mImgs = new int[]{R.drawable.home_main_seletor,R.drawable.tool_main_selector,
            R.drawable.message_main_selector,R.drawable.user_main_selector};

    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

        mTitles = new ArrayList<>();
        for (int i = 0 ; i < titles.length;i++) {
            mTitles.add(titles[i]);
        }

        mFragments  = new ArrayList<>();
            mFragments.add(MainFragment.newInstance(0,"主界面"));
            mFragments.add(ToolFragment.newInstance(1,"小工具"));
            mFragments.add(MessageFragment.newInstance(2,"消息"));
            mFragments.add(UserFragment.newInstance(3,"个人中心"));


        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mVpMainVp.setAdapter(mFragmentAdapter);//给ViewPager设置适配器
        mTabLayoutMainTab.setupWithViewPager(mVpMainVp);//将TabLayout和ViewPager关联起来
        mTabLayoutMainTab.setSelectedTabIndicatorHeight(0);//不显示tab底部的横线

        for(int i = 0;i<mTitles.size(); i++){
            //获得对应位置的Tab
            TabLayout.Tab  itemTab = mTabLayoutMainTab.getTabAt(i);
            if(itemTab != null){
                itemTab.setCustomView(R.layout.item_tab_main_activity);
                TextView title  = (TextView)itemTab.getCustomView().findViewById(R.id.tv_main_tab);
                title.setText(mTitles.get(i));
                ImageView image = (ImageView)itemTab.getCustomView().findViewById(R.id.iv_main_tab);
                //// TODO: 2017/5/4 设置tab图标
                image.setImageResource(mImgs[i]);
            }
        }
        mTabLayoutMainTab.getTabAt(0).getCustomView().setSelected(true);
        mTabLayoutMainTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }


}
