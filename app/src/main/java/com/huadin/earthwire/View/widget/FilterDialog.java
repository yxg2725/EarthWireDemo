package com.huadin.earthwire.View.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.Constant;
import com.huadin.earthwire.Utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lxj on 2016/9/15.
 */
public class FilterDialog extends BaseDialog {
  @BindView(R.id.et_start_time)
  TextView etStartTime;
  @BindView(R.id.btn_start)
  TextView btnStart;
  @BindView(R.id.sp_Project_team_name)
  Spinner spProjectTeamName;
  @BindView(R.id.et_work_name)
  EditText etWorkName;
  @BindView(R.id.btn_search)
  Button btnSearch;
  private String startTime;
  private Activity context;

  public FilterDialog(Activity context) {
    super(context);
    this.context = context;
  }

  @Override
  public int getLayoutId() {
    return R.layout.dialog_filter;
  }

  @OnClick({R.id.btn_start, R.id.btn_search})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_start://获取开始时间
        DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(context, 0,false);
        dateTimePickDialogUtil.dateTimePicKDialog(etStartTime);
        break;
      case R.id.btn_search:

        startTime = etStartTime.getText().toString();
        LogUtils.logi(this.getClass(),startTime);
        //获取作业名称
        String workname = etWorkName.getText().toString();
        int selectedID = spProjectTeamName.getSelectedItemPosition();
        String projectTeamName = Constant.PROJECT_TEAM_NAMES[selectedID];

        if(onSearchListener != null){
          onSearchListener.onsearchCallback(startTime,projectTeamName,workname);
        }
        break;
    }
  }


  //设置回调
  private OnSearchListener  onSearchListener;
  public interface OnSearchListener{
    void onsearchCallback(String startTime,String projectname,String workname);
  }
  public void setOnSerachListener(OnSearchListener  onSearchListener){
    this.onSearchListener = onSearchListener;
  }
}
