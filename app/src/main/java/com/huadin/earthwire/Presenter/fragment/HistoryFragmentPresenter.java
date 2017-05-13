package com.huadin.earthwire.Presenter.fragment;

import android.text.TextUtils;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.DaoSession;
import com.huadin.earthwire.Model.dao.ProjectDao;
import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.View.fragment.HistoryWorkFragment;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 华电 on 2017/5/12.
 */

public class HistoryFragmentPresenter {
  private final HistoryWorkFragment historyWorkFragment;

  public HistoryFragmentPresenter(HistoryWorkFragment historyWorkFragment) {
    this.historyWorkFragment = historyWorkFragment;
  }

  public void query(String startTime, String projectTeamName, String workname) {
    //查询数据库
    DaoSession daoSession = DaoManager.getInstance().getDaoSession();
    ProjectDao projectDao = daoSession.getProjectDao();
    QueryBuilder<Project> where = projectDao.queryBuilder()
            .where(ProjectDao.Properties.ProjectTeamName.eq(projectTeamName)//工程队名
                    , ProjectDao.Properties.StartTime.gt(startTime)//大于等于开始日期的
            ,ProjectDao.Properties.CompleteState.notEq("进行中"));//已完成和已超时的

    if (!TextUtils.isEmpty(workname)) {
      where = where.where(ProjectDao.Properties.WorkName.like(workname));//作业名称
    }
    List<Project> projectList = where.build().list();

    historyWorkFragment.success(projectList);
  }
}
