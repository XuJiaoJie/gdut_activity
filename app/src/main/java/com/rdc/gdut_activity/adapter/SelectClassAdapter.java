package com.rdc.gdut_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.ClassBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class SelectClassAdapter extends BaseRecyclerViewAdapter<ClassBean> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selectclass_class, null);
        return new SelectClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelectClassViewHolder viewHolder = (SelectClassViewHolder) holder;
        viewHolder.position = position;
        ClassBean classBean = mDataList.get(position);
        viewHolder.mTvItemSelectclassClassname.setText("课程名称:" + classBean.getClassName());
        viewHolder.mTvItemSelectclassClasstime.setText("课程时间:" + classBean.getClassTime());
        viewHolder.mTvItemSelectclassTeacher.setText("课程老师:" + classBean.getClassTeacher());
        viewHolder.mTvItemSelectclassSelected.setText("已选人数:" + classBean.getClassSelectPeople() + "/" + classBean.getClassPeople());
    }

    class SelectClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.tv_item_selectclass_classname)
        TextView mTvItemSelectclassClassname;
        @InjectView(R.id.tv_item_selectclass_classtime)
        TextView mTvItemSelectclassClasstime;
        @InjectView(R.id.tv_item_selectclass_teacher)
        TextView mTvItemSelectclassTeacher;
        @InjectView(R.id.rl_selectclass)
        RelativeLayout mRlSelectclass;
        @InjectView(R.id.tv_item_selectclass_selected)
        TextView mTvItemSelectclassSelected;
        int position;

        public SelectClassViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mRlSelectclass.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRecyclerViewListener != null) {
                mOnRecyclerViewListener.onItemClick(position);
            }
        }
    }

}
