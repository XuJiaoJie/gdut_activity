package com.rdc.gdut_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class VerifyRecyclerAdapter extends BaseRecyclerViewAdapter<ActivityInfoBean> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activity, null);
        return new VerifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VerifyViewHolder viewHolder = (VerifyViewHolder) holder;
        viewHolder.position = position;
        ActivityInfoBean bean = mDataList.get(position);
        viewHolder.mTvItemActivityTitle.setText(bean.getActivityName());
        viewHolder.mTvVerifyTime.setText(bean.getActivityTime());
        viewHolder.mTvVerifyPlace.setText(bean.getActivityLocation());
        viewHolder.mTvItemActivityPublishTime.setText(bean.getPublishTime());
        viewHolder.mTvItemUserName.setText(bean.getPublisherName());
    }


    class VerifyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @InjectView(R.id.tv_item_user_name)
        TextView mTvItemUserName;
        @InjectView(R.id.tv_item_activity_publish_time)
        TextView mTvItemActivityPublishTime;
        @InjectView(R.id.tv_item_activity_title)
        TextView mTvItemActivityTitle;
        @InjectView(R.id.tv_verify_time)
        TextView mTvVerifyTime;
        @InjectView(R.id.tv_verify_place)
        TextView mTvVerifyPlace;
        @InjectView(R.id.ll_item_list_activity)
        LinearLayout mLlItemListActivity;
        int position;

        VerifyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mLlItemListActivity.setOnClickListener(this);
            mLlItemListActivity.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mOnRecyclerViewListener) {
                mOnRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != mOnRecyclerViewListener) {
                mOnRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }

}
