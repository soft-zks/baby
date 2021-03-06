package com.hubu.baby.util;

import com.google.gson.Gson;

/**
 * <p>JsonUtil</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-01-30 21:42
 */
public class JsonUtil {

    public static String getJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T getObjFromJson(String json, Class<T> klass) {
        Gson gson = new Gson();
        T t = gson.fromJson(json, klass);
        return t;
    }
}
