package com.huadin.earthwire.Presenter.fragment;

import android.text.TextUtils;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.DaoSession;
import com.huadin.earthwire.Model.dao.ProjectDao;
import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.Utils.LogUtils;
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

    long startDate = DateUtil.convert2long(startTime, "yyyy-MM-dd HH:mm:ss");
    //查询数据库
    DaoSession daoSession = DaoManager.getInstance().getDaoSession();
    ProjectDao projectDao = daoSession.getProjectDao();
    List<Project> projects = projectDao.loadAll();
    LogUtils.logi(this.getClass(),projects.size() +"");


    QueryBuilder<Project> where = projectDao.queryBuilder()
            .where(ProjectDao.Properties.ProjectTeamName.eq(projectTeamName)
                    , ProjectDao.Properties.StartTimeMillis.ge(startDate)
                    , ProjectDao.Properties.CompleteState.notEq("进行中"));

    if (!TextUtils.isEmpty(workname)) {
      where = where.where(ProjectDao.Properties.WorkName.like(workname));//作业名称
    }
    List<Project> projectList = where.build().list();

    historyWorkFragment.success(projectList);
  }
}
