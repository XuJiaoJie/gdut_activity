package com.rdc.gdut_activity.base;


import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter <T> extends RecyclerView.Adapter{
    protected List<T> mDataList = new ArrayList<>();
    protected OnClickRecyclerViewListener mOnRecyclerViewListener;

    //更新数据
    public void updataData(List dataList){
        mDataList.clear();
        appendData(dataList);
    }

    //分页加载，追加数据
    public void appendData(List dataList){
        if (null != dataList && !dataList.isEmpty()){
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     *RecyclerView不提供点击事件，自定义点击事件
     */
    public void setOnRecyclerViewListener(OnClickRecyclerViewListener onRecyclerViewListener){
        mOnRecyclerViewListener = onRecyclerViewListener;
    }
}

