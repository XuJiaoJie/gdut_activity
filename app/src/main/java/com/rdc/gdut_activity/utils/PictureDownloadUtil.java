package com.rdc.gdut_activity.utils;

import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PictureDownloadUtil {

    public static Boolean loadImageSaveToLocal(String url, String filename) {
        try {
            String path = "/mnt/sdcard/" + filename;
            Log.e("error", path);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            byte[] data = readImage(url);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();
            Log.e("error", path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static byte[] readImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5 * 1000);
        InputStream inputStream = httpURLConnection.getInputStream();
        return readStream(inputStream);
    }

    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

}
