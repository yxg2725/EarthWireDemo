package com.huadin.earthwire.dagger.module;

import com.huadin.earthwire.Presenter.activity.ContainerActivityPresenter;
import com.huadin.earthwire.Presenter.activity.LoginActivityPresenter;
import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 华电 on 2017/4/27.
 */

@Module
public class PresenterModule {
    private LoginActivity loginActivity;
    private ContainerActivity containerActivity;

    public PresenterModule(LoginActivity view) {
        this.loginActivity = view;
    }
    public PresenterModule(ContainerActivity view) {
        this.containerActivity = view;
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

    @Provides
    public ContainerActivityPresenter provideContainerPresenter() {
        return new ContainerActivityPresenter(containerActivity);
    }


}
