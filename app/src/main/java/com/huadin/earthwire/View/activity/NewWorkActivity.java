package com.huadin.earthwire.View.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseActivity;

import butterknife.BindView;


public class NewWorkActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_work_name)
    EditText etWorkName;
    @BindView(R.id.et_build_team_head)
    EditText etBuildTeamHead;
    @BindView(R.id.et_phone)
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_new_work;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, getString(R.string.title_new_work));
    }

    @Override
    protected void initlistener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tool_bar_button, menu);
        menu.getItem(0).setTitle(getString(R.string.text_complete));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        showToast("完成");finish();
        return super.onOptionsItemSelected(item);
    }
}
