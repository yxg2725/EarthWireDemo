package com.huadin.earthwire.View.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.adapter.WorkAdapter;
import com.huadin.earthwire.View.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业列表Fragment
 */
public class WorkListFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.fab)
    public FloatingActionButton fab;

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
//        String name = getArguments().getString(ConstUtil.KEY_WORK_NAME);
//        ((ContainerActivity) getActivity()).setToolbarTitle(name);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        showToast("点击");
    }
}
