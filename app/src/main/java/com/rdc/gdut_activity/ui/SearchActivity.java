package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.MainFragmentContract;
import com.rdc.gdut_activity.presenter.MainFragmentPresenterImpl;
import com.rdc.gdut_activity.view.DeletableEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements OnLoadMoreDataRv, OnClickRecyclerViewListener, MainFragmentContract.View {


    @InjectView(R.id.back_activity_search)
    ImageView mBackActivitySearch;
    @InjectView(R.id.et_activity_search)
    DeletableEditText mEtActivitySearch;
    @InjectView(R.id.search_activity_search)
    ImageView mSearchActivitySearch;
    @InjectView(R.id.srl_activity_search)
    SwipeRefreshLayout mSrlActivitySearch;
    @InjectView(R.id.rv_activity_search)
    RecyclerView mRvActivitySearch;

    private VerifyRecyclerAdapter mAdapter;
    private MainFragmentContract.Presenter mPresenter;
    private List<ActivityInfoBean> mBeanList;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        mPresenter = new MainFragmentPresenterImpl(this);
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
        mAdapter.setOnRecyclerViewListener(this);
    }

    @Override
    protected void initView() {
        mRvActivitySearch.setHasFixedSize(true);
        mRvActivitySearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//设置Item的排列方式
    }

    @Override
    protected void initListener() {
        mSrlActivitySearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNameEmpty()){
                    mPresenter.onRefresh("全部");
                }
                else{
                    mPresenter.onRefreshByName("全部",mEtActivitySearch.getText().toString());
                }
                mSrlActivitySearch.setRefreshing(true);
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
        if(isNameEmpty()){
            mPresenter.onLoadMore("全部");
        }
        else{
            mPresenter.onLoadMoreByName("全部",mEtActivitySearch.getText().toString());
        }
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
            mRvActivitySearch.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
        mSrlActivitySearch.setRefreshing(false);
    }

    @Override
    public void onRefreshError(String s) {
        mSrlActivitySearch.setRefreshing(false);
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


    @OnClick({R.id.back_activity_search, R.id.search_activity_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_activity_search:
                finish();
                break;
            case R.id.search_activity_search:
                if(isNameEmpty()){
                    mPresenter.onRefresh("全部");
                }
                else{
                    mPresenter.onRefreshByName("全部",mEtActivitySearch.getText().toString());
                }
                break;
        }
    }

    private boolean isNameEmpty(){
        if(mEtActivitySearch.getText().toString().equals("")){
            return true;
        }
        return false;
    }
}
