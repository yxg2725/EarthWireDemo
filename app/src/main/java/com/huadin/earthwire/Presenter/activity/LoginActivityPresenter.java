package com.huadin.earthwire.Presenter.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.huadin.earthwire.Model.net.bean.User;
import com.huadin.earthwire.MyApp;
import com.huadin.earthwire.Presenter.base.BasePresenter;
import com.huadin.earthwire.Utils.BeanUtil;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.Utils.LogUtils;
import com.huadin.earthwire.Utils.MD5Util;
import com.huadin.earthwire.Utils.SharedPreferenceUtils;
import com.huadin.earthwire.View.activity.LoginActivity;
import com.huadin.earthwire.View.activity.MainActivity;

/**
 * 处理登录逻辑
 */

public class LoginActivityPresenter extends BasePresenter {

    private LoginActivity view;

    //构造
    public LoginActivityPresenter(LoginActivity view) {
        this.view = view;
    }

    /**
     * 登录校验逻辑
     */
    public void login(String userName, String pwd) {
        if (userName.equals("admin") && pwd.equals("123")) {
            view.success("登录成功");
        } else {
            view.failed("账号或密码错误");
            return;
        }
    }


    public void saveUserInfo(String userName, String pwd) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(pwd);

        String userInfo = MD5Util.md5(BeanUtil.getUserBeanInfo(user));
        //保存到本地
        SharedPreferenceUtils.getInstance(MyApp.context)
                .putStringSharedPrefreence(Constant.PREFERENCE_USER_ID,userInfo);

    }

    public void toMainActivity() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        String userInfo = MD5Util.md5(BeanUtil.getUserBeanInfo(user));
        LogUtils.logi(this.getClass(),"UserInfo0：" + userInfo);
        //获取本地用户信息
        String localUserInfo = SharedPreferenceUtils.getInstance(MyApp.context)
                .getStringSharedPreference(Constant.PREFERENCE_USER_ID,"");
        LogUtils.logi(this.getClass(),"UserInfo1：" + localUserInfo);

        if(TextUtils.equals(userInfo,localUserInfo)){
            view.toMainActivity();
        }
    }
}
