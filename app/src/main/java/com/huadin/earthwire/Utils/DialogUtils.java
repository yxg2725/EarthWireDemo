package com.huadin.earthwire.Utils;

import android.app.Activity;
import android.content.Context;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by 华电 on 2017/4/27.
 */

public class DialogUtils {

  private static TimePickerDialog tpd;

  public static void showTimePickerDialog(Activity context, final TimeSelectListener timeListener){
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(System.currentTimeMillis());
    tpd = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        if (timeListener != null){
          timeListener.onSetTime(view, hourOfDay, minute,second);
        }
      }
    },now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),now.get(Calendar.SECOND),true);
    tpd.setThemeDark(true);
    tpd.show(context.getFragmentManager(),"time_dialog");
  }

  private  static TimeSelectListener timeListener;
  public interface TimeSelectListener{
    void onSetTime(TimePickerDialog view, int hourOfDay, int minute, int second);
  }

  public void setOnTimeSelectListener(TimeSelectListener timeListener){
    this.timeListener = timeListener;
  }
}
