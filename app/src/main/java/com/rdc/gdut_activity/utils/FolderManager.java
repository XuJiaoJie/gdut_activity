package com.rdc.gdut_activity.utils;

import android.os.Environment;

import java.io.File;

public class FolderManager {
    private FolderManager() {
        throw new UnsupportedOperationException("u can't instantiate me");
    }

    public static final String APP_FOLDER_NAME = "GdutActivity";
    public static final String PHOTO_FOLDER_NAME = "photo";
    public static final String CRASH_LOG_FOLDER_NAME = "crash";


    /**
     * 获取app在sd卡上的主目录
     *
     * @return 成功则返回目录，失败则返回null
     */
    public static File getAppFolder() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File appFolder = new File(Environment.getExternalStorageDirectory(), APP_FOLDER_NAME);
            return createOnNotFound(appFolder);
        }
        return null;
    }

    /**
     * 获取应用存放图片的目录
     *
     * @return 成功则返回目录名，失败返回 bull
     */
    public static File getPhotoFolder() {
        File appFolder = getAppFolder();
        if (appFolder != null) {
            File photoFolder = new File(appFolder, PHOTO_FOLDER_NAME);
            return createOnNotFound(photoFolder);
        }
        return null;
    }

    private static File createOnNotFound(File folder) {
        if (folder == null) {
            return null;
        }
        if (!folder.exists()) {
            folder.mkdir();
        }

        if (folder.exists()) {
            return folder;
        } else {
            return null;
        }
    }

    /**
     * 获取闪退日志目录
     */
    public static File getCrashLogFolder() {
        File appFolder = getAppFolder();
        if (appFolder != null) {
            File crashLogFolder = new File(appFolder, CRASH_LOG_FOLDER_NAME);
            return createOnNotFound(crashLogFolder);
        }
        return null;
    }


}
