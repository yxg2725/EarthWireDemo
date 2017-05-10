package com.huadin.earthwire.View.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.adapter.WorkAdapter;
import com.huadin.earthwire.View.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 华电 on 2017/4/27.
 */

public class WorkActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private int num = 1;
    private WorkAdapter mAdapter;

    @Override
    public int getlayoutId() {
        return R.layout.activity_work;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, "地线作业一");
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WorkAdapter(this, num);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initlistener() {
        mFab.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {
        mAdapter.setItemCount(num++);
    }
}
