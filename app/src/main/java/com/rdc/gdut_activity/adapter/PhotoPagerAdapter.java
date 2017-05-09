package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.rdc.gdut_activity.R;

import java.util.List;

/**
 * Created by ThatNight on 2017.5.8.
 */

public class PhotoPagerAdapter extends PagerAdapter {
    private List<String> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PhotoPagerAdapter(Context context, List<String> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.viewpager_details_photo, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_details_photo);
        Glide.with(mContext).load(mList.get(position))
                .into(photoView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
