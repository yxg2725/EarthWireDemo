package com.huadin.earthwire.View.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.Presenter.activity.StartWorkActivityPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.adapter.ProjectListAdapter;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.dagger.conponent.DaggerCommonConponent;
import com.huadin.earthwire.dagger.module.PresenterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by 华电 on 2017/5/12.
 */

public class StartWorkActivity extends BaseActivity implements ProjectListAdapter.OnToDetailListener {

  @Inject
  StartWorkActivityPresenter startWorkActivityPresenter;
  public static final int REQUEST_NEWWORK = 1;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.fab)
  FloatingActionButton fab;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  private List<Project> projectList = new ArrayList<>();
  private ProjectListAdapter mAdapter;
  private ProgressDialog dialog;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_start_work;
  }

  @Override
  protected void initView() {

    DaggerCommonConponent.builder()
            .presenterModule(new PresenterModule(this)).build().in(this);

    dialog = new ProgressDialog(this);

    initToolBar(mToolbar, true, "开始作业");
    mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

    if (mAdapter == null){
      mAdapter = new ProjectListAdapter(this, projectList);
      mRecyclerview.setAdapter(mAdapter);
    }else{
      mAdapter.notifyDataSetChanged();
    }

  }

  @Override
  protected void initlistener() {
    mAdapter.setOnToDetailListener(this);
  }

  @Override
  protected void initData() {
  }

  @Override
  protected void onResume() {
    super.onResume();
    //查询数据库获取当前的工程 并展示
    dialog.show();
    startWorkActivityPresenter.queryCurrentProject();
  }

  @OnClick(R.id.fab)
  public void onClick() {

    //跳转到新建工程界面
    Intent intent = new Intent(this, NewFWorkActivity.class);
    startActivityForResult(intent, REQUEST_NEWWORK);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_NEWWORK) {
      startWorkActivityPresenter.getResultData(requestCode,resultCode,data);

      //跳转到作业中界面
      data.setClass(this, DetailWorkActivity.class);
      startActivity(data);
    }
  }

  @Override
  public void success(Object o) {
    super.success(o);
    dialog.dismiss();

    List<Project> list = (List<Project>) o;
    projectList.clear();
    projectList.addAll(list);

    if (mAdapter == null){
      mAdapter = new ProjectListAdapter(this, projectList);
      mRecyclerview.setAdapter(mAdapter);
    }else{
      mAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onToDetailListenerCallBack(String workname) {
    Intent intent = new Intent(this, DetailWorkActivity.class);
    intent.putExtra("workname",workname);
    intent.putExtra("tag",this.getClass().getSimpleName());
    startActivity(intent);
  }
}
