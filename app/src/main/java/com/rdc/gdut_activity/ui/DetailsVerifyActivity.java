package com.rdc.gdut_activity.ui;

import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailImgAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import butterknife.InjectView;

/**
 * Created by PC on 2017/5/10.
 */

public class DetailsVerifyActivity extends BaseActivity {
    @InjectView(R.id.tv_item_activity_title)
    TextView mTvItemActivityTitle;
    @InjectView(R.id.tv_item_user_name)
    TextView mTvItemUserName;
    @InjectView(R.id.tv_item_activity_publish_time)
    TextView mTvItemActivityPublishTime;
    @InjectView(R.id.tv_verify_time)
    TextView mTvVerifyTime;
    @InjectView(R.id.tv_verify_place)
    TextView mTvVerifyPlace;
    @InjectView(R.id.tv_verify_type)
    TextView mTvVerifyType;
    @InjectView(R.id.tv_verify_host)
    TextView mTvVerifyHost;
    @InjectView(R.id.tv_verify_details)
    TextView mTvVerifyDetails;
    @InjectView(R.id.ngiv_activity_pic)
    NineGridImageView mNgivActivityPic;

    private ActivityInfoBean mBean;
    private DetailImgAdapter mImgAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details_verify;
    }

    @Override
    protected void initData() {
        mBean = getIntent().getParcelableExtra("DetailsVerifyActivity");
        mImgAdapter = new DetailImgAdapter();
    }

    @Override
    protected void initView() {
        mTvItemActivityTitle.setText(mBean.getActivityName());
        mTvItemUserName.setText(mBean.getPublisherName());
        mTvItemActivityPublishTime.setText(mBean.getPublishTime());
        mTvVerifyTime.setText(mBean.getActivityTime());
        mTvVerifyPlace.setText(mBean.getActivityLocation());
        mTvVerifyType.setText(mBean.getActivityType());
        mTvVerifyHost.setText(mBean.getActivityHost());
        mTvVerifyDetails.setText(mBean.getActivityDetail());

        mNgivActivityPic.setAdapter(mImgAdapter);
        mNgivActivityPic.setImagesData(mBean.getImgUrlList());
    }

    @Override
    protected void initListener() {
    }

}
