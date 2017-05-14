package com.huadin.earthwire.View.fragment;

import android.content.Intent;
import android.widget.TextView;

import com.huadin.earthwire.Model.dao.DaoManager;
import com.huadin.earthwire.Model.dao.EarthWireDao;
import com.huadin.earthwire.Model.dao.bean.EarthWire;
import com.huadin.earthwire.R;
import com.huadin.earthwire.Utils.LogUtils;
import com.huadin.earthwire.View.activity.MainActivity;
import com.huadin.earthwire.View.activity.StartWorkActivity;
import com.huadin.earthwire.View.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 当前作业Fragment
 */
public class PresentWorkFragment extends BaseFragment {

    @BindView(R.id.tv_start_work)
    TextView mTvStartWork;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_present_work;
    }

    @Override
    public void initView() {
        initMapView();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)activity).mToolbar.setTitle("地线管理");

        //查询地线数据库
        List<EarthWire> list = DaoManager.getInstance().getDaoSession().getEarthWireDao()
                .queryBuilder().where(EarthWireDao.Properties.CurrentState.eq("进行中"))
                .build().list();

        for(EarthWire earthWire: list){
            String locationInfo = earthWire.getLocationInfo();
            LogUtils.logi(this.getClass(),locationInfo);
        }

        //展示
        // TODO: 2017/5/14
    }

    @Override
    public void initData() {
        //1.从网络加载数据
        //2.显示内容布局
        stateLayout.showContentView();
    }


    @Override
    public void onReload() {
        //重新定位
        initMapView();
    }


    @OnClick(R.id.tv_start_work)
    public void onClick() {

        //跳转到开始作业界面
        ((MainActivity)getActivity())
                .startActivity(new Intent(getActivity(),StartWorkActivity.class));

    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failed(String msg) {

    }
}
