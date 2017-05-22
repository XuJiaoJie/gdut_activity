package com.rdc.gdut_activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.Publisher;
import com.rdc.gdut_activity.contract.VerifyContract;
import com.rdc.gdut_activity.presenter.VerifyPresenterImpl;
import com.rdc.gdut_activity.ui.DetailsVerifyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import cn.bmob.v3.BmobUser;


public class VerifyFragment extends BaseFragment implements OnLoadMoreDataRv, OnClickRecyclerViewListener, VerifyContract.View {
    private static final String TAG = "VerifyFragment";
    @InjectView(R.id.rv_verify_fragment_list)
    RecyclerView mRvVerifyFragmentList;
    @InjectView(R.id.srl_verify_fragment)
    SwipeRefreshLayout mSrlVerifyFragment;
    private String mType; //Fragment类型标志，如审核和未审核
    private String mTypeCopy; //Fragment类型标志，如审核和未审核（备份）
    private String mPara;  //请求的参数
    private List<ActivityInfoBean> mBeanList;
    private VerifyRecyclerAdapter mAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;
    private VerifyContract.Presenter mPresenter;

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
        mSrlVerifyFragment.setColorSchemeResources(R.color.colorPrimary);
        mType = bundle.getString("fragmentType");
        mTypeCopy = mType;
        isPublisher();
        mPresenter = new VerifyPresenterImpl(this);
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
        mAdapter.setOnRecyclerViewListener(this);
    }

    @Override
    protected void initView() {
        mRvVerifyFragmentList.setHasFixedSize(true);
        mRvVerifyFragmentList.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));
//        mRvVerifyFragmentList.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
//
//        });

    }

    @Override
    protected void setListener() {
        mPresenter.onRefersh(mPara, mType);
        mSrlVerifyFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefersh(mPara, mType);
                mSrlVerifyFragment.setRefreshing(true);
            }
        });
    }

    private void isPublisher() {
        if (mType.equals("已发布")) {
            Publisher publisher = BmobUser.getCurrentUser(Publisher.class);
            mType = publisher.getObjectId();
            mPara = "mPublisher";
            Log.e(TAG, "isPublisher: " + mType + "   " + mPara + "  " + publisher.getUsername());
        } else {
            mPara = "mCheckStatus";
        }
    }

    /**
     * RecyclerView的点击和长按事件
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mBaseActivity, DetailsVerifyActivity.class);
        intent.putExtra("DetailsVerifyActivity", (Parcelable) mBeanList.get(position));
        if (mTypeCopy.equals("已审核")) {
            intent.putExtra("isVerifyType", false);
            intent.putExtra("ActivityTitle", "已审核活动详情");
        } else if (mTypeCopy.equals("已发布")) {
            intent.putExtra("isVerifyType", false);
            intent.putExtra("ActivityTitle", "已发布的活动");
        } else {
            intent.putExtra("isVerifyType", true);
        }
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    /**
     * 加载更多触发事件
     */
    @Override
    public void loadMoreData() {
        mPresenter.onLoadMore(mPara, mType);
    }


    /**
     * 以下为MVP数据更新回调
     */
    @Override
    public void onRefreshSuccess(List<ActivityInfoBean> list) {
        mBeanList = list;
        mAdapter.updataData(list);
        if (null == mLoadMoreAdapter) {
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter, this);
            mRvVerifyFragmentList.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
        mSrlVerifyFragment.setRefreshing(false);
    }

    @Override
    public void onRefreshError(String s) {
        mSrlVerifyFragment.setRefreshing(false);
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
