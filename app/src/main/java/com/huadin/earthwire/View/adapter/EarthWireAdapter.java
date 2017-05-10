package com.huadin.earthwire.View.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huadin.earthwire.View.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 华电 on 2017/4/26.
 */

public class EarthWireAdapter extends FragmentPagerAdapter {

  private final String[] mTitles;
  private ArrayList<BaseFragment> fragments;

  public EarthWireAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
    super(fm);
    mTitles = new String[]{"当前作业","历史记录"};
    this.fragments = fragments;
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return mTitles.length;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mTitles[position];
  }
}
