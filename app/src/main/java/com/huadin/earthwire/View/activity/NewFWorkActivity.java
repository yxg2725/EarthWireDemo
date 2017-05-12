package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 华电 on 2017/5/12.
 */

public class NewFWorkActivity extends BaseActivity {
  public static final int RESULT_NEWWORK = 1;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.et_work_name)
  EditText mEtWorkName;
  @BindView(R.id.et_build_team_head)
  EditText mEtBuildTeamHead;
  @BindView(R.id.et_phone)
  EditText mEtPhone;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_new_work;
  }

  @Override
  protected void initView() {
    initToolBar(mToolbar,true,"新建作业");
  }

  @Override
  protected void initlistener() {

  }

  @Override
  protected void initData() {
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_tool_bar_button,menu);
    menu.findItem(R.id.title_right_button).setTitle(getString(R.string.text_complete));
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    //获取控件数据
    String workName = mEtWorkName.getText().toString();
    String teamHead = mEtBuildTeamHead.getText().toString();
    String phone = mEtPhone.getText().toString();


    switch(item.getItemId()){
      case R.id.title_right_button:
        returnStartFragment(workName,teamHead,phone);//返回开始作业界面
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void returnStartFragment(String workName,String teamHead,String phone) {
    Intent intent = new Intent();
    intent.putExtra("workName",workName);
    intent.putExtra("teamHead",teamHead);
    intent.putExtra("phone",phone);
    setResult(RESULT_NEWWORK,intent);
    finish();
  }
}
