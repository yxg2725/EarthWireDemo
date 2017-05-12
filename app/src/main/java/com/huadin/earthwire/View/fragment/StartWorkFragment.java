package com.huadin.earthwire.View.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.ConstUtil;
import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.adapter.WorkAdapter;
import com.huadin.earthwire.View.base.BaseFragment;
import com.huadin.earthwire.event.FragmentNextEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开始作业Fragment
 */
public class StartWorkFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.fab)
    public FloatingActionButton fab;

    private int num = 1;
    private WorkAdapter mAdapter;
    FragmentNextEvent next;// 界面跳转EventBus使用的Event

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
        ((ContainerActivity) getActivity()).setToolbarTitle("开始作业");
        //        ((ContainerActivity)getActivity()).setToolbarMenu("", true);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        next = new FragmentNextEvent();
        next.setViewId(ConstUtil.KEY_FRAGMENT_NEW_WORK);
        EventBus.getDefault().post(next);
        showToast("点击");
    }
}
