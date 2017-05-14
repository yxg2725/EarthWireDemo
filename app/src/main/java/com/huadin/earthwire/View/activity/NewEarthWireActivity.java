package com.huadin.earthwire.View.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.Utils.LogUtils;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.View.widget.DateTimePickDialogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 华电 on 2017/5/13.
 */

public class NewEarthWireActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
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
  @BindView(R.id.ll_gps)
  LinearLayout mLlGps;

  private static BDLocation location;

  @Override
  public int getlayoutId() {
    return R.layout.activity_new_earthwire;
  }

  @Override
  protected void initView() {
    EventBus.getDefault().register(this);
    initToolBar(mToolbar,true,"新建地线");
  }

  @Override
  protected void initlistener() {
    mSpGpsStyle.setOnItemSelectedListener(this);
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
        DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(this, 0,true);
        dateTimePickDialogUtil.dateTimePicKDialog(mTvPlanEndTime);//默认为当前时间
        break;
      case R.id.ib_scan:
        break;
      case R.id.ib_location:
        //展示坐标信息
        if(location != null){
          mTvLocation.setText(location.getLongitude() + "," + location.getLatitude());
        }
        break;
    }
  }

  @Subscribe
  public void getLocationEvent(BDLocation location){
    this.location = location;
    LogUtils.logi(this.getClass(),location.getLongitude() + "," + location.getLatitude());
  }


  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    if (position == 0){
      mLlGps.setVisibility(View.VISIBLE);
    }else{
      mLlGps.setVisibility(View.GONE);
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
