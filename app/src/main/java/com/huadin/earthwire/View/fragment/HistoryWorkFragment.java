package com.huadin.earthwire.View.fragment;

import android.view.View;

import com.huadin.earthwire.R;
import com.huadin.earthwire.View.activity.ContainerActivity;
import com.huadin.earthwire.View.base.BaseFragment;

/**
 * Created by Jack Zhang on 2017/5/11.
 */

public class HistoryWorkFragment extends BaseFragment {

  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  Unbinder unbinder;

  @Override
  public int getlayoutId() {
    return R.layout.fragment_history_work;
  }

  @Override
  public void initView() {
    EventBus.getDefault().register(this);
  }

  @Override
  public void initData() {
    stateLayout.showContentView();
  }

  @Subscribe
  public void helloEventBus(List<WorkName> workNameList) {
    //展示查询的数据
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    HistoryAdapter adapter = new HistoryAdapter(getActivity(),workNameList) ;
    mRecyclerview.setAdapter(adapter);
  }

    @Override
    public void onResume() {
        super.onResume();
        ((ContainerActivity) getActivity()).setToolbarTitle("历史作业");
    }

  @Override
  public void onStop() {
    super.onStop();
    ((ContainerActivity) getActivity()).setToolbarMenu("", true);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
