package com.rdc.gdut_activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by ThatNight on 2017.5.9.
 */

public class NestedScrollView extends ScrollView {
    private IOnNestedScrollListener mIOnNestedScrollListener;

    public NestedScrollView(Context context) {
        super(context);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @param l    变化后的x位置
     * @param t    变化后的y位置
     * @param oldl 原先的x位置
     * @param oldt 原先的y位置
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mIOnNestedScrollListener != null) {
            if (t > oldt && t - oldt > 10) {            //向下滑动
                mIOnNestedScrollListener.onScroll(true);
            } else if (t < oldt && oldt - t > 10) {     //向上滑动
                mIOnNestedScrollListener.onScroll(false);
            }
        }
    }

    public void setOnNestedScrollListener(IOnNestedScrollListener onNestedScrollListener) {
        mIOnNestedScrollListener = onNestedScrollListener;
    }

    public interface IOnNestedScrollListener {
        void onScroll(boolean isShow);
    }
}
