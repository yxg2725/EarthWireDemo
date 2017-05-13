package com.huadin.earthwire.Model.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 华电 on 2017/5/12.
 */
@Entity
public class Project {
  @Id
  private Long id;
  private String workName;//作业名称
  private String projectTeamName;//工程队名称
  private String projectTeamHeadName;//工程队负责人
  private String startTime;// 开始时间
  private long startTimeMillis;// 开始时间long值
  private String worker;// 创建人
  private String completeState;// 完成状态
  public String getCompleteState() {
    return this.completeState;
  }
  public void setCompleteState(String completeState) {
    this.completeState = completeState;
  }
  public String getWorker() {
    return this.worker;
  }
  public void setWorker(String worker) {
    this.worker = worker;
  }
  public long getStartTimeMillis() {
    return this.startTimeMillis;
  }
  public void setStartTimeMillis(long startTimeMillis) {
    this.startTimeMillis = startTimeMillis;
  }
  public String getStartTime() {
    return this.startTime;
  }
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
  public String getProjectTeamHeadName() {
    return this.projectTeamHeadName;
  }
  public void setProjectTeamHeadName(String projectTeamHeadName) {
    this.projectTeamHeadName = projectTeamHeadName;
  }
  public String getProjectTeamName() {
    return this.projectTeamName;
  }
  public void setProjectTeamName(String projectTeamName) {
    this.projectTeamName = projectTeamName;
  }
  public String getWorkName() {
    return this.workName;
  }
  public void setWorkName(String workName) {
    this.workName = workName;
  }
  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Generated(hash = 1115700498)
  public Project(Long id, String workName, String projectTeamName,
      String projectTeamHeadName, String startTime, long startTimeMillis,
      String worker, String completeState) {
    this.id = id;
    this.workName = workName;
    this.projectTeamName = projectTeamName;
    this.projectTeamHeadName = projectTeamHeadName;
    this.startTime = startTime;
    this.startTimeMillis = startTimeMillis;
    this.worker = worker;
    this.completeState = completeState;
  }
  @Generated(hash = 1767516619)
  public Project() {
  }


}
