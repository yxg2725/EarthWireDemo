package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.View.widget.DateTimePickDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 华电 on 2017/5/13.
 */

public class NewEarthWireActivity extends BaseActivity {
  public static final int RESULT_NEW_EARTHWIRE = 1;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.tv_plan_end_time)
  TextView mTvPlanEndTime;
  @BindView(R.id.ib_end_time)
  ImageButton mIbEndTime;
  @BindView(R.id.sp_gps_style)
  Spinner mSpGpsStyle;
  @BindView(R.id.et_gps_imei)
  EditText mEtGpsImei;
  @BindView(R.id.ib_scan)
  ImageButton mIbScan;
  @BindView(R.id.et_location)
  TextView mTvLocation;
  @BindView(R.id.ib_location)
  ImageButton mIbLocation;

  @Override
  public int getlayoutId() {
    return R.layout.activity_new_earthwire;
  }

  @Override
  protected void initView() {
    initToolBar(mToolbar,true,"新建地线");
  }

  @Override
  protected void initlistener() {

  }

  @Override
  protected void initData() {

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_tool_bar_button,menu);
    menu.findItem(R.id.title_right_button).setTitle("完成");
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == R.id.title_right_button){
      String planEndTime = mTvPlanEndTime.getText().toString();
      int gpsStyleID = mSpGpsStyle.getSelectedItemPosition();
      String gpsStyle = Constant.GPSSTYLE[gpsStyleID];
      String gpsIMEi = mEtGpsImei.getText().toString();
      String locationInfo = mTvLocation.getText().toString();

      Intent intent = new Intent();
      intent.putExtra("planEndTime",planEndTime);
      intent.putExtra("gpsStyleID",gpsStyleID);
      intent.putExtra("gpsStyle",gpsStyle);
      intent.putExtra("gpsIMEi",gpsIMEi);
      intent.putExtra("locationInfo",locationInfo);

      setResult(RESULT_NEW_EARTHWIRE,intent);
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick({R.id.ib_end_time, R.id.ib_scan, R.id.ib_location})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.ib_end_time:
        new DateTimePickDialogUtil(this,0).dateTimePicKDialog(mTvPlanEndTime);//默认为当前时间
        break;
      case R.id.ib_scan:
        break;
      case R.id.ib_location:
        break;
    }
  }
}
