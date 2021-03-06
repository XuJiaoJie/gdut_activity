package com.rdc.gdut_activity.presenter;

import com.rdc.gdut_activity.bean.ScoreBean;
import com.rdc.gdut_activity.contract.ScoreContract;
import com.rdc.gdut_activity.model.ScoreModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/5/16.
 */

public class ScorePresenterImpl implements ScoreContract.Presenter {
    private static final String TAG = "ScorePresenterImpl";
    private ScoreContract.View mView;
    private ScoreModel mModel;
    private List<ScoreBean.RowsBean> mList;

    public ScorePresenterImpl(ScoreContract.View view){
        mList = new ArrayList<>();
        mView = view;
        mModel = new ScoreModel(this);
    }

    //view
    @Override
    public void queryScore(String time) {
        mModel.queryScore(time);
    }

    //model
    @Override
    public void querySuccess(byte[] bytes) {
        String s = new String(bytes);
        mList = ScoreBean.objectFromData(s).getRows();
        mView.querySuccess(mList);
    }

    @Override
    public void queryError(String s) {
        mView.queryError(s);
    }
}
