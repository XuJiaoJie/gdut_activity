package com.rdc.gdut_activity.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailsPhotoPageAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.listener.DetailsPhotoPagerListener;
import com.rdc.gdut_activity.ui.viewinterface.IDetailsView;
import com.rdc.gdut_activity.utils.CaptureWindowUtil;
import com.rdc.gdut_activity.utils.SDCardUtil;
import com.rdc.gdut_activity.view.NestedScrollView;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity implements IDetailsView, View.OnClickListener, NestedScrollView.IOnNestedScrollListener, TopBar.topbarClickListner {

    @InjectView(R.id.vp_details_top)
    ViewPager mVpDetailsTop;
    @InjectView(R.id.ll_details_top_dot)
    LinearLayout mLlDetailsTopDot;
    @InjectView(R.id.tv_details_title)
    TextView mTvDetailsTitle;
    @InjectView(R.id.tv_details_time)
    TextView mTvDetailsTime;
    @InjectView(R.id.tv_details_position)
    TextView mTvDetailsPosition;
    @InjectView(R.id.tv_details_to)
    TextView mTvDetailsTo;
    @InjectView(R.id.tv_details_details)
    TextView mTvDetailsDetails;
    @InjectView(R.id.sc_details)
    NestedScrollView mScDetails;
    @InjectView(R.id.btn_details_join)
    Button mBtnDetailsJoin;
    @InjectView(R.id.view_details_top_dot_red)
    View mViewDetailsTopDotRed;
    @InjectView(R.id.tv_details_publish_time)
    TextView mTvDetailsPublishTime;
    @InjectView(R.id.tb_details_main)
    TopBar mTbDetailsMain;
    @InjectView(R.id.fl_details)
    FrameLayout mFlDetails;
    @InjectView(R.id.tv_details_type)
    TextView mTvDetailsType;

    private List<String> mPhotoList;
    private DetailsPhotoPageAdapter mPageAdapter;
    private DetailsPhotoPagerListener mPagerListener;
    private float mBtnY;
    private float mBtnX;
    private float mScreenHeight;
    private float mViewDistance;
    private ActivityInfoBean mInfoBean;
    private boolean isCanSignUp = false;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected void initData() {
        setInfo();
        setSignUpButton();
        if (mPhotoList != null) {
            mFlDetails.setVisibility(View.VISIBLE);
            if (mPhotoList.size() > 1) {
                mPagerListener = new DetailsPhotoPagerListener(this, mLlDetailsTopDot, mViewDetailsTopDotRed, mPhotoList.size());
                mVpDetailsTop.addOnPageChangeListener(mPagerListener);
            }
            mPageAdapter = new DetailsPhotoPageAdapter(this, mPhotoList, this);
//            int startPage = Integer.MAX_VALUE / 2;  //无限循环
//            mVpDetailsTop.setCurrentItem(startPage);
        } else {
            mFlDetails.setVisibility(View.GONE);
        }
    }

    private void setInfo() {
        mInfoBean = getIntent().getParcelableExtra("details_info");
        mPhotoList = mInfoBean.getImgUrlList();
        mTvDetailsTitle.setText(mInfoBean.getActivityName());
        mTvDetailsTime.setText(mInfoBean.getActivityTime());
        mTvDetailsDetails.setText(mInfoBean.getActivityDetail());
        mTvDetailsPublishTime.setText(mInfoBean.getCreatedAt());
        mTvDetailsTo.setText(mInfoBean.getActivityHost());
        mTvDetailsPosition.setText(mInfoBean.getActivityLocation());
        mTvDetailsType.setText(mInfoBean.getActivityType());
        if (mInfoBean.getFormDataMap() != null && mInfoBean.getFormDataMap().size() > 0) {
            isCanSignUp = true;
        } else {
            isCanSignUp = false;
        }
    }

    @Override
    protected void initView() {
        mTbDetailsMain.setButtonBackground(R.drawable.iv_back, R.drawable.iv_share);
        mVpDetailsTop.setAdapter(mPageAdapter);
        mScDetails.setOnNestedScrollListener(this);
    }

    @Override
    protected void initListener() {
        mTbDetailsMain.setOnTopbarClickListener(this);
    }

    /**
     * 返回StartActivity的intent
     *
     * @param context
     * @param bean
     * @return
     */
    public static Intent newIntent(Context context, ActivityInfoBean bean) {
        Intent intent = new Intent(context, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("details_info", bean);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * 设置报名按钮
     */
    private void setSignUpButton() {
        if (isCanSignUp) {
            mBtnDetailsJoin.setVisibility(View.VISIBLE);
            mScreenHeight = getResources().getDisplayMetrics().heightPixels;
            mBtnDetailsJoin.post(new Runnable() {
                @Override
                public void run() {
                    mBtnY = mBtnDetailsJoin.getY();
                    mScreenHeight += mBtnY;
                }
            });
        } else {
            mBtnDetailsJoin.setVisibility(View.GONE);
        }
    }

    /**
     * 报名按钮
     */
    @OnClick(R.id.btn_details_join)
    public void onViewClicked() {
        Intent intent = new Intent(this, SignUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("signup_info", mInfoBean);
        intent.putExtras(bundle);
        startActivity(intent);
        //startActivityForResult(intent, Constant.RETURN_SUCCESS);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == Constant.RETURN_SUCCESS) {
//                isCanSignUp = false;
//                mBtnDetailsJoin.setVisibility(View.GONE);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        Intent intent1 = DetailsPhotoActivity.newIntent(DetailsActivity.this, mVpDetailsTop.getCurrentItem(), (ArrayList<String>) mPhotoList);
        startActivity(intent1);
    }

    @Override
    public void onScroll(boolean isShow) {
        if (isCanSignUp) {
            if (!isShow) {
                Animator animator = ObjectAnimator.ofFloat(mBtnDetailsJoin, View.Y, mBtnY);
                animator.setDuration(300);
                animator.start();
                mBtnDetailsJoin.setEnabled(true);
            } else {
                Animator animator = ObjectAnimator.ofFloat(mBtnDetailsJoin, View.Y, mScreenHeight);
                animator.setDuration(300);
                animator.start();
                mBtnDetailsJoin.setEnabled(false);
            }
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    /**
     * 分享分享
     */
    @Override
    public void rightClick() {
        Bitmap bitmap = CaptureWindowUtil.getSrollViewBitmap(mScDetails);
        SDCardUtil.saveFile(bitmap);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, SDCardUtil.getUri());
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享活动"));
    }
}
