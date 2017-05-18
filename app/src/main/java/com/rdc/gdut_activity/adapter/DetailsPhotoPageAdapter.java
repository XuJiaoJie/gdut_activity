package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rdc.gdut_activity.R;

import java.util.List;

/**
 * Created by ThatNight on 2017.5.9.
 */

public class DetailsPhotoPageAdapter extends PagerAdapter {
    private List<String> mPhoto;
    private Context mContext;
    private int mSize;
    private View.OnClickListener mOnClickListener;

    public DetailsPhotoPageAdapter(Context context, List<String> photo, View.OnClickListener onClickListener) {
        mPhoto = photo;
        mContext = context;
        mSize = mPhoto.size();
        mOnClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;   //实现无限滑动
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mSize;
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_details_top, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_details_top);
        Glide.with(mContext).load(mPhoto.get(position)).placeholder(R.drawable.photo_empty_photo).into(imageView);
        imageView.setOnClickListener(mOnClickListener);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
