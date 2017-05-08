package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReleasedFragment extends BaseFragment {

    @InjectView(R.id.tv_activity_name)
    TextView mTvActivityName;
    @InjectView(R.id.tv_activity_type)
    TextView mTvActivityType;
    @InjectView(R.id.tv_activity_host)
    TextView mTvActivityHost;
    @InjectView(R.id.tv_location)
    TextView mTvLocation;
    @InjectView(R.id.tv_detail)
    TextView mTvDetail;
    @InjectView(R.id.tv_form_data)
    TextView mTvFormData;
    @InjectView(R.id.btn_preview)
    Button mBtnPreview;

    private ActivityInfoBean mActivityInfoBean;
    private static final String KEY_EXTRA_ACTIVITY_INFO_BEAN = "EXTRA_ACTIVITY_INFO_BEAN";

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // TODO: 2017/5/6 0006 临时
    public static ReleasedFragment newInstance() {
        Bundle args = new Bundle();
        ReleasedFragment fragment = new ReleasedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ReleasedFragment newInstance(ActivityInfoBean a) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_EXTRA_ACTIVITY_INFO_BEAN, a);
        ReleasedFragment fragment = new ReleasedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_released;
    }

    @Override
    protected void initData(Bundle bundle) {
        mActivityInfoBean = bundle.getParcelable(KEY_EXTRA_ACTIVITY_INFO_BEAN);
    }

    @Override
    protected void initView() {
        ButterKnife.inject(this, mRootView);
        if (mActivityInfoBean != null) {
            mTvActivityName.setText(mActivityInfoBean.getActivityName());
            mTvDetail.setText(mActivityInfoBean.getActivityDetail());
            mTvActivityHost.setText(mActivityInfoBean.getActivityHost());
        }
    }

    @Override
    protected void setListener() {

    }

}
