package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.adapter.WorkAdapter;
import com.huadin.earthwire.View.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.baidu.mapapi.BMapManager.getContext;
import static com.huadin.earthwire.R.string.phone;

/**
 * Created by 华电 on 2017/5/12.
 */

public class StartWorkActivity extends BaseActivity {

  private static final int REQUEST_NEWWORK = 1;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.fab)
  FloatingActionButton fab;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  private List<Project> projectList = new ArrayList<>();
  private WorkAdapter mAdapter;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_start_work;
  }

  @Override
  protected void initView() {
    initToolBar(mToolbar,true,"开始作业");

    mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new WorkAdapter(this, projectList);
    mRecyclerview.setAdapter(mAdapter);
  }

  @Override
  protected void initlistener() {

  }

  @Override
  protected void initData() {

  }

  @OnClick(R.id.fab)
  public void onClick() {

    //跳转到新建工程界面
    Intent intent = new Intent(this,NewFWorkActivity.class);
    startActivityForResult(intent,REQUEST_NEWWORK);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==REQUEST_NEWWORK ){

      String workName = data.getStringExtra("workName");
      String teamHead = data.getStringExtra("teamHead");
      String phone = data.getStringExtra("phone");
      //projectList.add(project);

      //重新展示下界面

      //跳转到作业中界面
      //intent.putExtras(bundle);
      data.setClass(this,DetailWorkActivity.class);
      startActivity(data);
    }
  }


}
