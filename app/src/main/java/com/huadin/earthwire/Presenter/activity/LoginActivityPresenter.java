package com.huadin.earthwire.Presenter.activity;

import android.text.TextUtils;

import com.huadin.earthwire.Presenter.base.BasePresenter;
import com.huadin.earthwire.View.activity.LoginActivity;

/**
 * 处理登录逻辑
 */

public class LoginActivityPresenter extends BasePresenter {

  private LoginActivity view;

  //构造
  public LoginActivityPresenter(LoginActivity view){
    this.view = view;
  }

  /**
   * 登录校验逻辑
   */
  public void login(String userName, String pwd){
    //检验
    if (checkInput(userName,pwd)){
      if(userName.equals("admin") && pwd.equals("123")){
        view.loginSuccess("登录成功");
      }else {
        view.loginFailed("账号或密码错误");
        return;
      }
    }
  }

  /**
   * 校验用户名和密码是输入格式正确
   */
  public boolean checkInput(String userName, String pwd){
    if(TextUtils.isEmpty(userName)){
      view.failed("name");
      return false;
    }
    if (TextUtils.isEmpty(pwd)){
      view.failed("pwd");
      return false;
    }
    return true;
  }

}
