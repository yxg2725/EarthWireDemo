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
    private String projectName;//工程队名
    private String projectHeadName;//工程队负责人名
    private String projectHeadPhone;//工程队负责人电话
    public String getProjectHeadPhone() {
        return this.projectHeadPhone;
    }
    public void setProjectHeadPhone(String projectHeadPhone) {
        this.projectHeadPhone = projectHeadPhone;
    }
    public String getProjectHeadName() {
        return this.projectHeadName;
    }
    public void setProjectHeadName(String projectHeadName) {
        this.projectHeadName = projectHeadName;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1758484694)
    public ProjectTeam(Long id, String projectName, String projectHeadName,
            String projectHeadPhone) {
        this.id = id;
        this.projectName = projectName;
        this.projectHeadName = projectHeadName;
        this.projectHeadPhone = projectHeadPhone;
    }
    @Generated(hash = 1463436963)
    public ProjectTeam() {
    }


}
