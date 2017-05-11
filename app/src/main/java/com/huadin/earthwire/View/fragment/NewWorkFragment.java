package com.huadin.earthwire.View.fragment;

import android.widget.EditText;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Jack Zhang on 2017/5/11.
 */

public class NewWorkFragment extends BaseFragment {

    @BindView(R.id.et_work_name)
    EditText etWorkName;
    @BindView(R.id.et_build_team_head)
    EditText etBuildTeamHead;
    @BindView(R.id.et_phone)
    EditText etPhone;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_new_work;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        stateLayout.showContentView();
    }
}
