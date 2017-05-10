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

        toMainActivity();

        //百度地图初始化操作
        SDKInitializer.initialize(this);
    }

    public static MyApp getMyAppInstance(){
        if (myApp == null){
            return new MyApp();
        }
        return myApp;
    }

    /**
     * 判断登陆过 则自动登录
     */
    private void toMainActivity() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        String userInfo = MD5Util.md5(getUserBeanInfo(user));
        //获取本地用户信息
        String localUserInfo = SharedPreferenceUtils.getInstance(this)
                .getStringSharedPreference(Constant.PREFERENCE_USER_ID,"");

        if(TextUtils.equals(userInfo,localUserInfo)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public byte[] getUserBeanInfo(User user){
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建对象输出流，并封装字节流

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }


}
