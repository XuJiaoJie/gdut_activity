package com.rdc.gdut_activity.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mBaseActivity;  //贴附的activity,Fragment中可能用到
    protected View mRootView;    //根view

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResourceId(), container, false);
        initData(getArguments());
        initView();
        setListener();
        return mRootView;
    }

    protected abstract int setLayoutResourceId();

    /**
     * 初始化数据
     *
     * @param bundle 接收到的从其他地方传递过来的数据
     */
    protected abstract void initData(Bundle bundle);

    //初始化View
    protected abstract void initView();

    //设置监听事件
    protected abstract void setListener();

}
