package com.huadin.earthwire.dagger.conponent;

import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.activity.LoginActivity;
import com.huadin.earthwire.dagger.module.PresenterModule;

import dagger.Component;

/**
 * Created by 华电 on 2017/4/27.
 */

@Component(modules = PresenterModule.class)
public interface CommonConponent {
    void in(LoginActivity view);
    void in(ContainerActivity view);
}
