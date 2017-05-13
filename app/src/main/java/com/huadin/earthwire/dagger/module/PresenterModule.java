package com.huadin.earthwire.dagger.module;

import com.huadin.earthwire.Presenter.activity.DetailWorkActivityPresenter;
import com.huadin.earthwire.Presenter.activity.LoginActivityPresenter;
import com.huadin.earthwire.Presenter.activity.MainActivityPresenter;
import com.huadin.earthwire.Presenter.activity.NewFWorkActivityPresenter;
import com.huadin.earthwire.Presenter.activity.StartWorkActivityPresenter;
import com.huadin.earthwire.Presenter.activity.UserInfoSetActivityPresenter;
import com.huadin.earthwire.View.activity.DetailWorkActivity;
import com.huadin.earthwire.View.activity.LoginActivity;
import com.huadin.earthwire.View.activity.MainActivity;
import com.huadin.earthwire.View.activity.NewFWorkActivity;
import com.huadin.earthwire.View.activity.StartWorkActivity;
import com.huadin.earthwire.View.activity.UserInfoSetActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 华电 on 2017/4/27.
 */

@Module
public class PresenterModule {
    private LoginActivity loginActivity;
    private DetailWorkActivity detailWorkActivity;
    private StartWorkActivity startWorkActivity;
    private UserInfoSetActivity UserInfoSetActivity;
    private MainActivity mainActivity;
    private NewFWorkActivity newFWorkActivity;

    public PresenterModule(LoginActivity view) {
        this.loginActivity = view;
    }

    public PresenterModule(DetailWorkActivity view) {
        this.detailWorkActivity = view;
    }
    public PresenterModule(StartWorkActivity view) {
        this.startWorkActivity = view;
    }
    public PresenterModule(UserInfoSetActivity view) {
        this.UserInfoSetActivity = view;
    }
    public PresenterModule(MainActivity view) {
        this.mainActivity = view;
    }
    public PresenterModule(NewFWorkActivity view) {
        this.newFWorkActivity = view;
    }



    /**
     * 登录界面
     */
    @Provides
    public LoginActivityPresenter provideLoginPresenter() {
        return new LoginActivityPresenter(loginActivity);
    }
    /**
     * 详情界面
     */
    @Provides
    public DetailWorkActivityPresenter provideDetailWorkPresenter() {
        return new DetailWorkActivityPresenter(detailWorkActivity);
    }
    /**
     * 开始作业界面
     */
    @Provides
    public StartWorkActivityPresenter provideStartWorkPresenter() {
        return new StartWorkActivityPresenter(startWorkActivity);
    }
    /**
     * 用户设置界面
     */
    @Provides
    public UserInfoSetActivityPresenter provideUserInfoSetPresenter() {
        return new UserInfoSetActivityPresenter(UserInfoSetActivity);
    }
    /**
     * MainActivity
     */
    @Provides
    public MainActivityPresenter provideMainPresenter() {
        return new MainActivityPresenter(mainActivity);
    }
    /**
     * newFWorkActivity
     */
    @Provides
    public NewFWorkActivityPresenter provideNewFWorkPresenter() {
        return new NewFWorkActivityPresenter(newFWorkActivity);
    }

}
