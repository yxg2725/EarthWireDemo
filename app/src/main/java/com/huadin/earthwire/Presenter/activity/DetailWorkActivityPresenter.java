package com.huadin.earthwire.Presenter.activity;

import android.content.Intent;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.EarthWireDao;
import com.huadin.earthwire.Model.dao.bean.EarthWire;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.View.activity.DetailWorkActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class DetailWorkActivityPresenter {
  private final DetailWorkActivity detailWorkActivity;

  public DetailWorkActivityPresenter(DetailWorkActivity detailWorkActivity) {

    this.detailWorkActivity = detailWorkActivity;
  }

  public void queryInfo(String workName) {
    List<EarthWire> list = DaoManager.getInstance().getDaoSession().getEarthWireDao().queryBuilder()
            .where(EarthWireDao.Properties.ProjectName.eq(workName)).build().list();

    detailWorkActivity.success(list);
  }

  public void saveInfo(int requestCode, int resultCode, Intent data, String workName) {
    if (requestCode == DetailWorkActivity.REQUEST_NEW_EARTHWIRE){
      if (data != null){
        String planEndTime = data.getStringExtra("planEndTime");
        long planEndTimeMillis = DateUtil.convert2long(planEndTime, "yyyy-MM-dd HH:mm:ss");
        String gpsStyleID = data.getStringExtra("gpsStyleID");
        String gpsStyle = data.getStringExtra("gpsStyle");
        String gpsIMEi = data.getStringExtra("gpsIMEi");
        String locationInfo = data.getStringExtra("locationInfo");
        long startTimeMillis = System.currentTimeMillis();//开始时间

        EarthWire earthWire = new EarthWire();
        earthWire.setStartTimeMillis(startTimeMillis);
        earthWire.setPlanEndTime(planEndTimeMillis);
        earthWire.setGpsStyleID(gpsStyleID);
        earthWire.setGpsStyle(gpsStyle);
        earthWire.setGpsIMEi(gpsIMEi);
        earthWire.setLocationInfo(locationInfo);
        earthWire.setProjectName(workName);
        earthWire.setCurrentState("进行中");

        DaoManager.getInstance().getDaoSession().getEarthWireDao().insert(earthWire);
      }

    }
  }
}
