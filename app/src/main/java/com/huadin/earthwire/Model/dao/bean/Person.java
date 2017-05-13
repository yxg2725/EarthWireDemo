package com.huadin.earthwire.Model.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 华电 on 2017/5/12.
 */

@Entity
public class Person {
  @Id
  private Long id;
  private String userName;// 使用人
  private String projectTeam;// 工程队
  private int projectTeamId;// 工程队Id
  private String projectTeamHead;// 工程队负责人
  private String projectTeamHeadPhone;// 工程队负责人电话
  public String getProjectTeamHeadPhone() {
    return this.projectTeamHeadPhone;
  }
  public void setProjectTeamHeadPhone(String projectTeamHeadPhone) {
    this.projectTeamHeadPhone = projectTeamHeadPhone;
  }
  public String getProjectTeamHead() {
    return this.projectTeamHead;
  }
  public void setProjectTeamHead(String projectTeamHead) {
    this.projectTeamHead = projectTeamHead;
  }
  public int getProjectTeamId() {
    return this.projectTeamId;
  }
  public void setProjectTeamId(int projectTeamId) {
    this.projectTeamId = projectTeamId;
  }
  public String getProjectTeam() {
    return this.projectTeam;
  }
  public void setProjectTeam(String projectTeam) {
    this.projectTeam = projectTeam;
  }
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Generated(hash = 1847068354)
  public Person(Long id, String userName, String projectTeam, int projectTeamId,
      String projectTeamHead, String projectTeamHeadPhone) {
    this.id = id;
    this.userName = userName;
    this.projectTeam = projectTeam;
    this.projectTeamId = projectTeamId;
    this.projectTeamHead = projectTeamHead;
    this.projectTeamHeadPhone = projectTeamHeadPhone;
  }
  @Generated(hash = 1024547259)
  public Person() {
  }
  
  
}
