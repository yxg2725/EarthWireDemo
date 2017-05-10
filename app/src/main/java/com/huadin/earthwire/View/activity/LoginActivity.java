package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.huadin.earthwire.Presenter.activity.LoginActivityPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.dagger.conponent.DaggerCommonConponent;
import com.huadin.earthwire.dagger.module.PresenterModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 华电 on 2017/5/9.
 */

public class LoginActivity extends BaseActivity {
  @Inject
  LoginActivityPresenter loginPresenter;

  @BindView(R.id.et_name)
  TextInputEditText mEtName;
  @BindView(R.id.et_pwd)
  TextInputEditText mEtPwd;
  @BindView(R.id.btn_login)
  Button mBtnLogin;

  @Override
  public int getlayoutId() {
    return R.layout.activity_login;
  }

  @Override
  protected void initView() {
    DaggerCommonConponent.builder().presenterModule(new PresenterModule(this)).build().in(this);
  }

  @Override
  protected void initlistener() {
  }

  @Override
  protected void initData() {
  }


  @OnClick(R.id.btn_login)
  public void onClick() {
    //获取账号
    String userName = mEtName.getText().toString();
    //获取密码
    String pwd = mEtPwd.getText().toString();
    loginPresenter.login(userName,pwd);
  }

  /**
   * 登录成功
   */
  public void loginSuccess(String msg){
    showToast(msg);
    //跳转到主界面
    finish();
    startActivity(new Intent(this,MainActivity.class));
  }

  /**
   * 登录失败
   */
  public void loginFailed(String msg){
    showToast(msg);
  }


  @Override
  public void success(Object o) {
  }

  @Override
  public void failed(String msg) {
    if(msg.equals("name")){
      mEtName.setError("账号不能为空");
      mEtName.setFocusable(true);
    }else{
      mEtPwd.setError("密码不能为空");
      mEtPwd.setFocusable(true);
    }
  }
}
