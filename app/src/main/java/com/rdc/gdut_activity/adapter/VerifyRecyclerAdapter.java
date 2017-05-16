package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;


public class VerifyRecyclerAdapter extends BaseRecyclerViewAdapter<ActivityInfoBean> {
    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_activity, null);
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
        viewHolder.mTvItemActivityPublishTime.setText(bean.getCreatedAt());
        viewHolder.mTvItemUserName.setText(bean.getPublisherName());
        if (bean.getPublisherIconUrl() != null){
            Picasso.with(mContext).load(bean.getPublisherIconUrl())
                    .placeholder(R.drawable.photo_empty_photo)
                    .into(viewHolder.mCivItemUserIcon);
        }else {
            viewHolder.mCivItemUserIcon.setImageResource(R.drawable.ueser_icon);
        }
        if (bean.getImgUrlList() != null){
            Picasso.with(mContext).load(bean.getImgUrlList().get(0))
                    .placeholder(R.drawable.photo_empty_photo)
                    .into(viewHolder.mIvItemActivityPic);
        }else {
            viewHolder.mIvItemActivityPic.setImageResource(R.drawable.verify_item_pic);
        }
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
        @InjectView(R.id.civ_item_user_icon)
        CircleImageView mCivItemUserIcon;
        @InjectView(R.id.iv_item_activity_pic)
        ImageView mIvItemActivityPic;
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
