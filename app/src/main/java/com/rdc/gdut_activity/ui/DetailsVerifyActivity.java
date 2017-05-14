package com.rdc.gdut_activity.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailImgAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PC on 2017/5/10.
 */

public class DetailsVerifyActivity extends BaseActivity {
    private static final String TAG = "DetailsVerifyActivity";
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
    @InjectView(R.id.civ_item_user_icon)
    CircleImageView mCivItemUserIcon;
    @InjectView(R.id.ib_verify_back)
    ImageButton mIbVerifyBack;
    @InjectView(R.id.fab_verify_pass)
    FloatingActionButton mFabVerifyPass;
    @InjectView(R.id.fab_verify_failure)
    FloatingActionButton mFabVerifyFailure;
    @InjectView(R.id.fam_verify_menu)
    FloatingActionsMenu mFamVerifyMenu;
    @InjectView(R.id.tv_verify_verify)
    TextView mTvVerifyVerify;
    @InjectView(R.id.ctl_activity_title)
    CollapsingToolbarLayout mCtlActivityTitle;

    private ActivityInfoBean mBean;
    private DetailImgAdapter mImgAdapter;
    private boolean isVerifyType;
    private String mTitle;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details_verify;
    }

    @Override
    protected void initData() {
        mBean = getIntent().getParcelableExtra("DetailsVerifyActivity");
        isVerifyType = getIntent().getBooleanExtra("isVerifyType", false);
        mTitle = getIntent().getStringExtra("ActivityTitle");
        mImgAdapter = new DetailImgAdapter();
    }

    @Override
    protected void initView() {
        //对传入类型进行判断，显示审核者布局
        if (!isVerifyType) {
            mFamVerifyMenu.setVisibility(View.GONE);
            mTvVerifyVerify.setVisibility(View.GONE);
            mCtlActivityTitle.setTitle(mTitle);
        }
        mTvItemActivityTitle.setText(mBean.getActivityName());
        mTvItemUserName.setText(mBean.getPublisherName());
        mTvItemActivityPublishTime.setText(mBean.getPublishTime());
        mTvVerifyTime.setText(mBean.getActivityTime());
        mTvVerifyPlace.setText(mBean.getActivityLocation());
        mTvVerifyType.setText(mBean.getActivityType());
        mTvVerifyHost.setText(mBean.getActivityHost());
        mTvVerifyDetails.setText(mBean.getActivityDetail());
        if (mBean.getPublisherIconUrl() != null) {
            Picasso.with(this)
                    .load(mBean.getPublisherIconUrl())
                    .placeholder(R.drawable.photo_empty_photo)
                    .into(mCivItemUserIcon);
        } else {
            mCivItemUserIcon.setImageResource(R.drawable.ueser_icon);
        }
        mNgivActivityPic.setAdapter(mImgAdapter);
        mNgivActivityPic.setImagesData(mBean.getImgUrlList());
    }

    @Override
    protected void initListener() {
    }


    @OnClick({R.id.ib_verify_back, R.id.fab_verify_pass, R.id.fab_verify_failure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_verify_back:
                finish();
                break;
            case R.id.fab_verify_pass:
                Log.e(TAG, "onViewClicked: " + mBean.getObjectId());
                break;
            case R.id.fab_verify_failure:

                break;
        }
    }
}
