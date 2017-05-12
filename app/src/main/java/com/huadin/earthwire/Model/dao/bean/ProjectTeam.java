package com.huadin.earthwire.Model.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 华电 on 2017/5/11.
 */

@Entity
public class ProjectTeam {
  @Id
  private Long id;
  private String member;//成员人名
  private String projectName;//工程队名
  private String memberPhone;//成员电话
  public String getProjectName() {
    return this.projectName;
  }
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  public String getMember() {
    return this.member;
  }
  public void setMember(String member) {
    this.member = member;
  }
  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getMemberPhone() {
    return this.memberPhone;
  }
  public void setMemberPhone(String memberPhone) {
    this.memberPhone = memberPhone;
  }
  @Generated(hash = 253270602)
  public ProjectTeam(Long id, String member, String projectName,
      String memberPhone) {
    this.id = id;
    this.member = member;
    this.projectName = projectName;
    this.memberPhone = memberPhone;
  }
  @Generated(hash = 1463436963)
  public ProjectTeam() {
  }
}
