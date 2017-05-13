package com.huadin.earthwire.Presenter.activity;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.View.activity.MainActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class MainActivityPresenter {
  private final MainActivity mainActivity;

  public MainActivityPresenter(MainActivity mainActivity) {

    this.mainActivity = mainActivity;
  }

  public void queryPersonInfo() {
    List<Person> persons = DaoManager.getInstance().getDaoSession().getPersonDao().loadAll();
    if (persons != null ){
      if(persons.size()>0){
        mainActivity.success(persons.get(persons.size()-1));
      }else{
        mainActivity.success(null);
      }

    }
  }
}
