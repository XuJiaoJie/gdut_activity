package com.rdc.gdut_activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;

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
        mRvMainFragmentList.setHasFixedSize(true);
        mRvMainFragmentList.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));//设置Item的排列方式
//        Data();
//        mRvMainFragmentList.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
//
//        });
    }

    @Override
    protected void setListener() {
        mPresenter.onRefresh("这里填筛选内容");
        mSrlMainFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh("这里填筛选内容");
                mSrlMainFragment.setRefreshing(true);
            }
        });
    }

    private void setParams(Bundle bundle) {
        mTitle = bundle.getInt("title");
        mMessage = bundle.getString("message");
    }

    //虚拟数据
    private void Data() {
        for (int i = 0; i < 10; i++) {
            ActivityInfoBean bean = new ActivityInfoBean();
            bean.setActivityName("研发中心招新宣讲会开始啦啦啦  " + i);
            bean.setActivityTime("2017-5-8 19:00");
            bean.setActivityLocation("广东工业大学工一学术报告厅   " + i);
            bean.setPublishTime("2017-5-7 12:00");
            bean.setPublisherName("广东工业大学团委");
            bean.setActivityType("讲座");
            bean.setActivityHost("研发中心工作室");
            bean.setActivityDetail("多久啊看来大家啊克里夫剪啊开房间里的咖啡机的刻录机福克斯的减肥快圣诞分" +
                    "公司感受感受的分公司人发过过生日果然果然果然够节咖啡馆的精神科更加深刻搭公交可视对讲");
            List<String> picList = new ArrayList<>();
            picList.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/10762c593798466a.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/233a5f70512befcc.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg");
            bean.setImgUrlList(picList);
            HashMap<String, String> map = new HashMap<>();
            map.put("学号1", "");
            map.put("学号2", "");
            map.put("学号3", "");
            map.put("学号4", "");
            map.put("学号5", "");
            map.put("学号6", "");
            map.put("学号7", "");
            map.put("学号8", "");
            map.put("学号9", "");
            map.put("学号0", "");
            bean.setFormDataMap(map);
            mBeanList.add(bean);
        }
        mAdapter.updataData(mBeanList);
        if (null == mLoadMoreAdapter) {
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter, this);
            mRvMainFragmentList.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
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
        // TODO: 2017/5/17 什么意思
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
        mPresenter.onLoadMore("这里填筛选类型");
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
}
