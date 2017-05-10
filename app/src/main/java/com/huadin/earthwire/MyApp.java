package com.huadin.earthwire;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.baidu.mapapi.SDKInitializer;
import com.huadin.earthwire.Model.net.bean.User;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.Utils.LogUtils;
import com.huadin.earthwire.Utils.MD5Util;
import com.huadin.earthwire.Utils.SharedPreferenceUtils;
import com.huadin.earthwire.View.activity.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by 华电 on 2017/4/26.
 */

public class MyApp extends Application {

    public  static Context context;
    public  static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //百度地图初始化操作
        SDKInitializer.initialize(this);
    }

    public static MyApp getMyAppInstance(){
        if (myApp == null){
            return new MyApp();
        }
        return myApp;
    }

}
