package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.huadin.earthwire.Model.dao.bean.EarthWire;
import com.huadin.earthwire.Presenter.activity.DetailWorkActivityPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.View.adapter.ProjectDetailAdapter;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.dagger.conponent.DaggerCommonConponent;
import com.huadin.earthwire.dagger.module.PresenterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 华电 on 2017/5/12.
 */

public class DetailWorkActivity extends BaseActivity {

  public static final int REQUEST_NEW_EARTHWIRE = 1;
  @Inject
  DetailWorkActivityPresenter detailWorkActivityPresenter;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.fab)
  FloatingActionButton mFab;
  private ProjectDetailAdapter mAdapter;
  private String workName;
  private List<EarthWire> earthWireList = new ArrayList<>();

  @Override
  public int getlayoutId() {
    return R.layout.fragment_start_work;
  }

  @Override
  protected void initView() {

    DaggerCommonConponent.builder()
            .presenterModule(new PresenterModule(this)).build().in(this);

    Intent intent = getIntent();
    workName = intent.getStringExtra("workName");

    initToolBar(mToolbar,true, workName);
    mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    if(mAdapter == null){
      mAdapter = new ProjectDetailAdapter(this,earthWireList);
      mRecyclerview.setAdapter(mAdapter);
    }else{
      mAdapter.notifyDataSetChanged();
    }

  }

  @Override
  protected void initlistener() {
  }

  @Override
  protected void initData() {
  }

  @Override
  protected void onResume() {
    super.onResume();
    //查询地线数据库  对应的工程名下的地线信息
    detailWorkActivityPresenter.queryInfo(workName);
  }

  @OnClick(R.id.fab)
  public void onViewClicked() {
    Intent intent = new Intent(this,NewEarthWireActivity.class);
    startActivityForResult(intent,REQUEST_NEW_EARTHWIRE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    detailWorkActivityPresenter.saveInfo(requestCode, resultCode, data,workName);
  }

  @Override
  public void success(Object o) {
    super.success(o);
      List<EarthWire> list = (List<EarthWire>) o;
      earthWireList.clear();
      earthWireList.addAll(list);

    if(mAdapter == null){
      mAdapter = new ProjectDetailAdapter(this,earthWireList);
      mRecyclerview.setAdapter(mAdapter);
    }else{
      mAdapter.notifyDataSetChanged();
    }
  }
}
