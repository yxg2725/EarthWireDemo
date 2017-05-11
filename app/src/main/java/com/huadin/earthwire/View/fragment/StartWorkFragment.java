package com.huadin.earthwire.View.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.adapter.WorkAdapter;
import com.huadin.earthwire.View.base.BaseFragment;

import butterknife.BindView;

/**
 * 开始作业Fragment
 */
public class StartWorkFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;


    private int num = 1;
    private WorkAdapter mAdapter;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_start_work;
    }

    @Override
    public void initView() {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new WorkAdapter(getActivity(), num);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        stateLayout.showContentView();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ContainerActivity)getActivity()).setToolbarTitle("开始作业");
//        ((ContainerActivity)getActivity()).setToolbarMenu("", true);
        ((ContainerActivity)getActivity()).fab.setVisibility(View.VISIBLE);
    }
}
