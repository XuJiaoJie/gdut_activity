package com.rdc.gdut_activity.ui;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.PhotoPagerAdapter;
import com.rdc.gdut_activity.base.BaseActivity;

import java.util.List;

import butterknife.InjectView;

public class DetailsPhotoActivity extends BaseActivity {

    @InjectView(R.id.vp_details_photo)
    ViewPager mVpDetailsPhoto;
    @InjectView(R.id.tv_details_photo_count)
    TextView mTvDetailsPhotoCount;
    private PhotoPagerAdapter mPagerAdapter;
    private List<String> mPhotoList;
    private int mIndex = 0;
    private int mSize = 0;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_details_photo;
    }

    @Override
    protected void initData() {
        mIndex = getIntent().getIntExtra("photo_index", 0);
        mPhotoList = getIntent().getStringArrayListExtra("photo_list");
        mSize = mPhotoList.size();
        mPagerAdapter = new PhotoPagerAdapter(this, mPhotoList);
        mVpDetailsPhoto.setAdapter(mPagerAdapter);

    }

    @Override
    protected void initView() {
        mTvDetailsPhotoCount.setText(mIndex + 1 + "/" + mSize);
        mVpDetailsPhoto.setCurrentItem(mIndex);
        mVpDetailsPhoto.setOffscreenPageLimit(2);
    }

    @Override
    protected void initListener() {
        mVpDetailsPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = position;
                mTvDetailsPhotoCount.setText(position + 1 + "/" + mSize);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
