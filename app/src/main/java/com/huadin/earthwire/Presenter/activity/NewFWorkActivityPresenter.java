package com.huadin.earthwire.Presenter.activity;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.View.activity.NewFWorkActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class NewFWorkActivityPresenter {
  private final NewFWorkActivity newFWorkActivity;

  public NewFWorkActivityPresenter(NewFWorkActivity newFWorkActivity) {

    this.newFWorkActivity = newFWorkActivity;
  }

  public void queryInfo() {

    List<Person> persons = DaoManager.getInstance().getDaoSession().getPersonDao().loadAll();
    if(persons.size() > 0){
      newFWorkActivity.success(persons.get(persons.size()-1));
    }else{
      newFWorkActivity.success(null);
    }
  }
}
