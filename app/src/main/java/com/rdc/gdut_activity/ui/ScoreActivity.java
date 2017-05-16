package com.rdc.gdut_activity.ui;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.view.NiceSpinner;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by PC on 2017/5/16.
 */

public class ScoreActivity extends BaseActivity {
    private static final String TAG = "ScoreActivity";
    @InjectView(R.id.topbar_activity_main)
    TopBar mTopbarActivityMain;
    @InjectView(R.id.ns_score_select)
    NiceSpinner mNsScoreSelect;
    private List<String> mTimeList;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_score;
    }

    @Override
    protected void initData() {
        mTimeList = new ArrayList<>(Arrays.asList("Zhang", "Phil", "@", "CSDN"));
        mNsScoreSelect.setTextColor(Color.BLACK);
        mNsScoreSelect.attachDataSource(mTimeList);
        mNsScoreSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + mTimeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

}
