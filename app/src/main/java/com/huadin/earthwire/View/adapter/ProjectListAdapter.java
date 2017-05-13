package com.huadin.earthwire.View.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.DetailWorkActivity;
import com.huadin.earthwire.View.activity.StartWorkActivity;

import java.util.List;

/**
 * Created by 华电 on 2017/5/13.
 */

public class ProjectListAdapter extends RecyclerView.Adapter {

  private final Activity activity;
  private final List<Project> list;
  public ProjectListAdapter(Activity activity, List<Project> list) {
    this.activity = activity;
    this.list = list;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MyViewHolder myHolder = (MyViewHolder) holder;
    myHolder.setItem(position);
  }

  @Override
  public int getItemCount() {
    return list == null?0:list.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mTvProjectName;//工程名
    TextView mTvProjectTeamName;//工程队名称
    TextView mTvCreatePeople;//创建人
    TextView mTvStartTime;//开始时间
    private String workName;
    private String startTime;
    private String projectTeamName;
    private String worker;

    public MyViewHolder(View itemView) {
      super(itemView);
      mTvProjectName = (TextView) itemView.findViewById(R.id.tv_project_name);//工程名
      mTvStartTime = (TextView) itemView.findViewById(R.id.tv_start_time);//开始时间
      mTvProjectTeamName = (TextView) itemView.findViewById(R.id.tv_project_team_name);//工程队名称
      mTvCreatePeople = (TextView) itemView.findViewById(R.id.tv_create_people);//创建人

    }

    public void setItem(int position) {
      workName = list.get(position).getWorkName();
      startTime = list.get(position).getStartTime();
      projectTeamName = list.get(position).getProjectTeamName();
      worker = list.get(position).getWorker();

      mTvProjectName.setText(workName);//工程名
      mTvStartTime.setText(startTime);//开始时间
      mTvProjectTeamName.setText(projectTeamName);//工程队名称
      mTvCreatePeople.setText(worker);//创建人
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Intent intent = new Intent(activity, DetailWorkActivity.class);
      intent.putExtra("workName",workName);
      activity.startActivity(intent);
    }
  }
}
