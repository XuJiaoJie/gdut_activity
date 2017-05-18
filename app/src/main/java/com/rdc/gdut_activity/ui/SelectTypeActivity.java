package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.MainFragmentContract;
import com.rdc.gdut_activity.presenter.MainFragmentPresenterImpl;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class SelectTypeActivity extends BaseActivity implements OnLoadMoreDataRv, OnClickRecyclerViewListener, MainFragmentContract.View{

    @InjectView(R.id.tb_activity_select_type)
    TopBar mTbActivitySelectType;
    @InjectView(R.id.rv_select_type_activity)
    RecyclerView mRvSelectTypeActivity;
    @InjectView(R.id.srl_select_type_activity)
    SwipeRefreshLayout mSrlSelectTypeActivity;

    private String mType ;
    private VerifyRecyclerAdapter mAdapter;
    private MainFragmentContract.Presenter mPresenter ;
    private List<ActivityInfoBean> mBeanList;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_select_type;
    }

    @Override
    protected void initData() {
        mType = getIntent().getStringExtra("type");
        mPresenter = new MainFragmentPresenterImpl(this);
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
        mAdapter.setOnRecyclerViewListener(this);
    }

    @Override
    protected void initView() {
        mTbActivitySelectType.setTitle(mType);
        mRvSelectTypeActivity.setHasFixedSize(true);
        mRvSelectTypeActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//设置Item的排列方式

    }

    @Override
    protected void initListener() {
        mPresenter.onRefresh(mType);

        mSrlSelectTypeActivity.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh(mType);
                mSrlSelectTypeActivity.setRefreshing(true);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = DetailsActivity.newIntent(this, mBeanList.get(position));
        startActivity(intent);
    }

    @Override
    public void loadMoreData() {
        mPresenter.onLoadMore(mType);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onRefreshSuccess(List<ActivityInfoBean> list) {
        mBeanList = list;
        mAdapter.updataData(list);
        if (null == mLoadMoreAdapter) {
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter, this);
            mRvSelectTypeActivity.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
        mSrlSelectTypeActivity.setRefreshing(false);
    }

    @Override
    public void onRefreshError(String s) {
        mSrlSelectTypeActivity.setRefreshing(false);
        showToast(s);
    }

    @Override
    public void onLoadMoreSuccess(List<ActivityInfoBean> list) {

        if (list.size() != 0) {
            mBeanList.addAll(list);
            mAdapter.appendData(list);
        } else {
            mLoadMoreAdapter.setHasMoreData(false);
        }
        mLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreError(String s) {
        showToast(s);
    }
}
