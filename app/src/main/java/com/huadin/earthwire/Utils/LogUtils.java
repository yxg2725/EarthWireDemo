package com.huadin.earthwire.Utils;

import android.util.Log;

/**
 * Created by 华电 on 2017/4/27.
 */

public class LogUtils {
    private static boolean open = true;

    public static <T> void logi(Class<T> clazz, String msg) {
        if (open) {
            Log.i(clazz.getSimpleName(), msg);
        }
    }
}
