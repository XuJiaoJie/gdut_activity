package com.rdc.gdut_activity.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.MessageBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class MessageMainAdapter extends BaseRecyclerViewAdapter<MessageBean> {

    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_message_main, null);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageViewHolder viewHolder = (MessageViewHolder) holder;
        viewHolder.position = position;
        MessageBean msg = mDataList.get(position);
        viewHolder.mTvActivityMessageName.setText(msg.getName());
        viewHolder.mTvActivityMessageMsg.setText(msg.getMessage());
        viewHolder.mTvActivityMessageTime.setText(msg.getTime());
        Glide.with(mContext).load(msg.getIcon()).into(viewHolder.mCivActivityMessageIcon);

    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.civ_activity_message_icon)
        CircleImageView mCivActivityMessageIcon;
        @InjectView(R.id.tv_activity_message_name)
        TextView mTvActivityMessageName;
        @InjectView(R.id.tv_activity_message_time)
        TextView mTvActivityMessageTime;
        @InjectView(R.id.tv_activity_message_msg)
        TextView mTvActivityMessageMsg;
        protected int position;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }
}
