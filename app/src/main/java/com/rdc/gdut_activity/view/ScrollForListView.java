package com.rdc.gdut_activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class ScrollForListView extends ListView {
    public ScrollForListView(Context context) {
        super(context);
    }

    public ScrollForListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollForListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
