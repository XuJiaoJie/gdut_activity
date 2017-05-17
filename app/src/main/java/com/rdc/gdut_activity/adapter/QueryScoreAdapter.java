package com.rdc.gdut_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.ScoreBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by PC on 2017/5/16.
 */

public class QueryScoreAdapter extends BaseRecyclerViewAdapter<ScoreBean.RowsBean> {
    private static final String TAG = "QueryScoreAdapter";
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, null);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ScoreViewHolder viewHolder = (ScoreViewHolder)holder;
        ScoreBean.RowsBean scoreBean = mDataList.get(position);
        viewHolder.mTvScoreCourse.setText(scoreBean.getKcmc());
        viewHolder.mTvScoreCourseCredit.setText(scoreBean.getCjjd());
        viewHolder.mTvScoreCourseType.setText(scoreBean.getKcdlmc());
        viewHolder.mTvScoreCourseScore.setText(scoreBean.getZcj());
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_score_course)
        TextView mTvScoreCourse;
        @InjectView(R.id.tv_score_course_credit)
        TextView mTvScoreCourseCredit;
        @InjectView(R.id.tv_score_course_type)
        TextView mTvScoreCourseType;
        @InjectView(R.id.tv_score_course_score)
        TextView mTvScoreCourseScore;

        ScoreViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
