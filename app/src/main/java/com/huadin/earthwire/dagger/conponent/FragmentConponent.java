package com.huadin.earthwire.dagger.conponent;

import com.huadin.earthwire.View.fragment.HistoryWorkFragment;
import com.huadin.earthwire.dagger.module.FragmentModule;

import dagger.Component;

/**
 * Created by 华电 on 2017/5/12.
 */
@Component(modules = FragmentModule.class)
public interface FragmentConponent {
  void in(HistoryWorkFragment view);
}
