package com.huadin.earthwire.View.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.huadin.earthwire.R;

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
  private String projectname;

  public FilterDialog(Context context) {
    super(context);
  }

  @Override
  public int getLayoutId() {
    return R.layout.dialog_filter;
  }

  @OnClick({R.id.btn_start, R.id.btn_search})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_start://获取开始时间
        startTime = "";
        break;
      case R.id.btn_search:
        //获取工程名
        projectname = "";
        //获取作业名称
        String workname = etWorkName.getText().toString();

        if(onSearchListener != null){
          onSearchListener.onsearchCallback(startTime,projectname,workname);
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
