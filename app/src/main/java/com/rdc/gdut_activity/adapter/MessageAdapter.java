package com.rdc.gdut_activity.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class MessageAdapter extends BaseRecyclerViewAdapter<MessageBean> {
    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_message, null);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageViewHolder viewHolder = (MessageViewHolder) holder;
        viewHolder.position = position;
        MessageBean msg = mDataList.get(position);
        viewHolder.mTvMessageName.setText(msg.getName());
        viewHolder.mTvMessageMsg.setText(msg.getMessage());
        viewHolder.mTvMessageTime.setText(msg.getTime());
        Glide.with(mContext).load(msg.getIcon()).into(viewHolder.mCivMessageIcon);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.civ_message_icon)
        CircleImageView mCivMessageIcon;
        @InjectView(R.id.tv_message_name)
        TextView mTvMessageName;
        @InjectView(R.id.tv_message_time)
        TextView mTvMessageTime;
        @InjectView(R.id.tv_message_msg)
        TextView mTvMessageMsg;
        @InjectView(R.id.ll_item_message)
        LinearLayout mLlItemMessage;
        protected int position;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mLlItemMessage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRecyclerViewListener != null) {
                mOnRecyclerViewListener.onItemClick(position);
            }
        }
    }
}
