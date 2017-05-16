package com.rdc.gdut_activity.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.SelectClassAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ClassBean;
import com.rdc.gdut_activity.presenter.SelectClassPresenter;
import com.rdc.gdut_activity.ui.viewinterface.ISelectClassView;
import com.rdc.gdut_activity.view.SelectClassPopWindow;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SelectClassActivity extends BaseActivity implements OnClickRecyclerViewListener, SwipeRefreshLayout.OnRefreshListener, ISelectClassView, OnLoadMoreDataRv, TopBar.topbarClickListner, View.OnClickListener {

    @InjectView(R.id.rv_selectclass)
    RecyclerView mRvSelectclass;
    @InjectView(R.id.srl_selectclass)
    SwipeRefreshLayout mSrlSelectclass;
    @InjectView(R.id.tb_selectclass)
    TopBar mTbSelectclass;
    private List<ClassBean> mClassBean;
    private SelectClassAdapter mClassAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapterWrapper;
    private boolean isRefreshing = true;
    private boolean isLogin = false;
    private boolean isFirstGet = true;
    private SelectClassPresenter mClassPresenter;
    private int mPage = 1;
    private AlertDialog.Builder mSelectDiaglog;
    private AlertDialog.Builder mSearchDiaglog;
    private AlertDialog.Builder mResponseDiaglog;
    private SelectClassPopWindow mClassPopWindow;

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
        //mClassPresenter.getClasses("1");
    }

    @Override
    protected void initView() {
        mTbSelectclass.setButtonBackground(R.drawable.iv_back, R.drawable.iv_more);
        mRvSelectclass.setLayoutManager(new LinearLayoutManager(this));
        mRvSelectclass.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvSelectclass.setItemAnimator(new DefaultItemAnimator());
        mSrlSelectclass.setColorSchemeResources(R.color.colorPrimary);
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
        mTbSelectclass.setOnTopbarClickListener(this);
    }

    /**
     * recyclerview点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(final int position) {
        if (mSelectDiaglog == null) {
            mSelectDiaglog = new AlertDialog.Builder(this);
            mSelectDiaglog.setTitle("提示");
            mSelectDiaglog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mClassPresenter.selectClass(mClassBean.get(position).getClassNum(), mClassBean.get(position).getClassName());
                }
            });
            mSelectDiaglog.setNegativeButton("否", null);
        }
        mSelectDiaglog.setMessage("是否抢该门课程: " + mClassBean.get(position).getClassName());
        mSelectDiaglog.show();
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
        mClassPresenter.getClasses(String.valueOf(mPage + 1));
    }

    /**
     * presenter回调方法
     *
     * @param response
     */
    @Override
    public void selectSuccess(String response) {
        if (mResponseDiaglog == null) {
            mResponseDiaglog = new AlertDialog.Builder(this);
            mResponseDiaglog.setTitle("选课结果");
            mResponseDiaglog.setNegativeButton("确定", null);
        }
        mResponseDiaglog.setMessage(response);
        mResponseDiaglog.show();
    }

    @Override
    public void selectFailed() {

    }

    /**
     * 设置下拉刷新状态
     *
     * @param isRefreshing
     */
    @Override
    public void showProgress(boolean isRefreshing) {
        mSrlSelectclass.setRefreshing(true);
    }

    /**
     * Login
     */
    @Override
    public void loginSuccess() {
        showToast("登录成功,正在获取数据!");
        mClassPresenter.getClasses(String.valueOf(mPage));
    }

    @Override
    public void loginFailed() {
        showToast("登录失败,请检查账号和密码!");
        // TODO: 2017.5.14 跳转到个人中心_我的广工
    }

    /**
     * 获取数据
     */
    @Override
    public void getSuccess(List<ClassBean> classBean) {
        mClassBean = classBean;
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
        mClassPresenter.login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    /**
     * topbar点击事件
     */
    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        if (mClassPopWindow == null) {
            mClassPopWindow = new SelectClassPopWindow(this, this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        mClassPopWindow.showPopupWindow(mTbSelectclass.getRightButton());
    }

    /**
     * 右上角菜单点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_dialog_selectclass_search:
                if (mSearchDiaglog == null) {
                    mSearchDiaglog = new AlertDialog.Builder(this);
                    mSearchDiaglog.setView(R.layout.dialog_selectclass_search);
                    mSearchDiaglog.setTitle("搜索");
                    mSearchDiaglog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                }
                mSearchDiaglog.show();
                break;
            case R.id.ll_dialog_selectclass_myclass:
                mClassPresenter.getOwnClass();
                break;
            default:
                break;
        }
    }
}
