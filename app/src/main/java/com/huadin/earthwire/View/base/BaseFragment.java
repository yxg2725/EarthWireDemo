package com.huadin.earthwire.View.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.LogUtils;
import com.huadin.earthwire.Utils.ToastUtils;
import com.huadin.earthwire.View.widget.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 华电 on 2017/4/26.
 */

public abstract class BaseFragment extends Fragment implements StateLayout.OnReloadListener, BDLocationListener,BaseView {
    @Nullable
    @BindView(R.id.mapview)
    MapView mMapview;
    protected StateLayout stateLayout;//复用Fragment的View对象
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private LatLng myLocation;
    private boolean isFirstLoc = true;
    public FragmentActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (stateLayout == null) {
            stateLayout = new StateLayout(getContext());
            View contentView = inflater.inflate(getlayoutId(), null);
            stateLayout.setContentView(contentView);
            stateLayout.showLoadingView();//默认显示加载中界面
            stateLayout.setOnReloadListener(this);
            ButterKnife.bind(this, contentView);

            initView();
        }
        return stateLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    protected void showToast(String msg) {
        ToastUtils.showToast(activity, msg);
    }

    protected void startToActivity(Bundle bundle, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivity(intent);
    }

    protected void initMapView() {
        mMapview.showScaleControl(false);
        mMapview.showZoomControls(false); //设置显示标尺控件
        mBaiduMap = mMapview.getMap();//获取地图管理者
        // mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));////设置缩放级别 默认级别是12
        //设置打开地图显示的位置  可以百度地图开发者中心 拾取想要的坐标点
        // mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(39.999452, 116.402131)));//鸟巢 116.402131,39.999452
        startLocation();
    }

    /**
     * 定位初始化
     */
    private void startLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(activity);
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        // option.setCoorType("gcj02"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapview != null) {
            mLocClient.stop();
            // 关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
            mMapview.onDestroy();
            mMapview = null;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapview != null) {
            mMapview.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapview != null) {
            mMapview.onPause();
        }
    }

    public abstract int getlayoutId();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null || mMapview == null) {
            return;
        }

        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if (isFirstLoc) {
            isFirstLoc = false;

            LogUtils.logi(this.getClass(), location.getLocType() + "");

            if (location.getLocType() == BDLocation.TypeCriteriaException) {
                //stateLayout.showErrorView();
            }
            if (location.getLocType() == BDLocation.TypeServerError) {
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16.0f));//默认级别是12
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(39.763972, 116.159391)));//房山供电局营销楼
            } else {
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(myLocation).zoom(15.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

        }
    }

    @Override
    public void onReload() {

    }
}
