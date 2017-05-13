package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.fragment.ReleasedFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReleasedActRvAdapter extends RecyclerView.Adapter<ReleasedActRvAdapter.ReleasedItemViewHolder> {
    private List<ActivityInfoBean> mReleasedActList;
    private Context mContext;

    public ReleasedActRvAdapter(List<ActivityInfoBean> releasedActList, Context context) {
        mReleasedActList = releasedActList;
        mContext = context;
    }

    @Override
    public ReleasedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_list_fragment, parent, false);
        return new ReleasedItemViewHolder(v, mContext);
    }

    @Override
    public void onBindViewHolder(ReleasedItemViewHolder holder, int position) {
        holder.bindView(mReleasedActList.get(position));
    }

    @Override
    public int getItemCount() {
        return mReleasedActList.size();
    }

    class ReleasedItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.iv_verify_item_pic)
        ImageView mIvVerifyItemPic;
        @InjectView(R.id.tv_verify_title)
        TextView mTvVerifyTitle;
        @InjectView(R.id.tv_verify_time)
        TextView mTvVerifyTime;
        @InjectView(R.id.tv_verify_place)
        TextView mTvVerifyPlace;
        @InjectView(R.id.tv_publish_default_text)
        TextView mTvPublishDefaultText;
        @InjectView(R.id.tv_verify_publish)
        TextView mTvVerifyPublish;


        private ActivityInfoBean mActivityInfoBean;

        private Context mContext;

        ReleasedItemViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
            mContext = context;
        }

        public void bindView(ActivityInfoBean bean) {
            mActivityInfoBean = bean;
            mIvVerifyItemPic.setImageResource(R.drawable.ic_pick_photo);
            mTvVerifyTitle.setText(bean.getActivityName());
            mTvVerifyTime.setText(bean.getActivityTime());
            mTvVerifyPlace.setText(bean.getActivityLocation());
        }

        @Override
        public void onClick(View v) {
            Fragment fragment = ReleasedFragment.newInstance();

            ((BaseActivity) mContext).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
