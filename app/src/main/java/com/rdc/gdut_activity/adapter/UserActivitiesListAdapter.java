package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivitiesListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ActivityInfoBean> mActivityInfoList = new ArrayList<>();

    public UserActivitiesListAdapter(Context context, List<ActivityInfoBean> activityInfoBeen) {
        this.mContext = context;
        this.mActivityInfoList = activityInfoBeen;
    }

    @Override
    public int getCount() {
        return mActivityInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mActivityInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_activity, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Glide.with(mContext).load(mActivityInfoList.get(position).getPublisherIconUrl()).into(viewHolder.civItemUserIcon);
        Glide.with(mContext).load(mActivityInfoList.get(position).getPublisher().getIcon()).into(viewHolder.civItemUserIcon);
        viewHolder.tvItemUserName.setText(mActivityInfoList.get(position).getPublisherName());
        viewHolder.tvItemActivityPublishTime.setText(mActivityInfoList.get(position).getPublishTime());
        Glide.with(mContext).load(mActivityInfoList.get(position).getImgUrlList().get(0)).into(viewHolder.ivItemActivityPic);
        viewHolder.tvItemActivityTitle.setText(mActivityInfoList.get(position).getActivityName());
        viewHolder.tvVerifyTime.setText(mActivityInfoList.get(position).getActivityTime());
        viewHolder.tvVerifyPlace.setText(mActivityInfoList.get(position).getActivityLocation());
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.civ_item_user_icon)
        CircleImageView civItemUserIcon;
        @InjectView(R.id.tv_item_user_name)
        TextView tvItemUserName;
        @InjectView(R.id.tv_item_activity_default_text)
        TextView tvItemActivityDefaultText;
        @InjectView(R.id.tv_item_activity_publish_time)
        TextView tvItemActivityPublishTime;
        @InjectView(R.id.iv_item_activity_pic)
        ImageView ivItemActivityPic;
        @InjectView(R.id.tv_item_activity_title)
        TextView tvItemActivityTitle;
        @InjectView(R.id.iv_item_activity_default_time)
        ImageView ivItemActivityDefaultTime;
        @InjectView(R.id.tv_verify_time)
        TextView tvVerifyTime;
        @InjectView(R.id.iv_item_activity_default_place)
        ImageView ivItemActivityDefaultPlace;
        @InjectView(R.id.tv_verify_place)
        TextView tvVerifyPlace;
        @InjectView(R.id.ll_item_list_activity)
        LinearLayout llItemListActivity;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
