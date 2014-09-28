package com.supergod.util;

import android.util.Log;

/**
 * Created by acer on 2014/9/28.
 */
public class SLog {

    private static boolean isLogAble = true;

    public static int e(String tag, String msg, Throwable tr) {
        if (isLogAble) Log.e(tag, msg, tr);
        return 0;
    }

    public static int e(String tag, String msg) {
        if (isLogAble) Log.e(tag, msg);
        return 0;
    }

    public static int w(String tag, Throwable tr) {
        if (isLogAble) Log.w(tag, tr);
        return 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (isLogAble) Log.w(tag, msg, tr);
        return 0;
    }

    public static int w(String tag, String msg) {
        if (isLogAble) Log.w(tag, msg);
        return 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (isLogAble) Log.i(tag, msg, tr);
        return 0;
    }

    public static int i(String tag, String msg) {
        if (isLogAble) Log.i(tag, msg);
        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (isLogAble) Log.d(tag, msg, tr);
        return 0;
    }

    public static int d(String tag, String msg) {
        if (isLogAble) Log.d(tag, msg);
        return 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (isLogAble) Log.v(tag, msg, tr);
        return 0;
    }

    public static int v(String tag, String msg) {
        if (isLogAble) Log.v(tag, msg);
        return 0;
    }
}
