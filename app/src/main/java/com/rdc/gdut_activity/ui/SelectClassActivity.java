package com.rdc.gdut_activity.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.SelectClassAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ClassBean;
import com.rdc.gdut_activity.presenter.SelectClassPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ISelectClassView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class SelectClassActivity extends BaseActivity implements OnClickRecyclerViewListener, SwipeRefreshLayout.OnRefreshListener, ISelectClassView, OnLoadMoreDataRv {

    @InjectView(R.id.rv_selectclass)
    RecyclerView mRvSelectclass;
    @InjectView(R.id.srl_selectclass)
    SwipeRefreshLayout mSrlSelectclass;
    @InjectView(R.id.btn_selectclass_query)
    Button mBtnSelectclassQuery;
    @InjectView(R.id.btn_selectclass_selected)
    Button mBtnSelectclassSelected;
    @InjectView(R.id.btn_selectclass_select)
    Button mBtnSelectclassGet;

    private List<ClassBean> mClassBean;
    private SelectClassAdapter mClassAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapterWrapper;
    private boolean isRefreshing = true;
    private boolean isLogin = false;
    private boolean isFirstGet = true;
    private SelectClassPresenter mClassPresenter;


    @Override
    protected int setLayoutResID() {
        return R.layout.activity_select_class;
    }

    @Override
    protected void initData() {
        mClassBean = new ArrayList<>();
        mClassPresenter = new SelectClassPresenter(this);
        mClassAdapter = new SelectClassAdapter();
        mClassAdapter.setOnRecyclerViewListener(this);
        mClassPresenter.login();
    }

    @Override
    protected void initView() {
        mRvSelectclass.setLayoutManager(new LinearLayoutManager(this));
        mRvSelectclass.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvSelectclass.setItemAnimator(new DefaultItemAnimator());
        mSrlSelectclass.setColorSchemeResources(R.color.color_primary);
        mSrlSelectclass.post(new Runnable() {
            @Override
            public void run() {
                mSrlSelectclass.setRefreshing(isRefreshing);
            }
        });
        mSrlSelectclass.setOnRefreshListener(this);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.btn_selectclass_query, R.id.btn_selectclass_selected, R.id.btn_selectclass_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selectclass_query:

                break;
            case R.id.btn_selectclass_selected:

                break;
            case R.id.btn_selectclass_select:

                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    /**
     * 下拉刷新刷新中
     */
    @Override
    public void onRefresh() {
        mClassPresenter.getClasses("1");
    }


    /**
     * 上拉加载
     */
    @Override
    public void loadMoreData() {
        mClassPresenter.getClasses("2");
    }

    /**
     * presenter回调方法
     */
    @Override
    public void selectSuccess() {

    }

    @Override
    public void selectFailed() {

    }

    @Override
    public void refreshing(boolean isRefreshing) {

    }

    /**
     * Login
     */
    @Override
    public void loginSuccess() {
        showToast("登录成功,正在获取数据!");
        mClassPresenter.getClasses("1");
    }

    @Override
    public void loginFailed() {

    }

    /**
     * 获取数据
     */
    @Override
    public void getSuccess(List<ClassBean> classBean) {
        mClassBean=classBean;
        if (isFirstGet) {
            isFirstGet = false;
            mClassAdapter.updataData(mClassBean);
            if (mLoadMoreAdapterWrapper == null) {
                mLoadMoreAdapterWrapper = new LoadMoreAdapterWrapper(mClassAdapter, this);
                mRvSelectclass.setAdapter(mLoadMoreAdapterWrapper);
            } else {
                mLoadMoreAdapterWrapper.notifyDataSetChanged();
            }
        } else {
            if (classBean.size() == 0 || classBean.size() < 5) {
                mLoadMoreAdapterWrapper.setHasMoreData(false);
                mLoadMoreAdapterWrapper.notifyDataSetChanged();
            } else {
                mClassAdapter.updataData(mClassBean);
            }
        }
        mSrlSelectclass.setRefreshing(false);

    }

    @Override
    public void getFailed() {

    }

}
