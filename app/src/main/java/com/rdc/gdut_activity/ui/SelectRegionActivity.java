package com.rdc.gdut_activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.RegionAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.Region;
import com.rdc.gdut_activity.db.RegionDao;
import com.rdc.gdut_activity.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class SelectRegionActivity extends BaseActivity implements OnClickRecyclerViewListener, TopBar.topbarClickListner {

    @InjectView(R.id.rv_region)
    RecyclerView rvRegion;

    public static final String REGION_PROVINCE = "region_province";
    public static final String REGION_CITY = "region_city";
    public static final String REGION_AREA = "region_area";

    private static final int RESULT_CODE_SUCCESS = 1;
    @InjectView(R.id.tb_select_area)
    TopBar tbSelectArea;

    private List<Region> mList;
    private List<Region> mProvinceList;
    private List<Region> mCityList;
    private List<Region> mAreaList;

    private RegionDao mRegionDao;
    private RegionAdapter mRegionAdapter;

    private int mState = 0;

    private String mProvince;
    private String mCity;
    private String mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_select_region;
    }

    @Override
    protected void initData() {
        mRegionDao = new RegionDao(this);
        mList = new ArrayList<>();
        mRegionAdapter = new RegionAdapter(mList, this);
        mRegionAdapter.setOnRecyclerViewListener(this);
        mProvinceList = mRegionDao.loadProvinceList();
        mRegionAdapter.appendData(mProvinceList);
    }

    @Override
    protected void initView() {
        tbSelectArea.setButtonBackground(R.drawable.icon_back, 0);
        tbSelectArea.setOnTopbarClickListener(this);
        rvRegion.setLayoutManager(new LinearLayoutManager(this));
        rvRegion.setAdapter(mRegionAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(int position) {
        Region region = mRegionAdapter.getItem(position);
        if (mState == 0) {
            mCityList = mRegionDao.loadCityList(region.getId());
            mList.clear();
            mRegionAdapter.appendData(mCityList);
            mProvince = region.getName();
            tbSelectArea.setTitle("选择城市");
            mState++;
        } else if (mState == 1) {
            mAreaList = mRegionDao.loadCountryList(region.getId());
            mCity = region.getName();
            if (mAreaList.size() == 0) {
                finishSelect();
            } else {
                mList.clear();
                mRegionAdapter.appendData(mAreaList);
                tbSelectArea.setTitle("选择地区");
                mState++;
            }
        } else if (mState == 2) {
            mArea = region.getName();
            mState++;
            finishSelect();
        }
    }

    private void finishSelect() {
        Intent intent = new Intent();
        intent.putExtra(REGION_PROVINCE, mProvince);
        intent.putExtra(REGION_CITY, mCity);
        intent.putExtra(REGION_AREA, mArea);
        setResult(RESULT_CODE_SUCCESS, intent);
        finish();
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mState == 0) {
            super.onBackPressed();
        }
        if (mState == 1) {
            mList.clear();
            mRegionAdapter.appendData(mProvinceList);
            tbSelectArea.setTitle("选择省份");
            mState--;
        } else if (mState == 2) {
            mList.clear();
            mRegionAdapter.appendData(mCityList);
            tbSelectArea.setTitle("选择城市");
            mState--;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
