package com.huadin.earthwire;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by 华电 on 2017/4/26.
 */

public class MyApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    //百度地图初始化操作
    SDKInitializer.initialize(this);
  }
}
