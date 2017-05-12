package com.huadin.earthwire.Presenter.fragment;

import android.text.TextUtils;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.DaoSession;
import com.huadin.earthwire.Model.dao.WorkNameDao;
import com.huadin.earthwire.Model.dao.bean.WorkName;
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

  public void query(String startTime, String projectname, String workname) {
    //查询数据库
    DaoSession daoSession = DaoManager.getInstance().getDaoSession();
    WorkNameDao workNameDao = daoSession.getWorkNameDao();
    QueryBuilder<WorkName> where = workNameDao.queryBuilder()
            .where(WorkNameDao.Properties.ProjectName.eq(projectname)//工程队名
                    , WorkNameDao.Properties.StartTime.eq(startTime));//开始时间
    if (!TextUtils.isEmpty(workname)) {
      where = where.where(WorkNameDao.Properties.WorkName.like(workname));//作业名称
    }
    List<WorkName> workNameList = where.build().list();
    if (workNameList != null && workNameList.size() > 0) {
      historyWorkFragment.success(workNameList);
    }
  }
}
