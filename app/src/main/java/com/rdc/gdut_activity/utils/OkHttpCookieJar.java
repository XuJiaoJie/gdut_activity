package com.rdc.gdut_activity.utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class OkHttpCookieJar implements CookieJar {
    private static List<Cookie> sCookies;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        sCookies = cookies;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (null != sCookies){
            return sCookies;
        }else {
            return new ArrayList<>();
        }
    }

    public static void resetCookies(){
        sCookies = null;
    }
}
