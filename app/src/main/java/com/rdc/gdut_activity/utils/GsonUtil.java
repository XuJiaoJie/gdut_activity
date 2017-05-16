package com.rdc.gdut_activity.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class GsonUtil {
    private static Gson sGson;

    static {
        if (sGson == null) {
            synchronized (GsonUtil.class) {
                if (sGson == null) {
                    sGson = new Gson();
                }
            }
        }
    }

    /**
     * 转成json
     * @param o
     * @return
     */
    public static String gsonToJson(Object o) {
        String json = null;
        if (sGson != null) {
            json = sGson.toJson(o);
        }
        return json;
    }

    /**
     * 转成bean类
     *
     * @param response
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T gsonToBean(String response, Class<T> bean) {
        T t = null;
        if (sGson != null) {
            t = sGson.fromJson(response, bean);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param response
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToList(String response, Class<T> cls) {
        List<T> list = null;
        if (sGson != null) {
            list = sGson.fromJson(response, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String response) {
        List<Map<String, T>> list = null;
        if (sGson != null) {
            list = sGson.fromJson(response, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String response) {
        Map<String, T> map = null;
        if (sGson != null) {
            map = sGson.fromJson(response, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
