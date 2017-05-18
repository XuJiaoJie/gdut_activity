package com.rdc.gdut_activity.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.rdc.gdut_activity.R;

import java.util.Random;


public class UIUtil {
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }

    public static int getRandomColor(Activity activity) {
        int[] customColorAry = activity.getResources().getIntArray(R.array.customColorAry);
        return customColorAry[new Random().nextInt(customColorAry.length)];
    }

    public static int getScreenWidthDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return px2dp(context, displayMetrics.widthPixels);
    }

    public static int getScreenHeightDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return px2dp(context, displayMetrics.heightPixels);
    }

    public static int px2dp(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


}
