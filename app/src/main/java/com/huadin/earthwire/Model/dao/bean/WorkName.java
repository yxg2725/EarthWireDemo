package com.huadin.earthwire.Model.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 华电 on 2017/5/11.
 */
@Entity
public class WorkName {

    @Id
    private Long id;
    private String workName;// 作业名称
    private String startTime;// 开始时间
    private String planTime;// 预计结束时间
    private String endTime;// 实际结束时间
    private String currentState;// 当前状态
    private String projectName;// 工程队名称
    private String projectHead;// 工程队负责人
    private String projectHeadPhone;// 工程队负责人电话
    private String worker;// 作者

    public String getWorker() {
        return this.worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getProjectHeadPhone() {
        return this.projectHeadPhone;
    }

    public void setProjectHeadPhone(String projectHeadPhone) {
        this.projectHeadPhone = projectHeadPhone;
    }

    public String getProjectHead() {
        return this.projectHead;
    }

    public void setProjectHead(String projectHead) {
        this.projectHead = projectHead;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlanTime() {
        return this.planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    @Generated(hash = 1976446036)
    public WorkName(Long id, String workName, String startTime, String planTime,
                    String endTime, String currentState, String projectName,
                    String projectHead, String projectHeadPhone, String worker) {
        this.id = id;
        this.workName = workName;
        this.startTime = startTime;
        this.planTime = planTime;
        this.endTime = endTime;
        this.currentState = currentState;
        this.projectName = projectName;
        this.projectHead = projectHead;
        this.projectHeadPhone = projectHeadPhone;
        this.worker = worker;
    }

    @Generated(hash = 1008777422)
    public WorkName() {
    }

}
