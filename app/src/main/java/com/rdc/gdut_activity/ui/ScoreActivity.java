package com.rdc.gdut_activity.ui;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.QueryScoreAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ScoreBean;
import com.rdc.gdut_activity.contract.ScoreContract;
import com.rdc.gdut_activity.presenter.ScorePresenterImpl;
import com.rdc.gdut_activity.view.NiceSpinner;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by PC on 2017/5/16.
 */

public class ScoreActivity extends BaseActivity implements ScoreContract.View {
    private static final String TAG = "ScoreActivity";
    @InjectView(R.id.topbar_activity_main)
    TopBar mTopbarActivityMain;
    @InjectView(R.id.ns_score_select)
    NiceSpinner mNsScoreSelect;
    @InjectView(R.id.rv_score_list)
    RecyclerView mRvScoreList;
    private List<String> mTimeList;
    private String[] mTimes;
    private String[] mTimesNum;
    private ScoreContract.Presenter mPresenter;
    private QueryScoreAdapter mAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_score;
    }

    @Override
    protected void initData() {
        mPresenter = new ScorePresenterImpl(this);
        mTimes = getResources().getStringArray(R.array.scoreTimes);
        mTimesNum = getResources().getStringArray(R.array.scoreTimesNum);
        mTimeList = new ArrayList<>(Arrays.asList(mTimes));
        mNsScoreSelect.setTextColor(Color.BLACK);
        mNsScoreSelect.attachDataSource(mTimeList);
        mNsScoreSelect.setSelectedIndex(8);
        mNsScoreSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.queryScore(mTimesNum[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initView() {
        mRvScoreList.setHasFixedSize(true);
        mRvScoreList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mPresenter.queryScore(mTimesNum[8]);
        mTopbarActivityMain.setTitle("成绩查询");
        mTopbarActivityMain.setButtonBackground(R.drawable.icon_back,0);
    }

    @Override
    protected void initListener() {
        mTopbarActivityMain.setOnTopbarClickListener(new TopBar.topbarClickListner() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public void querySuccess(List<ScoreBean.RowsBean> list) {
        if (mAdapter == null) {
            mAdapter = new QueryScoreAdapter();
            mAdapter.updataData(list);
            mRvScoreList.setAdapter(mAdapter);
        }else {
            mAdapter.updataData(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void queryError(String s) {
        showToast(s);
    }

}
