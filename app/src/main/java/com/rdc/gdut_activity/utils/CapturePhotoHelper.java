package com.rdc.gdut_activity.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CapturePhotoHelper {

    private static final String TIME_STAMP_FORMAT = "yyyy_MM_dd_HH_mm_ss";

    public static final int CAPTURE_PHOTO_REQUEST_CODE = 0x1111;

    private Activity mActivity;
    /**
     * 存放图片的目录
     */
    private File mPhotoFolder;
    /**
     * 拍照生成的图片文件
     */
    private File mPhotoFile;

    private Uri mPhotoUri;

    private Fragment mFragment;

    @Deprecated
    public CapturePhotoHelper(Activity activity, File photoFolder) {
//        mActivity = activity;
        mPhotoFolder = photoFolder;
    }

    public CapturePhotoHelper(Fragment fragment, File photoFolder) {
        mPhotoFolder = photoFolder;
        mFragment = fragment;
        mActivity = fragment.getActivity();
    }

    public void capture() {
        if (hasCamera()) {
            createPhotoFile();
            if (mPhotoFile == null) {
                Toast.makeText(mFragment.getActivity(), "无法打开相机", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mPhotoUri = Uri.fromFile(mPhotoFile);

            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
            mFragment.startActivityForResult(captureIntent, CAPTURE_PHOTO_REQUEST_CODE);

        } else {
            Toast.makeText(mActivity, "无法打开相机", Toast.LENGTH_SHORT).show();
        }
    }

    private void createPhotoFile() {
        if (mPhotoFolder != null) {
            if (!mPhotoFolder.exists()) {
                mPhotoFolder.mkdirs();
            }
            @SuppressLint("SimpleDateFormat") String fileName = new SimpleDateFormat(TIME_STAMP_FORMAT).format(new Date());
            mPhotoFile = new File(mPhotoFolder, fileName + PictureUtil.JPG_SUFFIX);
            if (mPhotoFile.exists()) {
                mPhotoFile.delete();
            }

            try {
                mPhotoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                mPhotoFile = null;
            }
        } else {
            mPhotoFile = null;
            Toast.makeText(mActivity, "没有指定路径", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean hasCamera() {
        PackageManager packageManager = mActivity.getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public File getPhotoFile() {
        return mPhotoFile;
    }

    public void setPhotoFile(File photoFile) {
        mPhotoFile = photoFile;
    }

    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    private boolean hasCameraMethod2() {
        PackageManager packageManager = mActivity.getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return intent.resolveActivity(packageManager) != null;
    }


}
