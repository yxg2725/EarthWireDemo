package com.huadin.earthwire.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 华电 on 2017/4/26.
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();

    }
}
