package com.huadin.earthwire.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.EarthWireDao;
import com.huadin.earthwire.Model.dao.bean.EarthWire;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.View.activity.DetailWorkActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class ProjectDetailAdapter extends RecyclerView.Adapter {

  private final DetailWorkActivity activity;
  private final List<EarthWire> earthWireList;
  private int postion;


  public ProjectDetailAdapter(DetailWorkActivity activity, List<EarthWire> earthWireList) {
    this.activity = activity;
    this.earthWireList = earthWireList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MyViewHolder myHolder = (MyViewHolder) holder;
    myHolder.setItem(position);
    this.postion = position;
  }

  @Override
  public int getItemCount() {
    return earthWireList == null ? 0: earthWireList.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private final TextView mTvCountDown;
    TextView mEtExpectTime;
    TextView mEtRealityTime;
    TextView mBtnReality;
    TextView mTvCurrentState;
    TextView mTv_title;
    TextView mEtStartTime;
    private long planEndTimeMillis;
    private String projectName;

    public MyViewHolder(View itemView) {
      super(itemView);
      mEtStartTime = (TextView) itemView.findViewById(R.id.et_start_time);
      mEtExpectTime = (TextView) itemView.findViewById(R.id.et_expect_time);
      mEtRealityTime = (TextView) itemView.findViewById(R.id.et_reality_time);
      mBtnReality = (TextView) itemView.findViewById(R.id.btn_reality);
      mTvCurrentState = (TextView) itemView.findViewById(R.id.tv_current_state);
      mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
      mTvCountDown = (TextView) itemView.findViewById(R.id.tv_count_down);//倒计时

    }

    public void setItem(int position) {
      projectName = earthWireList.get(position).getProjectName();//工程名
      long startTimeMillis = earthWireList.get(position).getStartTimeMillis();
      String startTime = DateUtil.toymdhms(startTimeMillis);
      long realEndTime = earthWireList.get(position).getRealEndTime();

      planEndTimeMillis = earthWireList.get(position).getPlanEndTime();
      String planEndTime = DateUtil.toymdhms(planEndTimeMillis);
      long len = planEndTimeMillis - startTimeMillis;
      String counDown = "";
      if(len < 24*60*60*1000){
         counDown = DateUtil.tohms(len);
      }else{
        counDown = DateUtil.toymdhms(len);
      }

      String currentState = earthWireList.get(position).getCurrentState();


      mEtStartTime.setText(startTime);
      mEtExpectTime.setText(planEndTime);
      mTvCurrentState.setText(currentState);
      mTvCountDown.setText(counDown);
      if(realEndTime > 0){
        mEtRealityTime.setText(DateUtil.toymdhms(realEndTime));
      }
      mTv_title.setText("地线" + (position+1));


      mBtnReality.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.btn_reality://结束时间
          long endTimeMillis = System.currentTimeMillis();
          String realEndTime = DateUtil.tohms(endTimeMillis);
          mEtRealityTime.setText(realEndTime);
          String currentState = "进行中";
          if(endTimeMillis - planEndTimeMillis <= 0){//未超时
            currentState = "已完成";
          }else{//超时
            currentState = "已超时";
          }
          mTvCurrentState.setText(currentState);
          //更新数据库
          List<EarthWire> list = DaoManager.getInstance().getDaoSession().getEarthWireDao()
                  .queryBuilder()
                  .where(EarthWireDao.Properties.ProjectName.eq(projectName))
                  .build().list();
          EarthWire earthWire = list.get(postion);
          earthWire.setCurrentState(currentState);
          earthWire.setRealEndTime(endTimeMillis);
          DaoManager.getInstance().getDaoSession().getEarthWireDao().update(earthWire);

          break;
      }
    }
  }
}
