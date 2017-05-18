package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailImgAdapter;
import com.rdc.gdut_activity.adapter.DetailImgUriAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.contract.VerifyContract;
import com.rdc.gdut_activity.presenter.VerifyPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PC on 2017/5/10.
 */

public class DetailsVerifyActivity extends BaseActivity implements VerifyContract.DetailView {
    private static final String TAG = "DetailsVerifyActivity";
    private static final String KEY_BUNDLE = "BUNDLE";
    private static final String KEY_URI_LIST = "URI_LIST";
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
    @InjectView(R.id.iv_verify_pass)
    ImageView mIvVerifyPass;
    @InjectView(R.id.iv_verify_nopass)
    ImageView mIvVerifyNopass;
    @InjectView(R.id.tv_verify_reason)
    TextView mTvVerifyReason;
    @InjectView(R.id.rl_verify_nopass_reason)
    RelativeLayout mRlVerifyNopassReason;
    @InjectView(R.id.btn_activity_sign_up)
    Button mBtnActivitySignUp;
    @InjectView(R.id.rl_activity_sign_up)
    RelativeLayout mRlActivitySignUp;
    @InjectView(R.id.tv_verify_num)
    TextView mTvVerifyNum;
    @InjectView(R.id.tv_verify_pic)
    TextView mTvVerifyPic;
    @InjectView(R.id.iv_verify_pic_frist)
    ImageView mIvVerifyPicFrist;

