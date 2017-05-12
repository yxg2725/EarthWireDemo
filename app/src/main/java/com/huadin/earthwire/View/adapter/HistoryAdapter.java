package com.huadin.earthwire.View.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huadin.earthwire.Model.dao.bean.WorkName;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.DateUtil;
import com.huadin.earthwire.Utils.DialogUtils;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.List;

/**
 * Created by 华电 on 2017/5/12.
 */

public class HistoryAdapter extends RecyclerView.Adapter {

  private final Activity activity;
  private List<WorkName> workNameList;
  public HistoryAdapter(Activity activity,List<WorkName> workNameList) {
    this.activity = activity;
    this.workNameList = workNameList;
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
  }

  @Override
  public int getItemCount() {
    return workNameList == null?0:workNameList.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mBtnStart;
    TextView mEtExpectTime;
    TextView mBtnExpect;
    TextView mEtRealityTime;
    TextView mBtnReality;
    TextView mTvCurrentState;
    TextView mTv_title;
    TextView mEtStartTime;

    public MyViewHolder(View itemView) {
      super(itemView);
      mEtStartTime = (TextView) itemView.findViewById(R.id.et_start_time);
      mBtnStart = (TextView) itemView.findViewById(R.id.btn_start);
      mEtExpectTime = (TextView) itemView.findViewById(R.id.et_expect_time);
      mBtnExpect = (TextView) itemView.findViewById(R.id.btn_expect);
      mEtRealityTime = (TextView) itemView.findViewById(R.id.et_reality_time);
      mBtnReality = (TextView) itemView.findViewById(R.id.btn_reality);
      mTvCurrentState = (TextView) itemView.findViewById(R.id.tv_current_state);
      mTv_title = (TextView) itemView.findViewById(R.id.tv_title);

    }

    public void setItem(int position) {
      mBtnStart.setOnClickListener(this);
      mBtnExpect.setOnClickListener(this);
      mBtnReality.setOnClickListener(this);

      mTv_title.setText("地线" + (position + 1));
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.btn_start://开始时间
          String startTime = DateUtil.tohms(System.currentTimeMillis());
          mEtStartTime.setText(startTime);
          break;
        case R.id.btn_expect://预计时间

          DialogUtils.showTimePickerDialog(activity, new DialogUtils.TimeSelectListener() {
            @Override
            public void onSetTime(TimePickerDialog view, int hourOfDay, int minute, int second) {
              String hourResult = String.format("%02d", hourOfDay);
              String minuteResult = String.format("%02d", minute);
              String secondResult = String.format("%02d", second);
              mEtExpectTime.setText(hourResult + ":" + minuteResult + ":" + secondResult);
            }
          });
          break;
        case R.id.btn_reality://结束时间
          String realEndTime = DateUtil.tohms(System.currentTimeMillis());
          mEtRealityTime.setText(realEndTime);
          break;
      }
    }
  }
}