package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.bean.Region;

import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> implements View.OnClickListener {

    private List<Region> mDatas;
    private Context mContext;
    private OnClickRecyclerViewListener mOnRecyclerViewListener;

    public RegionAdapter(List<Region> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_region, parent, false);
        view.setOnClickListener(this);
        RegionViewHolder regionViewHolder = new RegionViewHolder(view);
        return regionViewHolder;
    }

    @Override
    public void onBindViewHolder(RegionViewHolder holder, int position) {
        holder.regionTextView.setText(mDatas.get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void appendData(List dataList){
        if (null != dataList && !dataList.isEmpty()){
            mDatas.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public Region getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public void onClick(View v) {
        if(mOnRecyclerViewListener != null) {
            mOnRecyclerViewListener.onItemClick((int) v.getTag());
        }
    }

    class RegionViewHolder extends RecyclerView.ViewHolder{

        TextView regionTextView;

        public RegionViewHolder(View itemView) {
            super(itemView);
            regionTextView = (TextView) itemView.findViewById(R.id.tv_region_item);
        }

    }

    public void setOnRecyclerViewListener(OnClickRecyclerViewListener onRecyclerViewListener){
        mOnRecyclerViewListener = onRecyclerViewListener;
    }
}
