package com.huadin.earthwire.View.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.baidu.location.BDLocation;
import com.huadin.earthwire.Model.dao.bean.Project;
import com.huadin.earthwire.Presenter.fragment.HistoryFragmentPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.DetailWorkActivity;
import com.huadin.earthwire.View.activity.MainActivity;
import com.huadin.earthwire.View.adapter.ProjectListAdapter;
import com.huadin.earthwire.View.base.BaseFragment;
import com.huadin.earthwire.View.widget.FilterDialog;
import com.huadin.earthwire.dagger.conponent.DaggerFragmentConponent;
import com.huadin.earthwire.dagger.module.FragmentModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Jack Zhang on 2017/5/11.
 */

public class HistoryWorkFragment extends BaseFragment implements ProjectListAdapter.OnToDetailListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @Inject
    HistoryFragmentPresenter historyFragmentPresenter;
    private ProgressDialog dialog;
    private List<Project> projectList = new ArrayList<>();
    private ProjectListAdapter mAdater;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_history_work;
    }

    @Override
    public void initView() {

        DaggerFragmentConponent.builder()
                .fragmentModule(new FragmentModule(this)).build().in(this);

        dialog = new ProgressDialog(activity);
        setHasOptionsMenu(true);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(activity));

        if (mAdater == null ){
          mAdater = new ProjectListAdapter(activity,projectList);
          mRecyclerview.setAdapter(mAdater);
        }else{
          mAdater.notifyDataSetChanged();
        }

      mAdater.setOnToDetailListener(this);
    }

    @Override
    public void initData() {
        stateLayout.showContentView();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).mToolbar.setTitle("历史作业");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_tool_bar_button,menu);
        menu.findItem(R.id.title_right_button).setTitle("筛选");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.title_right_button){
            //弹出对话框
            final FilterDialog filterDialog = new FilterDialog(activity);
            filterDialog.setOnSerachListener(new FilterDialog.OnSearchListener() {
                @Override
                public void onsearchCallback(String startTime, String projectname, String workname) {
                    filterDialog.dismiss();
                    dialog.show();
                    historyFragmentPresenter.query(startTime,projectname,workname);
                }
            });
            filterDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void success(Object o) {
        dialog.dismiss();
        List<Project> list = (List<Project>) o;
        projectList.clear();
        projectList.addAll(list);

        if (mAdater == null ){
            mAdater = new ProjectListAdapter(activity,projectList);
            mRecyclerview.setAdapter(mAdater);
        }else{
            mAdater.notifyDataSetChanged();
        }

    }

    @Override
    public void failed(String msg) {

    }

  @Override
  public void onToDetailListenerCallBack(String workname) {
    Intent intent = new Intent(activity, DetailWorkActivity.class);
    intent.putExtra("workname",workname);
    intent.putExtra("tag",this.getClass().getSimpleName());
    startActivity(intent);
  }

}
