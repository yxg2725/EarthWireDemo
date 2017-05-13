package com.huadin.earthwire.View.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.Presenter.activity.UserInfoSetActivityPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.dagger.conponent.DaggerCommonConponent;
import com.huadin.earthwire.dagger.module.PresenterModule;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 华电 on 2017/5/13.
 */

public class UserInfoSetActivity extends BaseActivity {

  @Inject
  UserInfoSetActivityPresenter userInfoSetActivityPresenter;

  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.et_name)
  EditText mEtName;
  @BindView(R.id.et_project)
  Spinner mEtProject;
  @BindView(R.id.et_project_head)
  EditText mEtProjectHead;
  @BindView(R.id.et_head_phone)
  EditText mEtHeadPhone;

  @Override
  public int getlayoutId() {
    return R.layout.activity_userinfo;
  }

  @Override
  protected void initView() {
    DaggerCommonConponent.builder().presenterModule(new PresenterModule(this)).build().in(this);
    initToolBar(mToolbar,true,"个人信息设置");
  }

  @Override
  protected void initlistener() {
  }

  @Override
  protected void initData() {
    //查询数据库
    userInfoSetActivityPresenter.queryInfo();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_tool_bar_button, menu);
    menu.findItem(R.id.title_right_button).setTitle("完成");
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if(item.getItemId()==R.id.title_right_button){
      //获取输入的信息
      String name = mEtName.getText().toString();
      int projectTeamId = mEtProject.getSelectedItemPosition();
      String projectTeam = Constant.PROJECT_TEAM_NAMES[projectTeamId];
      String projectHead = mEtProjectHead.getText().toString();
      String headPhone = mEtHeadPhone.getText().toString();

      //保存到数据库
      userInfoSetActivityPresenter.saveToDB(name,projectTeamId,projectTeam,projectHead,headPhone);

    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void success(Object o) {
    super.success(o);
    finish();
  }

  public void queryComplete(Person person) {
    mEtName.setText(person.getUserName());
    mEtProject.setSelection(person.getProjectTeamId());
    mEtProjectHead.setText(person.getProjectTeamHead());
    mEtHeadPhone.setText(person.getProjectTeamHeadPhone());
  }
}
