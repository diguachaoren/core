package com.digua.core.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SPUtil {

    /*
    * 保存对象
    * */
    public static void putValueObject(Context context,String key, Object value) {
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        sp.putString(key, json);
        sp.commit();
    }

    /*
     * 获取对象
     * */
    public static <T> T getValueObject(Context context,String key,Class<T> className) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        String json = sp.getString(key, null);
        return new Gson().fromJson(json,className);
    }

    public static void putValue(Context context,String key, int value) {
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        sp.putInt(key, value);
        sp.commit();
    }
    public static void putValue(Context context,String key, long value) {
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        sp.putLong(key, value);
        sp.commit();
    }
    public static boolean putValue(Context context,String key, boolean value) {
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        sp.putBoolean(key, value);
        return sp.commit();
    }
    public static boolean putValue(Context context,String key, float value) {
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        sp.putFloat(key, value);
        return sp.commit();
    }
    public static void putValue(Context context,String key, String value) {
        if(context == null)  return;
        Editor sp =  context.getSharedPreferences(key, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS).edit();
        sp.putString(key, value);
        sp.commit();
    }
    public static int getValue(Context context,String key, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        int value = sp.getInt(key, defValue);
        return value;
    }
    public static float getValue(Context context,String key, float defValue) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        float value = sp.getFloat(key, defValue);
        return value;
    }
    public static boolean getValue(Context context,String key, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }
    public static long getValue(Context context,String key, long defValue) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        long value = sp.getLong(key, defValue);
        return value;
    }
    public static String getValue(Context context,String key, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(key, context.MODE_MULTI_PROCESS);
        String value = sp.getString(key, defValue);
        return value;
    }


    public static void putPreferenceValue(Context context,String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
    }

    public static void putPreferenceValue(Context context,String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static void putPreferenceValue(Context context,String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static int getPreferenceValue(Context context,String key, int defValue) {
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(context);
        int value = shp.getInt(key, defValue);
        return value;
    }

    public static boolean getPreferenceValue(Context context,String key, boolean defValue) {
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean value = shp.getBoolean(key, defValue);
        return value;
    }

    public static String getPreferenceValue(Context context,String key, String defValue) {
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(context);
        String value = shp.getString(key, defValue);
        return value;
    }
    public static void clear() {
//        SharedPreferences preferences = App.getInstance().getSharedPreferences(Constants.SP_TAG, Context.MODE_PRIVATE);
//        Editor editor = preferences.edit();
//        editor.clear();
//
//        editor.commit();
    }

}
