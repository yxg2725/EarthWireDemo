package com.huadin.earthwire.View.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huadin.earthwire.Model.dao.bean.Person;
import com.huadin.earthwire.Presenter.activity.MainActivityPresenter;
import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseActivity;
import com.huadin.earthwire.View.fragment.HistoryWorkFragment;
import com.huadin.earthwire.View.fragment.PresentWorkFragment;
import com.huadin.earthwire.dagger.conponent.DaggerCommonConponent;
import com.huadin.earthwire.dagger.module.PresenterModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private long mExitTime = 0;
    private SparseArray<String> mSparseTags = new SparseArray<>();
    private View mHeaderView;
    private TextView mTvUserName;
    private TextView mTvProjectTeamName;

    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            // 如果剩一个说明在主页，提示按两次退出app
            exit();
        } else {
            // 获取上一个堆栈中保存的是哪个页面，根据name来设置导航项的选中状态
            final String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 2).getName();
            mNavView.setCheckedItem(mSparseTags.keyAt(mSparseTags.indexOfValue(tagName)));
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        DaggerCommonConponent.builder().presenterModule(new PresenterModule(this)).build().in(this);

        initToolBar(mToolbar, true, "地线管理");
        initDrawLayout();
        mSparseTags.put(R.id.nav_earthwork, "earthwork");
        mSparseTags.put(R.id.nav_history_earthwork, "history_earthwork");
        mSparseTags.put(R.id.nav_pole_collection, "collection");
        mSparseTags.put(R.id.nav_pole_import, "import");
        mSparseTags.put(R.id.nav_data_statistics, "statistics");
        mSparseTags.put(R.id.nav_about, "about");

        // 侧拉菜单头部点击事件
        mHeaderView = mNavView.getHeaderView(0);
        mTvUserName = (TextView) mHeaderView.findViewById(R.id.tv_use_people);
        mTvProjectTeamName = (TextView) mHeaderView.findViewById(R.id.tv_project_team_name);

        // 侧拉菜单item点击事件 默认展示地线作业
        mNavView.setCheckedItem(R.id.nav_earthwork);
        replaceFragment(R.id.fl_container, new PresentWorkFragment(), mSparseTags.get(R.id.nav_earthwork));
    }

    private void initDrawLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initlistener() {
        mNavView.setNavigationItemSelectedListener(this);
        mHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                String user = mTvUserName.getText().toString();
                String projectTeam = mTvProjectTeamName.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                bundle.putString("projectTeam",projectTeam);

                startToActivity(bundle,UserInfoSetActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询数据库的个人信息
        mainActivityPresenter.queryPersonInfo();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        if (id == R.id.nav_earthwork) {//当前作业
            replaceFragment(R.id.fl_container, new PresentWorkFragment(), mSparseTags.get(R.id.nav_earthwork));
        } else if (id == R.id.nav_history_earthwork) {//历史作业
            replaceFragment(R.id.fl_container, new HistoryWorkFragment(), mSparseTags.get(R.id.nav_history_earthwork));
        } else if (id == R.id.nav_pole_collection) {//杆塔采集
            mNavView.setCheckedItem(R.id.nav_pole_collection);
            showToast("杆塔采集");
        } else if (id == R.id.nav_pole_import) {//杆塔导入
            showToast("杆塔导入");
        } else if (id == R.id.nav_data_statistics) {//数据统计
            showToast("数据统计");
        } else if (id == R.id.nav_about) {//关于
            showToast("关于");
        }
        return true;
    }

    /**
     * 退出
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(Object o) {
        super.success(o);
        if(o != null){
            Person person = (Person) o;
            mTvUserName.setText(person.getUserName());
            mTvProjectTeamName.setText(person.getProjectTeam());
        }else{
            mTvUserName.setText("姓名");
            mTvProjectTeamName.setText("工程队");
        }

    }
}
