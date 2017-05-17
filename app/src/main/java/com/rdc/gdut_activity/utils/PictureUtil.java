package com.rdc.gdut_activity.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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

    private static int computSampleSize(BitmapFactory.Options options, float reqWidth, float reqHeight) {
        float srcWidth = options.outWidth;//20
        float srcHeight = options.outHeight;//10
        int sampleSize = 1;
        if (srcWidth > reqWidth || srcHeight > reqHeight) {
            int withRatio = Math.round(srcWidth / reqWidth);
            int heightRatio = Math.round(srcHeight / reqHeight);
            sampleSize = Math.min(withRatio, heightRatio);
        }
        return sampleSize;
    }

//    /**
//     * 通过uri地址获取文件路径
//     * @param uri
//     * @return
//     */
//    private String getFilePathFromUri(String uri) {
//        Uri pathUri = Uri.parse(uri);
//        Cursor cursor = context.getContentResolver().query(pathUri, null, null, null, null);
//        if (cursor == null) {
//            return pathUri.getPath();
//        } else {
//            cursor.moveToFirst();
//            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            String str = cursor.getString(index);
//            cursor.close();
//            return str;
//        }
//    }

    /**
     * Can't compress a recycled bitmap
     *
     * @param srcImagePath 原始图片的path路径
     * @param outWidth     期望的输出图片的宽度
     * @param outHeight    期望的输出图片的高度
     * @param maxFileSize  期望的输出图片的最大占用的存储空间
     * @return
     */
    public static String compressImage(String srcImagePath, int outWidth, int outHeight, int maxFileSize) {
//        String srcImagePath = getFilePathFromUri(srcImageUri);
        //进行大小缩放来达到压缩的目的
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcImagePath, options);
        //根据原始图片的宽高比和期望的输出图片的宽高比计算最终输出的图片的宽和高
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        float maxWidth = outWidth;
        float maxHeight = outHeight;
        float srcRatio = srcWidth / srcHeight;
        float outRatio = maxWidth / maxHeight;
        float actualOutWidth = srcWidth;
        float actualOutHeight = srcHeight;

        if (srcWidth > maxWidth || srcHeight > maxHeight) {
            //如果输入比率小于输出比率,则最终输出的宽度以maxHeight为准()
            //比如输入比为10:20 输出比是300:10 如果要保证输出图片的宽高比和原始图片的宽高比相同,则最终输出图片的高为10
            //宽度为10/20 * 10 = 5  最终输出图片的比率为5:10 和原始输入的比率相同

            //同理如果输入比率大于输出比率,则最终输出的高度以maxHeight为准()
            //比如输入比为20:10 输出比是5:100 如果要保证输出图片的宽高比和原始图片的宽高比相同,则最终输出图片的宽为5
            //高度需要根据输入图片的比率计算获得 为5 / 20/10= 2.5  最终输出图片的比率为5:2.5 和原始输入的比率相同
            if (srcRatio < outRatio) {
                actualOutHeight = maxHeight;
                actualOutWidth = actualOutHeight * srcRatio;
            } else if (srcRatio > outRatio) {
                actualOutWidth = maxWidth;
                actualOutHeight = actualOutWidth / srcRatio;
            } else {
                actualOutWidth = maxWidth;
                actualOutHeight = maxHeight;
            }
        }
        options.inSampleSize = computSampleSize(options, actualOutWidth, actualOutHeight);
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = null;
        try {
            scaledBitmap = BitmapFactory.decodeFile(srcImagePath, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (scaledBitmap == null) {
            return null;//压缩失败
        }
        //生成最终输出的bitmap
        Bitmap actualOutBitmap = Bitmap.createScaledBitmap(scaledBitmap, (int) actualOutWidth, (int) actualOutHeight, true);
        if (actualOutBitmap != scaledBitmap)
            scaledBitmap.recycle();


        //进行有损压缩
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options_ = 100;
        actualOutBitmap.compress(Bitmap.CompressFormat.JPEG, options_, baos);//质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)

        int baosLength = baos.toByteArray().length;

        while (baosLength / 1024 > maxFileSize) {//循环判断如果压缩后图片是否大于maxMemmorrySize,大于继续压缩
            baos.reset();//重置baos即让下一次的写入覆盖之前的内容
            options_ = Math.max(0, options_ - 10);//图片质量每次减少10
            actualOutBitmap.compress(Bitmap.CompressFormat.JPEG, options_, baos);//将压缩后的图片保存到baos中
            baosLength = baos.toByteArray().length;
            if (options_ == 0)//如果图片的质量已降到最低则，不再进行压缩
                break;
        }
        actualOutBitmap.recycle();

        //将bitmap保存到指定路径
        FileOutputStream fos = null;
//        String filePath = getOutputFileName(srcImagePath);
        try {
            fos = new FileOutputStream(srcImagePath);
            //包装缓冲流,提高写入速度
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
            bufferedOutputStream.write(baos.toByteArray());
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return srcImagePath;
    }

    private static String getOutputFileName(String srcFilePath) {
        File srcFile = new File(srcFilePath);
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "LGImgCompressor/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + File.separator + srcFile.getName());
        return uriSting;
    }

}
