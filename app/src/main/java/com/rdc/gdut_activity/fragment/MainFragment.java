package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ThatNight on 2017.4.26.
 */

public class MainFragment extends BaseFragment {


    private int mTitle;
    private String mMessage;
    private TopBar mTopBar;

    /**
     * 用来获取fragment实例的方法，这里可以让Activity给fragment设置参数,参数可以在下面的initData方法中的bundle中取出
     */
    public static MainFragment newInstance(int title, String message ) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("title", title);
        bundle.putString("message", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    public void topbarLeftButtonClick(){
        Toast.makeText(getActivity(),"主页面左按钮",Toast.LENGTH_SHORT).show();
    }

    public void topbarRightButtonClick(){
        Toast.makeText(getActivity(),"主页面右按钮",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    private void setParams(Bundle bundle) {
        mTitle = bundle.getInt("title");
        mMessage = bundle.getString("message");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
