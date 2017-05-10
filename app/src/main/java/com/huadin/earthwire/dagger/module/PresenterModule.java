package com.huadin.earthwire.dagger.module;

import com.huadin.earthwire.Presenter.activity.LoginActivityPresenter;
import com.huadin.earthwire.View.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 华电 on 2017/4/27.
 */

@Module
public class PresenterModule {
    private LoginActivity loginActivity;

    public PresenterModule(LoginActivity view) {
        this.loginActivity = view;
    }


    /**
     * 生成登录的
     *
     * @return
     */
    @Provides
    public LoginActivityPresenter provideLoginPresenter() {
        return new LoginActivityPresenter(loginActivity);
    }


}
