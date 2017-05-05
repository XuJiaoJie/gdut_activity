package com.rdc.gdut_activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by zjz on 2017/5/4.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public List<Fragment> list;
    private List<String> titles ;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles ) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
