package com.huadin.earthwire.Presenter.activity;

import android.content.Intent;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.ProjectDao;
import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.View.activity.StartWorkActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class StartWorkActivityPresenter {
  private final StartWorkActivity startWorkActivity;

  public StartWorkActivityPresenter(StartWorkActivity startWorkActivity) {

    this.startWorkActivity = startWorkActivity;
  }

  public void getResultData(int requestCode, int resultCode, Intent data) {
      String workName = data.getStringExtra("workName");
      String teamHead = data.getStringExtra("teamHead");

    List<Person> persons = DaoManager.getInstance().getDaoSession().getPersonDao().loadAll();
    Person person = persons.get(persons.size() - 1);
    String projectTeam = person.getProjectTeam();
    String userName = person.getUserName();

    //获取当前系统时间 为该工程的采集时间
      long startTimeMillis = System.currentTimeMillis();
      String startTime = DateUtil.toymdhms(startTimeMillis);//采集时间

      Project project = new Project();
      project.setWorkName(workName);//工程名
      project.setStartTime(startTime);//开始时间
      project.setStartTimeMillis(startTimeMillis);//开始时间long值
      project.setProjectTeamName(projectTeam);//工程队名
      project.setProjectTeamHeadName(teamHead);//工程队负责人名
      project.setWorker(userName);//创建人
      project.setCompleteState("进行中");

      //保存到数据库
    DaoManager.getInstance().getDaoSession().getProjectDao().insert(project);
  }

  public void queryCurrentProject() {
    List<Project> list = DaoManager.getInstance().getDaoSession().getProjectDao()
            .queryBuilder().where(ProjectDao.Properties.CompleteState.eq("进行中"))
            .list();

    startWorkActivity.success(list);
  }
}
