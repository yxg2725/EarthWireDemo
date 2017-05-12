package com.huadin.earthwire.dagger.module;

import com.huadin.earthwire.Presenter.fragment.HistoryFragmentPresenter;
import com.huadin.earthwire.View.fragment.HistoryWorkFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 华电 on 2017/5/12.
 */

@Module
public class FragmentModule {
  private HistoryWorkFragment historyWorkFragment;

  public FragmentModule(HistoryWorkFragment view) {
    this.historyWorkFragment = view;
  }

  /**
   * 生成历史任务
   *
   * @return
   */
  @Provides
  public HistoryFragmentPresenter provideHistoryPresenter() {
    return new HistoryFragmentPresenter(historyWorkFragment);
  }
}
