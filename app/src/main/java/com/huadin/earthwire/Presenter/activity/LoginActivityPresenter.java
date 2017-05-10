package com.huadin.earthwire.Presenter.activity;

import com.huadin.earthwire.Model.net.bean.User;
import com.huadin.earthwire.MyApp;
import com.huadin.earthwire.Presenter.base.BasePresenter;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.Utils.MD5Util;
import com.huadin.earthwire.Utils.SharedPreferenceUtils;
import com.huadin.earthwire.View.activity.LoginActivity;

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

        String userInfo = MD5Util.md5(MyApp.getMyAppInstance().getUserBeanInfo(user));
        //保存到本地
        SharedPreferenceUtils.getInstance(MyApp.context)
                .putStringSharedPrefreence(Constant.PREFERENCE_USER_ID,userInfo);

    }
}
