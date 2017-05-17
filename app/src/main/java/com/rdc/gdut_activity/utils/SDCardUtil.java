package com.rdc.gdut_activity.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 截长图保存截图
 * Created by ThatNight on 2017.5.17.
 */
public class SDCardUtil {
    private static int mShare = 1;
    private static final String mFileDirectory = "/shotScreen/";
    private static final String mFileName = "share";

    public static void saveFile(File file) {
        if (getSdCardStatus()) {
            FileOutputStream os;
            try {
                os = new FileOutputStream(file);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(Bitmap bitmap) {
        File file = getFile(mShare);
        if (getSdCardStatus()) {
            FileOutputStream os;
            try {
                os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                mShare++;
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean getSdCardStatus() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public static File getFile(int num) {
        File file = new File(Environment.getExternalStorageDirectory(), mFileDirectory + mFileName + num + ".png");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static Uri getUri() {
        return Uri.fromFile(getFile(mShare - 1));
    }
}
