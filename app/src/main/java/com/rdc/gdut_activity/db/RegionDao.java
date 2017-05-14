package com.rdc.gdut_activity.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rdc.gdut_activity.bean.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionDao {

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public RegionDao(Context context) {
        this.mContext = context;
        mSQLiteDatabase = RegionDataBaseHelper.getInstance(context).getWritableDatabase();
    }

    public List<Region> loadProvinceList() {
        List<Region> provinceList = new ArrayList<>();
        String sql = "SELECT ID,NAME FROM PROVINCE";
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(0);
            String name = cursor.getString(1);
            Region region = new Region(id, name);
            provinceList.add(region);
        }
        return provinceList;
    }

    public List<Region> loadCityList(Long provinceId) {
        List<Region> cityList = new ArrayList<>();
        String sql = "SELECT ID,NAME FROM CITY WHERE PID = ?";
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, new String[]{String.valueOf(provinceId)});
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(0);
            String name = cursor.getString(1);
            Region region = new Region(id, name);
            cityList.add(region);
        }
        return cityList;
    }

    public List<Region> loadCountryList(Long cityId) {
        List<Region> countryList = new ArrayList<>();
        String sql = "SELECT ID,NAME FROM AREA WHERE PID = ?";
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, new String[]{String.valueOf(cityId)});
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(0);
            String name = cursor.getString(1);
            Region region = new Region(id, name);
            countryList.add(region);
        }
        return countryList;
    }
}
