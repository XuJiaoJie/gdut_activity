package com.rdc.gdut_activity.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailsPhotoPageAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.listener.DetailsPhotoPagerListener;
import com.rdc.gdut_activity.ui.viewinterface.IDetailsView;
import com.rdc.gdut_activity.view.NestedScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class DetailsActivity extends BaseActivity implements IDetailsView {

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

    private List<String> mPhotoList;
    private DetailsPhotoPageAdapter mPageAdapter;
    private DetailsPhotoPagerListener mPagerListener;
    private float mBtnY;
    private float mBtnX;
    private float mScreenHeight;
    private float mViewDistance;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_details_2;
    }

    @Override
    protected void initData() {
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        mBtnDetailsJoin.post(new Runnable() {
            @Override
            public void run() {
                mBtnY = mBtnDetailsJoin.getY();
                mScreenHeight += mBtnY;
            }
        });
        mPhotoList = new ArrayList<String>();
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPhotoList.add(Constant.photo);
        mPagerListener = new DetailsPhotoPagerListener(this, mLlDetailsTopDot, mViewDetailsTopDotRed, mPhotoList.size());

        mPageAdapter = new DetailsPhotoPageAdapter(this, mPhotoList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = DetailsPhotoActivity.newIntent(DetailsActivity.this, mVpDetailsTop.getCurrentItem() % mPhotoList.size(), (ArrayList<String>) mPhotoList);
//                Intent intent = new Intent(DetailsActivity.this, DetailsPhotoActivity.class);
//                intent.putStringArrayListExtra("photo_list", (ArrayList<String>) mPhotoList);
//                intent.putExtra("photo_index", mVpDetailsTop.getCurrentItem() % mPhotoList.size());
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void initView() {
        mVpDetailsTop.setAdapter(mPageAdapter);
        mVpDetailsTop.addOnPageChangeListener(mPagerListener);
        int startPage = Integer.MAX_VALUE / 2;  //无限循环
        mVpDetailsTop.setCurrentItem(startPage);

        mScDetails.setOnNestedScrollListener(new NestedScrollView.IOnNestedScrollListener() {
            @Override
            public void onScroll(boolean isShow) {
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
        });
    }

    @Override
    protected void initListener() {
    }

    @OnClick(R.id.btn_details_join)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                final BmobFile file = new BmobFile(new File(getRealFilePath(uri)));
                file.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            showToast("上传成功");
                            updateUser(file.getFileUrl());
                        } else {
                            showToast("上传失败");
                        }
                    }
                });
            }
        }
    }

    private void updateUser(String fileUrl) {
        User user = BmobUser.getCurrentUser(User.class);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("更新成功");
                } else {
                }
            }
        });
    }

    public String getRealFilePath(Uri content) {
        Cursor cursor = getContentResolver().query(content, null, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(columnIndex);
        return imagePath;
    }

}
