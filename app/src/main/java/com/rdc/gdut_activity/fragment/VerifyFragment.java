package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


public class VerifyFragment extends BaseFragment implements OnLoadMoreDataRv{
    private static final String TAG = "VerifyFragment";
    @InjectView(R.id.rv_verify_fragment_list)
    RecyclerView mRvVerifyFragmentList;
    @InjectView(R.id.srl_verify_fragment)
    SwipeRefreshLayout mSrlVerifyFragment;
    private String mType; //Fragment类型标志，如审核和未审核
    private List<ActivityInfoBean> mBeanList;
    private VerifyRecyclerAdapter mAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;

    /**
     * 创建Fragment，并传入参数
     */
    public static VerifyFragment newInstance(String type) {
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentType", type);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_verify;
    }

    @Override
    protected void initData(Bundle bundle) {
        mType = bundle.getString("fragmentType");
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
    }

    @Override
    protected void initView() {
        mRvVerifyFragmentList.setHasFixedSize(true);
        mRvVerifyFragmentList.setLayoutManager(new LinearLayoutManager(mBaseActivity,LinearLayoutManager.VERTICAL,false));
        Data();
    }

    @Override
    protected void setListener() {

    }


    //虚拟数据
    private void Data(){
        for (int i=0;i<10;i++){
            ActivityInfoBean bean = new ActivityInfoBean();
            bean.setActivityName("研发中心招新宣讲会开始啦啦啦  " + i);
            bean.setActivityTime("2017-5-8 19:00");
            bean.setActivityLocation("广东工业大学工一学术报告厅   " +i);
            bean.setPublishTime("2017-5-7 12:00");
            bean.setPublisherName("广东工业大学团委");
            mBeanList.add(bean);
        }
        mAdapter.updataData(mBeanList);
        if (null == mLoadMoreAdapter){
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter,this);
            mRvVerifyFragmentList.setAdapter(mLoadMoreAdapter);
        }else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 加载更多触发事件
     */
    @Override
    public void loadMoreData() {

    }
}
