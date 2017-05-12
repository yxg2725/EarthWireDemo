package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 华电 on 2017/5/12.
 */

public class DetailWorkActivity extends BaseActivity {
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.fab)
  FloatingActionButton mFab;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_start_work;
  }

  @Override
  protected void initView() {
    /*Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Project project = (Project) bundle.getSerializable("project");*/

    //initToolBar(mToolbar,true,project.getWorkName());
    initToolBar(mToolbar,true,"工程名");
  }

  @Override
  protected void initlistener() {
  }

  @Override
  protected void initData() {
  }


  @OnClick(R.id.fab)
  public void onViewClicked() {
    showToast("点击了啊");
  }
}
