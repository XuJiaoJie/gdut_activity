package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.ReleasedActRvAdapter;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import java.util.ArrayList;
import java.util.List;

public class ReleasedActListFragment extends BaseFragment {

    private RecyclerView mRvActList;

    private List<ActivityInfoBean> mActInfoList;

    private void initTmpData() {
        mActInfoList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ActivityInfoBean activityInfoBean =
                    new ActivityInfoBean();
            mActInfoList.add(activityInfoBean);
        }
    }


    public static ReleasedActListFragment newInstance() {

        Bundle args = new Bundle();

        ReleasedActListFragment fragment = new ReleasedActListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_released_list;
    }

    @Override
    protected void initData(Bundle bundle) {
        initTmpData();
    }

    @Override
    protected void initView() {
        mRvActList = (RecyclerView) mRootView.findViewById(R.id.rv_act_list);
        mRvActList.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        mRvActList.setAdapter(new ReleasedActRvAdapter(mActInfoList, mBaseActivity));
    }

    @Override
    protected void setListener() {
    }
}
