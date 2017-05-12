package com.huadin.earthwire.View.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.ConstUtil;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.View.fragment.WorkListFragment;

import butterknife.BindView;

public class WorkActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private int viewID;
    private String workName;

    WorkListFragment workListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void initView() {
        workName = getIntent().getExtras().getString(ConstUtil.KEY_WORK_NAME);
        initToolBar(mToolbar, true, workName);

        viewID = getIntent().getExtras().getInt(ConstUtil.KEY_FRAGMENT_ID);
        switch (viewID) {
            case 0:
                showToast("view无效");
                break;
            case ConstUtil.KEY_FRAGMENT_WORK_LIST:
                if (workListFragment == null) {
                    workListFragment = new WorkListFragment();
                }
                initToolBar(mToolbar, true, workName);
                replaceFragment(R.id.fl_container, workListFragment, "worklist");
                break;
        }
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

        if (viewID == ConstUtil.KEY_FRAGMENT_WORK_LIST) {

        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (viewID == ConstUtil.KEY_FRAGMENT_WORK_LIST) {

        }

        if (item.getItemId() == android.R.id.home) {
            exit();
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.title_right_button).setVisible(viewID != ConstUtil.KEY_FRAGMENT_WORK_LIST);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        if (mToolbar.getTitle().toString().equals(workName)) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
