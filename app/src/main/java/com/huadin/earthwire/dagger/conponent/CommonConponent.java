package com.huadin.earthwire.dagger.conponent;

import com.huadin.earthwire.Presenter.activity.NewFWorkActivityPresenter;
import com.huadin.earthwire.View.activity.DetailWorkActivity;
import com.huadin.earthwire.View.activity.LoginActivity;
import com.huadin.earthwire.View.activity.MainActivity;
import com.huadin.earthwire.View.activity.NewFWorkActivity;
import com.huadin.earthwire.View.activity.StartWorkActivity;
import com.huadin.earthwire.View.activity.UserInfoSetActivity;
import com.huadin.earthwire.dagger.module.PresenterModule;

import dagger.Component;

/**
 * Created by 华电 on 2017/4/27.
 */

@Component(modules = PresenterModule.class)
public interface CommonConponent {
    void in(LoginActivity view);
    void in(DetailWorkActivity view);
    void in(StartWorkActivity view);
    void in(UserInfoSetActivity view);
    void in(MainActivity view);
    void in(NewFWorkActivity view);
}
