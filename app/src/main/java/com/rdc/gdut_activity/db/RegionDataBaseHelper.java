package com.rdc.gdut_activity.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegionDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "region.db";
    private static final int DB_VERSION = 1;

    private static RegionDataBaseHelper sInstance;

    public RegionDataBaseHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public RegionDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static RegionDataBaseHelper getInstance(Context context) {
        if(sInstance == null) {
            synchronized (RegionDataBaseHelper.class) {
                if(sInstance == null) {
                    sInstance = new RegionDataBaseHelper(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public static void closeDataBase() {
        if (sInstance != null) {
            sInstance.close();
        }
        sInstance = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
