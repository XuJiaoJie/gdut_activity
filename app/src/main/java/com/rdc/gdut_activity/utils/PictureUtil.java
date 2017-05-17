package com.rdc.gdut_activity.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureUtil {
    private PictureUtil() {
    }

    public static final String JPG_SUFFIX = ".jpg";
    private static final String TAG = "PictureUtil";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String handleImageOnKitKat(Context context, Uri uri) {
        String imagePath = null;
        if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(context, uri, null);
        }
        return imagePath;
    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

//    public static String handleImageOnKitKat(Uri uri, Context context) {
//        String imgPath = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (DocumentsContract.isDocumentUri(context, uri)) {
//                String docId = DocumentsContract.getDocumentId(uri);
//                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                    Log.d(TAG, "handleImageOnKitKat: " + uri.toString());
//                    String id = docId.split(":")[1];
//                    String selection = MediaStore.Images.Media._ID + "=" + id;
//                    imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, context, selection);
//                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                    Log.e(TAG, "handleImageOnKitKat: " + uri.toString());
//                    Uri contentUri = ContentUris.withAppendedId(
//                            Uri.parse("content://downloads/public_downloads"),
//                            Long.valueOf(docId));
//                    imgPath = getImagePath(contentUri, context, null);
//                } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//                    Log.e(TAG, "handleImageOnKitKat: content: " + uri.toString());
//                    imgPath = getImagePath(uri, context, null);
//                }
//            }
//        } else {
//            imgPath = getImagePath(uri, context, null);
//
//        }
//        Log.e(TAG, "handleImageOnKitKat:imgPath " + imgPath);
//
//        return imgPath;
//    }
//
//    private static String getImagePath(Uri uri, Context context, String selection) {
//        String path = null;
//        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }

    public static void compressBitmap(String sourcePath, String targetPath, float maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(sourcePath, options);

        final float originalWidth = options.outWidth;
        final float originalHeight = options.outHeight;

        float convertedWidth;
        float convertedHeight;

        if (originalWidth > originalHeight) {
            convertedWidth = maxSize;
            convertedHeight = maxSize / originalWidth * originalHeight;
        } else {
            convertedHeight = maxSize;
            convertedWidth = maxSize / originalHeight * originalWidth;
        }


        final float ratio = originalWidth / convertedWidth;

        options.inSampleSize = (int) ratio;
        options.inJustDecodeBounds = false;

        Bitmap convertedBitmap = BitmapFactory.decodeFile(sourcePath, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        convertedBitmap.compress(Bitmap.CompressFormat.JPEG, 35, byteArrayOutputStream);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(targetPath));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
