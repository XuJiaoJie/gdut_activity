package com.rdc.gdut_activity.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBCopyUtil {

    public static void copyDataBaseFromAssets(Context context, String DBName) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = context.getApplicationContext().getDatabasePath(DBName);
        if (!file.exists()) {
            File parent = new File(file.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
            try {
                inputStream = context.getAssets().open(DBName);
                fileOutputStream = new FileOutputStream(file.getPath());
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, length);
                }
                fileOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
