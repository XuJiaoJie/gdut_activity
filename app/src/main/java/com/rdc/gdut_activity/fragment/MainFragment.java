package com.rdc.gdut_activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.MainFragmentContract;
import com.rdc.gdut_activity.presenter.MainFragmentPresenterImpl;
import com.rdc.gdut_activity.ui.DetailsActivity;
import com.rdc.gdut_activity.ui.SearchActivity;
import com.rdc.gdut_activity.ui.SelectTypeActivity;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.rdc.gdut_activity.constant.Constant.ACTIVITY_ALL;

/**
 * Created by ThatNight on 2017.4.26.
 */

public class MainFragment extends BaseFragment implements OnLoadMoreDataRv, OnClickRecyclerViewListener, MainFragmentContract.View {


    @InjectView(R.id.rv_main_fragment_list)
    RecyclerView mRvMainFragmentList;
    @InjectView(R.id.srl_main_fragment)
    SwipeRefreshLayout mSrlMainFragment;

    private int mTitle;
    private String mMessage;
    private TopBar mTopBar;
    private List<ActivityInfoBean> mBeanList;
    private VerifyRecyclerAdapter mAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;
    private MainFragmentContract.Presenter mPresenter;


    /**
     * 用来获取fragment实例的方法，这里可以让Activity给fragment设置参数,参数可以在下面的initData方法中的bundle中取出
     */
    public static MainFragment newInstance(int title, String message) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("title", title);
        bundle.putString("message", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    public void topbarRightButtonClick() {
        startActivity(new Intent(mBaseActivity, SearchActivity.class));
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mPresenter = new MainFragmentPresenterImpl(this);
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
        mAdapter.setOnRecyclerViewListener(this);
    }


    @Override
    protected void initView() {
        mSrlMainFragment.setColorSchemeResources(R.color.colorPrimary);
        mRvMainFragmentList.setHasFixedSize(true);
        mRvMainFragmentList.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));//设置Item的排列方式
    }

    @Override
    protected void setListener() {
        mPresenter.onRefresh(ACTIVITY_ALL);
        mSrlMainFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh(ACTIVITY_ALL);
                mSrlMainFragment.setRefreshing(true);
            }
        });
    }

    private void setParams(Bundle bundle) {
        mTitle = bundle.getInt("title");
        mMessage = bundle.getString("message");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefreshSuccess(List<ActivityInfoBean> list) {
        mBeanList = list;
        mAdapter.updataData(list);
        if (null == mLoadMoreAdapter) {
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter, this);
            mRvMainFragmentList.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }

        mSrlMainFragment.setRefreshing(false);
    }

    @Override
    public void onRefreshError(String s) {
        mSrlMainFragment.setRefreshing(false);
        showToast(s);
    }

    @Override
    public void onLoadMoreSuccess(List<ActivityInfoBean> list) {
        //   Log.e(TAG, "onLoadMoreSuccess: " + list.size());
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

    @Override
    public void loadMoreData() {
        mPresenter.onLoadMore(ACTIVITY_ALL);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = DetailsActivity.newIntent(mBaseActivity, mBeanList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


    @OnClick({R.id.item_activity_main_fragment, R.id.item_speech_main_fragment, R.id.item_competition_main_fragment, R.id.item_club_main_fragment, R.id.item_college_main_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_activity_main_fragment:
                startSelectTypeActivity("公益");
                break;
            case R.id.item_speech_main_fragment:
                startSelectTypeActivity("讲座");
                break;
            case R.id.item_competition_main_fragment:
                startSelectTypeActivity("比赛");
                break;
            case R.id.item_club_main_fragment:
                startSelectTypeActivity("演出");
                break;
            case R.id.item_college_main_fragment:
                startSelectTypeActivity("其他");
                break;
        }
    }

    private void startSelectTypeActivity(String type) {
        Intent intent = new Intent(mBaseActivity, SelectTypeActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

}
