
package com.digua.core.utils;

import android.util.Log;

/**
 * Log日志打印操作
 */
public class LogUtils {

    public static boolean isDebug;

    public static void init(boolean debug){
        isDebug = debug;
    }

    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;

    /**
     * 获取当前类名
     * @return
     */
    private static String getClassName() {
        // 这里的数组的index，即2，是根据你工具类的层级取的值，可根据需求改变
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());
        return result;
    }


    public static void w(String logString) {
        if (isDebug) {
            Log.w(getClassName(), logString);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void d(String TAG, String msg) {
        if (isDebug) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                //剩下的文本还是大于规定长度则继续重复截取并输出
                if (strLength > end) {
                    Log.d(TAG + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.d(TAG, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    /**
     * error log
     *
     * @param msg
     */
    public static void e(String TAG, String msg) {
        if(isDebug){
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                //剩下的文本还是大于规定长度则继续重复截取并输出
                if (strLength > end) {
                    Log.e(TAG + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(TAG, msg.substring(start, strLength));
                    break;
                }
            }
        }

    }


    /**
     * debug log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(getClassName(), msg);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isDebug) {
            Log.i(getClassName(), msg);
        }
    }
    /**
     * error log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(getClassName(), msg);
        }
    }


    /**
     * error log
     *
     * @param msg
     */
    public static void e2(String msg) {
        if (isDebug) {
            Log.e("digua", msg);
        }
    }

    public static void i(String tag, String logString) {
        if (isDebug) {
            Log.i(tag, logString);
        }
    }



    public static void w(String tag, String logString) {
        if (isDebug) {
            Log.w(tag, logString);
        }
    }



}
