package com.kered.dklog;

import android.util.Log;


public class DKLog {
    public static int displayLevel = Log.VERBOSE;
    private static boolean enable = false;

    public static void setEnable(boolean value) {
        enable = value;
    }

    public static void v(String tag, String msg) {
        if (enable && Log.VERBOSE >= displayLevel) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (enable && Log.DEBUG >= displayLevel) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (enable && Log.INFO >= displayLevel) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (enable && Log.WARN >= displayLevel) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (enable && Log.ERROR >= displayLevel) {
            Log.e(tag, msg);
        }
    }


}
