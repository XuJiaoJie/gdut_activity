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

public class ReleasedActRvAdapter extends RecyclerView.Adapter<ReleasedItemViewHolder> {
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
}

class ReleasedItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView mIv;
    private TextView mTvActName;
    private TextView mTvTime;
    private TextView mTvLocation;
    private ActivityInfoBean mActivityInfoBean;

    private Context mContext;

    public ReleasedItemViewHolder(View itemView, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        mIv = (ImageView) itemView.findViewById(R.id.iv_act_icon);
        mTvActName = (TextView) itemView.findViewById(R.id.tv_act_name);
        mTvTime = (TextView) itemView.findViewById(R.id.tv_act_time);
        mTvLocation = (TextView) itemView.findViewById(R.id.tv_act_location);
        mContext = context;
    }

    public void bindView(ActivityInfoBean bean) {
        mActivityInfoBean = bean;
        mIv.setImageResource(R.drawable.ic_pick_photo);
        mTvActName.setText(bean.getActivityName());
        mTvTime.setText(bean.getActivityTime());
        mTvLocation.setText(bean.getActivityLocation());

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
