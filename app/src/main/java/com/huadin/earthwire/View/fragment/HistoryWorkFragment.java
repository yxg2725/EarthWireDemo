package com.huadin.earthwire.View.fragment;

import android.view.View;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.ContainerActivity;
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


    @Override
    public void onResume() {
        super.onResume();
        ((ContainerActivity) getActivity()).setToolbarTitle("历史作业");
        ((ContainerActivity) getActivity()).fab.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((ContainerActivity)getActivity()).setToolbarMenu("", true);
    }
}
