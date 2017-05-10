package com.huadin.earthwire.View.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
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
    private ProgressDialog dialog;
    private String userName;
    private String pwd;

    @Override
    public int getlayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        dialog = new ProgressDialog(this);
        DaggerCommonConponent.builder().presenterModule(new PresenterModule(this)).build().in(this);
    }

    @Override
    protected void initlistener() {
    }

    @Override
    protected void initData() {
        loginPresenter.toMainActivity();
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        //获取账号
        userName = mEtName.getText().toString();
        //获取密码
        pwd = mEtPwd.getText().toString();

        //检查账号和密码是否为空
        boolean checkInput = checkInput(userName, pwd);
        if (checkInput){
            dialog.show();
            loginPresenter.login(userName, pwd);
        }else {
            showToast("用户名或密码不能为空");
        }
    }


    /**
     * 登录成功
     * @param o
     */
    @Override
    public void success(Object o) {
        dialog.dismiss();
        String msg = (String)o;
        showToast(msg);

        //保存到本地 下次可以自动登录
        loginPresenter.saveUserInfo(userName,pwd);

        //跳转到主界面
        toMainActivity();
    }

    /**
     * 登录失败
     * @param msg
     */
    @Override
    public void failed(String msg) {
        dialog.dismiss();
        showToast(msg);
    }

    /**
     * 校验用户名和密码是输入格式正确 界面相关逻辑
     */
    public boolean checkInput(String userName, String pwd) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd)) {
            return false;
        }
        return true;
    }

    public void toMainActivity(){
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
