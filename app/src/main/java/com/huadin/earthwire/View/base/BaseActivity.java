package com.huadin.earthwire.View.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.huadin.earthwire.Utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by 华电 on 2017/4/26.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

  private static final int REQUEST_PERMISSION_LOCATION = 1;
  private static final int REQUEST_PERMISSION_READ_PHONE_STATE = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        ButterKnife.bind(this);

        requestPermissions();

        initView();
        initlistener();
        initData();
    }

  private void requestPermissions() {
    //动态申请权限
    if(Build.VERSION.SDK_INT >=23){

      //定位权限
      if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_PERMISSION_LOCATION);
      }
      //读取手机状态
      if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_PERMISSION_READ_PHONE_STATE);
      }

    }
  }

  /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 替换Activity
     */
    protected void startToActivity(Bundle bundle, Class clazz) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 布局文件
     */
    public abstract int getlayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 注册监听事件
     */
    protected abstract void initlistener();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Override
    public void success(Object o) {

    }

    @Override
    public void failed(String msg) {

    }


}
