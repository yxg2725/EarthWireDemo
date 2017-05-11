package com.huadin.earthwire.View.fragment;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseFragment;

/**
 * Created by Jack Zhang on 2017/5/11.
 */

public class HistoryWorkFragment extends BaseFragment {

    @Override
    public int getlayoutId() {
        return R.layout.fragment_history_work;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        stateLayout.showContentView();
    }
}
