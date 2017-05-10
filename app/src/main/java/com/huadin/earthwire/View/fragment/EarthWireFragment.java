package com.huadin.earthwire.View.fragment;


import android.support.design.widget.TabLayout;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.adapter.EarthWireAdapter;
import com.huadin.earthwire.View.base.BaseFragment;
import com.huadin.earthwire.View.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by 华电 on 2017/4/26.
 */

public class EarthWireFragment extends BaseFragment {

  @BindView(R.id.tablayout)
  TabLayout mTablayout;
  @BindView(R.id.viewpager)
  NoScrollViewPager mViewpager;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_home;
  }

  @Override
  public void initView() {
    ArrayList<BaseFragment> fragments = new ArrayList<>();
    fragments.add(new EarthWireWorkFragment());
    fragments.add(new EarthWireHistoryFragment());

    mViewpager.setOffscreenPageLimit(3);
    EarthWireAdapter adapter = new EarthWireAdapter(getChildFragmentManager(),fragments);
    mViewpager.setAdapter(adapter);
    mTablayout.setupWithViewPager(mViewpager);
  }

  @Override
  public void initData() {
    stateLayout.showContentView();
  }

  @Override
  public void onReload() {
  }

}
