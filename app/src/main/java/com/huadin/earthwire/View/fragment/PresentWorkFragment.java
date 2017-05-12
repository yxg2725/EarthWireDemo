package com.huadin.earthwire.View.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.ConstUtil;
import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 当前作业Fragment
 */
public class PresentWorkFragment extends BaseFragment {

    @BindView(R.id.tv_start_work)
    TextView mTvStartWork;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_present_work;
    }

    @Override
    public void initView() {
        initMapView();
    }


    @Override
    public void initData() {
        //1.从网络加载数据
        //2.显示内容布局
        stateLayout.showContentView();
    }


    @Override
    public void onReload() {
        //重新定位
        initMapView();
    }


    @OnClick(R.id.tv_start_work)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstUtil.KEY_FRAGMENT_ID, ConstUtil.KEY_FRAGMENT_START_WORK);
        startToActivity(bundle, ContainerActivity.class);
    }
}
