package com.rdc.gdut_activity.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private TimeUtil() {
    }

    public static int getCurrentWeek(String startingDateStr) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date currentDate = new Date();
            Date enrolDate = simpleDateFormat.parse(startingDateStr);
            long day = (currentDate.getTime() - enrolDate.getTime()) / (24 * 60 * 60 * 1000) / 7 + 1;
            return (int) day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
