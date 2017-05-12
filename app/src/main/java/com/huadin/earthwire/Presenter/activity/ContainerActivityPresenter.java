package com.huadin.earthwire.Presenter.activity;

import android.text.TextUtils;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.DaoSession;
import com.huadin.earthwire.Model.dao.WorkNameDao;
import com.huadin.earthwire.Model.dao.bean.WorkName;
import com.huadin.earthwire.View.activity.ContainerActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 华电 on 2017/5/12.
 */

public class ContainerActivityPresenter {
  private final ContainerActivity containerActivity;
  public ContainerActivityPresenter(ContainerActivity containerActivity) {
    this.containerActivity = containerActivity;
  }

  public void query(String startTime, String projectname, String workname) {
    //查询数据库
    DaoSession daoSession = DaoManager.getInstance().getDaoSession();
    WorkNameDao workNameDao = daoSession.getWorkNameDao();
    QueryBuilder<WorkName> where = workNameDao.queryBuilder()
            .where(WorkNameDao.Properties.ProjectName.eq(projectname)//工程队名
                    , WorkNameDao.Properties.StartTime.eq(startTime));//开始时间
    if(!TextUtils.isEmpty(workname)){
      where = where.where(WorkNameDao.Properties.WorkName.like(workname));//作业名称
    }
    List<WorkName> workNameList = where.build().list();
    if(workNameList != null && workNameList.size()>0){
      containerActivity.success(workNameList);
    }
  }
}
