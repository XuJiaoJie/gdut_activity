package com.rdc.gdut_activity.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ScrollView;

/**
 * Created by ThatNight on 2017.5.17.
 */

public class CaptureWindowUtil {

    /**
     * Scrollview 长截图
     *
     * @param scrollView
     * @return
     */
    public static Bitmap getSrollViewBitmap(ScrollView scrollView) {
        int height = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            height += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

}
