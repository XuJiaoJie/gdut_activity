package com.rdc.gdut_activity.listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rdc.gdut_activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThatNight on 2017.5.9.
 */

public class DetailsPhotoPagerListener implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private LinearLayout mLinearLayout;         //底部圆点布局
    private int mSize;                          //圆点数量
    private List<ImageView> mDotView;           //圆点容器

    public DetailsPhotoPagerListener(Context context, LinearLayout linearLayout, int size) {
        mContext = context;
        mLinearLayout = linearLayout;
        mSize = size;
        initData();
    }

    private void initData() {
        mDotView = new ArrayList<>();
        for (int i = 0; i < mSize; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 10;     //设置圆点相距距离
            params.rightMargin = 10;
            if (i == 0) {               //初始化为红点
                imageView.setBackgroundResource(R.drawable.bg_details_photo_viewindicator_red);
            } else {
                imageView.setBackgroundResource(R.drawable.bg_details_photo_viewindicator_grey);
            }
            mLinearLayout.addView(imageView,params);
            mDotView.add(imageView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mSize; i++) {
            if ((position % mSize) == i) {      //如果是当前的位置就设置为红点
                mDotView.get(i).setBackgroundResource(R.drawable.bg_details_photo_viewindicator_red);
            } else {
                mDotView.get(i).setBackgroundResource(R.drawable.bg_details_photo_viewindicator_grey);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
