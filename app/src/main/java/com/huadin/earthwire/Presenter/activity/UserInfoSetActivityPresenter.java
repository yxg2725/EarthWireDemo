package com.huadin.earthwire.Presenter.activity;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.View.activity.UserInfoSetActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class UserInfoSetActivityPresenter {
  private final UserInfoSetActivity userInfoSetActivity;

  public UserInfoSetActivityPresenter(UserInfoSetActivity userInfoSetActivity) {

    this.userInfoSetActivity = userInfoSetActivity;
  }

  public void saveToDB(String name, int projectTeamId, String projectTeam, String projectHead, String headPhone) {
    Person person = new Person();
    person.setUserName(name);
    person.setProjectTeam(projectTeam);
    person.setProjectTeamId(projectTeamId);
    person.setProjectTeamHead(projectHead);
    person.setProjectTeamHeadPhone(headPhone);

    DaoManager.getInstance().getDaoSession().getPersonDao().insert(person);
    userInfoSetActivity.success(null);
  }

  public void queryInfo() {
    List<Person> persons = DaoManager.getInstance().getDaoSession().getPersonDao().loadAll();
    if(persons != null && persons.size()>0){
      userInfoSetActivity.queryComplete(persons.get(persons.size()-1));
    }

  }
}
