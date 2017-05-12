package com.huadin.earthwire.Model.dao.bean;

import java.io.Serializable;

/**
 * Created by 华电 on 2017/5/12.
 */

public class Project implements Serializable{
  private String workName;//作业名称
  private String projectHead;//工程队负责人
  private String headPhone;//工程队负责人电话

  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }

  public String getProjectHead() {
    return projectHead;
  }

  public void setProjectHead(String projectHead) {
    this.projectHead = projectHead;
  }

  public String getHeadPhone() {
    return headPhone;
  }

  public void setHeadPhone(String headPhone) {
    this.headPhone = headPhone;
  }
}
