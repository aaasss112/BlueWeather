package com.blueweather.app.utils;

import android.util.Log;

/**
 * Created by user on 2016/3/3.
 */
public class LogUtils {
    private static final int DEBUG = 1;
    private static final int INFO = 2;
    private static final int WARN = 3;
    private static final int ERROR = 4;
    private static int LEVEL = 1;

    /**
     * 错误信息日志
     *
     * @param TAG
     * @param msg
     */
    public final static void e(String TAG, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 提醒日志
     *
     * @param TAG
     * @param msg
     */
    public final static void w(String TAG, String msg) {
        if (LEVEL <= WARN) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 信息日志
     *
     * @param TAG
     * @param msg
     */
    public final static void i(String TAG, String msg) {
        if (LEVEL <= INFO) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 调试日志
     *
     * @param TAG
     * @param msg
     */
    public final static void d(String TAG, String msg) {
        if (LEVEL <= DEBUG) {
            Log.e(TAG, msg);
        }
    }
}
