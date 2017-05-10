package com.huadin.earthwire.View.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.ConstUtil;
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

    private String flag;// 判断是从哪个页面跳转到该Activity的标志

    @Override
    public int getlayoutId() {
        return R.layout.activity_work;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        flag = bundle.getString(ConstUtil.KEY_FROM_WHAT_TO_WORK_ACTIVITY);
        if (TextUtils.equals(flag, ConstUtil.KEY_START_WORK)) {
            initToolBar(mToolbar, true, getString(R.string.title_start_work));
        } else if (TextUtils.equals(flag, ConstUtil.KEY_HISTORY_WORK)) {
            initToolBar(mToolbar, true, getString(R.string.title_history_work));
            mFab.setVisibility(View.GONE);
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tool_bar_button, menu);
        if (TextUtils.equals(flag, ConstUtil.KEY_START_WORK)) {
            menu.getItem(0).setTitle(getString(R.string.text_new));
        } else if (TextUtils.equals(flag, ConstUtil.KEY_HISTORY_WORK)) {
            menu.getItem(0).setTitle(getString(R.string.text_filtrate));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (TextUtils.equals(flag, ConstUtil.KEY_START_WORK)) {
            startToActivity(null, NewWorkActivity.class);
        } else if (TextUtils.equals(flag, ConstUtil.KEY_HISTORY_WORK)) {
            // TODO: 2017/5/10

        }
        return super.onOptionsItemSelected(item);
    }
}