    private ActivityInfoBean mBean;
    private DetailImgAdapter mImgAdapter;
    private boolean isVerifyType;
    private String mTitle;
    private VerifyContract.Presenter mPresenter;
    private DetailImgUriAdapter mImgUriAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details_verify;
    }


    public static Intent newIntent(Context context, ActivityInfoBean bean, ArrayList<Uri> uriList, String actTitle, boolean isVerifyType) {
        Intent intent = new Intent(context, DetailsVerifyActivity.class);
        intent.putExtra("DetailsVerifyActivity", (Parcelable) bean);
        intent.putExtra("isVerifyType", isVerifyType);
        intent.putExtra("ActivityTitle", actTitle);
        intent.putExtra("UriList", (Parcelable) uriList);
        return intent;
    }

    @Override
    protected void initData() {
        mBean = getIntent().getParcelableExtra("DetailsVerifyActivity");
        isVerifyType = getIntent().getBooleanExtra("isVerifyType", false);
        mTitle = getIntent().getStringExtra("ActivityTitle");
        mImgAdapter = new DetailImgAdapter();
        mPresenter = new VerifyPresenterImpl(this);
    }

    @Override
    protected void initView() {
        //对传入类型进行判断，显示审核者布局
        initVerifyUI();
        mTvItemActivityTitle.setText(mBean.getActivityName());
        mTvItemUserName.setText(mBean.getPublisherName());
        mTvItemActivityPublishTime.setText(mBean.getPublishTime());
        mTvVerifyTime.setText(mBean.getActivityTime());
        mTvVerifyPlace.setText(mBean.getActivityLocation());
        mTvVerifyType.setText(mBean.getActivityType());
        mTvVerifyHost.setText(mBean.getActivityHost());
        mTvVerifyDetails.setText(mBean.getActivityDetail());
        mTvVerifyNum.setText("炒鸡少");
        if (mBean.getImgUrlList() == null || mBean.getImgUrlList().size() == 0) {
            mTvVerifyPic.setVisibility(View.GONE);
            mIvVerifyPicFrist.setImageResource(R.drawable.verify_item_pic);
        }else if(mBean.getImgUrlList().size() != 0){
            mTvVerifyPic.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(mBean.getImgUrlList().get(0))
                    .placeholder(R.drawable.photo_empty_photo)
                    .into(mIvVerifyPicFrist);
        }
        if (mBean.getPublisherIconUrl() != null) {
            Picasso.with(this)
                    .load(mBean.getPublisherIconUrl())
                    .placeholder(R.drawable.photo_empty_photo)
                    .into(mCivItemUserIcon);
        } else {
            mCivItemUserIcon.setImageResource(R.drawable.ueser_icon);
        }
        if (mTitle != null && mTitle.equals("预览")) {
            mImgUriAdapter = new DetailImgUriAdapter();
            mNgivActivityPic.setAdapter(mImgUriAdapter);
            if (getIntent() != null && getIntent().getBundleExtra(KEY_BUNDLE) != null) {
                Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE);
                if (bundle.getParcelableArrayList(KEY_URI_LIST) != null) {
                    mNgivActivityPic.setImagesData(bundle.getParcelableArrayList(KEY_URI_LIST));
                }
            }
        } else {
            mNgivActivityPic.setAdapter(mImgAdapter);
            mNgivActivityPic.setImagesData(mBean.getImgUrlList());
        }
    }

    @Override
    protected void initListener() {
    }


    @OnClick(R.id.btn_activity_sign_up)
    public void onViewClicked() {
        Intent intent = EnrollStatusActivity.newIntent(this, mBean);
        startActivity(intent);
    }

    @OnClick({R.id.ib_verify_back, R.id.fab_verify_pass, R.id.fab_verify_failure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_verify_back:
                finish();
                break;
            case R.id.fab_verify_pass:
                Log.e(TAG, "onViewClicked: " + mBean.getObjectId());
                mPresenter.verifyPass(mBean.getObjectId());
                break;
            case R.id.fab_verify_failure:
                verifyFailureReason();
                break;
        }
    }

    //对传入类型进行判断，显示审核者布局
    private void initVerifyUI() {
        if (!isVerifyType) {
            mFamVerifyMenu.setVisibility(View.GONE);
            mTvVerifyVerify.setVisibility(View.GONE);
            mCtlActivityTitle.setTitle(mTitle);
            if (mTitle.equals("已审核活动详情")) {
                mTvVerifyVerify.setVisibility(View.VISIBLE);
                if (mBean.getCheckStatus().equals("审核通过")) {
                    mIvVerifyPass.setVisibility(View.VISIBLE);
                } else if (mBean.getCheckStatus().equals("审核不通过")) {
                    mIvVerifyNopass.setVisibility(View.VISIBLE);
                    mRlVerifyNopassReason.setVisibility(View.VISIBLE);
                    mTvVerifyReason.setText(mBean.getCheckReason());
                }
            }
            if (mTitle.equals("已发布的活动")) {
                mTvVerifyVerify.setVisibility(View.VISIBLE);
                if (mBean.getCheckStatus().equals("审核通过")) {
                    mRlActivitySignUp.setVisibility(View.VISIBLE);
                    mIvVerifyPass.setVisibility(View.VISIBLE);
                } else if (mBean.getCheckStatus().equals("审核不通过")) {
                    mRlActivitySignUp.setVisibility(View.GONE);
                    mIvVerifyNopass.setVisibility(View.VISIBLE);
                    mRlVerifyNopassReason.setVisibility(View.VISIBLE);
                    mTvVerifyReason.setText(mBean.getCheckReason());
                }
            }
        }
    }

    //审核失败原因Dialog
    private void verifyFailureReason() {
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_verify_failure_reason, (ViewGroup) findViewById(R.id.ll_verify_failure_reason));
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon_verify_failure)
                .setTitle("审核不通过")
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) view.findViewById(R.id.et_verify_failure_reason);
                        String reason = editText.getText().toString();
                        if (reason.equals("")) {
                            showToast("请输入审核不通过原因");
                            return;
                        }
                        mPresenter.verifyFailure(mBean.getObjectId(), reason);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //审核成功上传回调
    @Override
    public void verifySuccess() {
        showToast("审核操作成功");
        finish();
    }

    //审核失败上传回调
    @Override
    public void verifyError(String s) {
        showToast("审核操作失败：" + s);
        finish();
    }


}
