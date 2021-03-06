package com.huadin.earthwire.View.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.DateUtil;

import java.util.Calendar;

/**
 * Created by 华电 on 2017/5/13.
 */

public class DateTimePickDialogUtil implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {
  private DatePicker datePicker;
  private TimePicker timePicker;
  private AlertDialog ad;
  private String dateTime;
  private long initDateTime;
  private Activity activity;
  private boolean isSetMinDate;

  /**
   * 日期时间弹出选择框构造函数
   *
   * @param activity
   *            ：调用的父activity
   * @param initDateTime
   *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
   */
  public DateTimePickDialogUtil(Activity activity, long initDateTime,boolean isSetMinDate) {
    this.activity = activity;
    this.initDateTime = initDateTime;
    this.isSetMinDate = isSetMinDate;
  }

  public void init(DatePicker datePicker, TimePicker timePicker) {
    Calendar calendar = Calendar.getInstance();
    if (initDateTime > 0) {
      calendar.setTimeInMillis(initDateTime);
    } else {
      initDateTime = System.currentTimeMillis();
    }

    if(isSetMinDate){
      datePicker.setMinDate(initDateTime);
    }
    datePicker.init(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), this);
    timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
    timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
  }

  /**
   * 弹出日期时间选择框方法
   *
   * @param inputDate
   *            :为需要设置的日期时间文本编辑框
   * @return
   */
  public AlertDialog dateTimePicKDialog(final TextView inputDate) {
    LinearLayout dateTimeLayout = (LinearLayout) activity
            .getLayoutInflater().inflate(R.layout.common_datetime, null);
    datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
    timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);

   /* ViewGroup.LayoutParams layoutParams = datePicker.getLayoutParams();

    layoutParams.height = 90;

    datePicker.setLayoutParams(layoutParams);*/

    //是否隐藏时间选择器
    if(!isSetMinDate){
      timePicker.setVisibility(View.GONE);
    }
    init(datePicker, timePicker);
    timePicker.setIs24HourView(true);
    timePicker.setOnTimeChangedListener(this);

    ad = new AlertDialog.Builder(activity)
            .setTitle(DateUtil.toymdhms(initDateTime))
            .setView(dateTimeLayout)
            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                ad.dismiss();
                inputDate.setText(dateTime);

              }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                inputDate.setText("");
                ad.dismiss();
              }
            }).show();

    onDateChanged(null, 0, 0, 0);
    return ad;
  }

  public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
    onDateChanged(null, 0, 0, 0);
  }

  public void onDateChanged(DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) {
    // 获得日历实例
    Calendar calendar = Calendar.getInstance();

    if (!isSetMinDate){//查询历史记录
      calendar.set(datePicker.getYear(), datePicker.getMonth(),
              datePicker.getDayOfMonth(),0,0,0);
      dateTime = DateUtil.timestamp2ymd(calendar.getTimeInMillis());
    }else{//设置最小日期  为 设置预计结束时间
      calendar.set(datePicker.getYear(), datePicker.getMonth(),
              datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
              timePicker.getCurrentMinute());
      dateTime = DateUtil.toymdhms(calendar.getTimeInMillis());
    }

    ad.setTitle(dateTime);
  }
}
