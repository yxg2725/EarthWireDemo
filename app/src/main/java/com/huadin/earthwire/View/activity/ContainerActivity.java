package com.huadin.earthwire.View.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.ConstUtil;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.View.fragment.HistoryWorkFragment;
import com.huadin.earthwire.View.fragment.NewWorkFragment;
import com.huadin.earthwire.View.fragment.StartWorkFragment;
import com.huadin.earthwire.event.FragmentNextEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


public class ContainerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private int viewID;

    StartWorkFragment startWorkFragment;
    HistoryWorkFragment historyWorkFragment;
    NewWorkFragment newWorkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void initView() {
        viewID = getIntent().getExtras().getInt(ConstUtil.KEY_FRAGMENT_ID);
        switch (viewID) {
            case 0:
                showToast("view无效");
                break;
            case ConstUtil.KEY_FRAGMENT_START_WORK:
                if (startWorkFragment == null) {
                    startWorkFragment = new StartWorkFragment();
                }
                initToolBar(mToolbar, true, "开始作业");
                replaceFragment(R.id.fl_container, startWorkFragment, null);
                fab.setVisibility(View.VISIBLE);
                break;
            case ConstUtil.KEY_FRAGMENT_HISTORY_WORK:
                if (historyWorkFragment == null) {
                    historyWorkFragment = new HistoryWorkFragment();
                }
                initToolBar(mToolbar, true, "历史作业");
                replaceFragment(R.id.fl_container, historyWorkFragment, null);
                fab.setVisibility(View.GONE);
                break;
        }
    }

    @Subscribe
    public void fragmentOnEvent(FragmentNextEvent next) {
        if (next.getViewId() == ConstUtil.KEY_FRAGMENT_NEW_WORK) {
            viewID = ConstUtil.KEY_FRAGMENT_NEW_WORK;
            if (newWorkFragment == null) {
                newWorkFragment = new NewWorkFragment();
            }
            initToolBar(mToolbar, true, "新建作业");
            replaceFragment(R.id.fl_container, newWorkFragment, null);
            fab.setVisibility(View.GONE);
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
        if (viewID == ConstUtil.KEY_FRAGMENT_START_WORK) {
            menu.setGroupVisible(0, false);
        } else if (viewID == ConstUtil.KEY_FRAGMENT_HISTORY_WORK) {
            menu.getItem(0).setTitle(getString(R.string.text_filtrate));
        } else if (viewID == ConstUtil.KEY_FRAGMENT_NEW_WORK) {
            menu.getItem(0).setTitle(getString(R.string.text_complete));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (viewID == ConstUtil.KEY_FRAGMENT_START_WORK) {

        } else if (viewID == ConstUtil.KEY_FRAGMENT_HISTORY_WORK) {
            // TODO: 2017/5/10

        } else if (viewID == ConstUtil.KEY_FRAGMENT_NEW_WORK) {

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        FragmentNextEvent next = new FragmentNextEvent();
        next.setViewId(ConstUtil.KEY_FRAGMENT_NEW_WORK);

        EventBus.getDefault().post(next);
    }
}
