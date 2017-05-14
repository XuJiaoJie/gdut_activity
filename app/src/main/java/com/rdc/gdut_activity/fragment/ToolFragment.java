package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseFragment;

/**
 * Created by zjz on 2017/5/5.
 */

public class ToolFragment extends BaseFragment {

    private int mTitle;
    private String mMessage;

    /**
     用来获取fragment实例的方法，这里可以让Activity给fragment设置参数,参数可以在下面的initData方法中的bundle中取出
     */
    public static  ToolFragment newInstance(int title , String message){
        ToolFragment toolFragment = new ToolFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("title",title);
        bundle.putString("message",message);
        toolFragment.setArguments(bundle);
        return toolFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_tool;
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

    public void topbarLeftButtonClick(){
        Toast.makeText(getActivity(),"工具左按钮",Toast.LENGTH_SHORT).show();
    }

    public void topbarRightButtonClick(){
        Toast.makeText(getActivity(),"工具右按钮",Toast.LENGTH_SHORT).show();
    }
}

