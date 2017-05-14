package com.huadin.earthwire;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

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
