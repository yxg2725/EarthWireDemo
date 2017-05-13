package com.huadin.earthwire.Model.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 华电 on 2017/5/13.
 */

@Entity
public class EarthWire {
  @Id
  private Long id;
  private long startTimeMillis;//开始时间
  private long planEndTime;//计划结束时间
  private long realEndTime;//实际结束时间
  private String gpsStyleID;//gps模式id
  private String gpsStyle;//gps模式
  private String gpsIMEi;//gpsIMEI
  private String locationInfo;//坐标
  private String currentState;//状态
  private String projectName;//所属工程名
  private String earthWireId;//地线编号
  public String getEarthWireId() {
    return this.earthWireId;
  }
  public void setEarthWireId(String earthWireId) {
    this.earthWireId = earthWireId;
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
  public String getLocationInfo() {
    return this.locationInfo;
  }
  public void setLocationInfo(String locationInfo) {
    this.locationInfo = locationInfo;
  }
  public String getGpsIMEi() {
    return this.gpsIMEi;
  }
  public void setGpsIMEi(String gpsIMEi) {
    this.gpsIMEi = gpsIMEi;
  }
  public String getGpsStyle() {
    return this.gpsStyle;
  }
  public void setGpsStyle(String gpsStyle) {
    this.gpsStyle = gpsStyle;
  }
  public String getGpsStyleID() {
    return this.gpsStyleID;
  }
  public void setGpsStyleID(String gpsStyleID) {
    this.gpsStyleID = gpsStyleID;
  }
  public long getRealEndTime() {
    return this.realEndTime;
  }
  public void setRealEndTime(long realEndTime) {
    this.realEndTime = realEndTime;
  }
  public long getPlanEndTime() {
    return this.planEndTime;
  }
  public void setPlanEndTime(long planEndTime) {
    this.planEndTime = planEndTime;
  }
  public long getStartTimeMillis() {
    return this.startTimeMillis;
  }
  public void setStartTimeMillis(long startTimeMillis) {
    this.startTimeMillis = startTimeMillis;
  }
  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Generated(hash = 1663600335)
  public EarthWire(Long id, long startTimeMillis, long planEndTime,
      long realEndTime, String gpsStyleID, String gpsStyle, String gpsIMEi,
      String locationInfo, String currentState, String projectName,
      String earthWireId) {
    this.id = id;
    this.startTimeMillis = startTimeMillis;
    this.planEndTime = planEndTime;
    this.realEndTime = realEndTime;
    this.gpsStyleID = gpsStyleID;
    this.gpsStyle = gpsStyle;
    this.gpsIMEi = gpsIMEi;
    this.locationInfo = locationInfo;
    this.currentState = currentState;
    this.projectName = projectName;
    this.earthWireId = earthWireId;
  }
  @Generated(hash = 224447731)
  public EarthWire() {
  }
  
}
