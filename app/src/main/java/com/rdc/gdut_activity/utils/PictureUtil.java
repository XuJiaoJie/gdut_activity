package com.rdc.gdut_activity.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PictureUtil {
    private PictureUtil() {
    }
    public static Bitmap getScaleBitmap(String path, int destWidth, int desHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcHeight = options.outHeight;
        float srcWidth = options.outWidth;

        int inSampleSize = 1;
        if (srcHeight > desHeight || srcWidth > destWidth) {
            // 实际宽度大于实际高度
            if (srcWidth > srcHeight) {
                // 取值为 实际高度/目标高度
                inSampleSize = Math.round(srcHeight / desHeight);
            } else {
                // 取值为 实际宽度/目标宽度
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }
}
