package com.huadin.earthwire.View.fragment;

import android.widget.TextView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.WorkActivity;
import com.huadin.earthwire.View.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 华电 on 2017/4/26.
 */

public class EarthWireWorkFragment extends BaseFragment {

    @BindView(R.id.tv_start_work)
    TextView mTvStartWork;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_earth_wire_work;
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
        startToActivity(null, WorkActivity.class);
    }
}
