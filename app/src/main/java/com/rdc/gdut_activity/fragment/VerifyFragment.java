package com.rdc.gdut_activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.LoadMoreAdapterWrapper;
import com.rdc.gdut_activity.adapter.VerifyRecyclerAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.adapter.adapterInterface.OnLoadMoreDataRv;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.ui.DetailsVerifyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


public class VerifyFragment extends BaseFragment implements OnLoadMoreDataRv, OnClickRecyclerViewListener {
    private static final String TAG = "VerifyFragment";
    @InjectView(R.id.rv_verify_fragment_list)
    RecyclerView mRvVerifyFragmentList;
    @InjectView(R.id.srl_verify_fragment)
    SwipeRefreshLayout mSrlVerifyFragment;
    private String mType; //Fragment类型标志，如审核和未审核
    private List<ActivityInfoBean> mBeanList;
    private VerifyRecyclerAdapter mAdapter;
    private LoadMoreAdapterWrapper mLoadMoreAdapter;

    /**
     * 创建Fragment，并传入参数
     */
    public static VerifyFragment newInstance(String type) {
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentType", type);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_verify;
    }

    @Override
    protected void initData(Bundle bundle) {
        mType = bundle.getString("fragmentType");
        mBeanList = new ArrayList<>();
        mAdapter = new VerifyRecyclerAdapter();
        mAdapter.setOnRecyclerViewListener(this);
    }

    @Override
    protected void initView() {
        mRvVerifyFragmentList.setHasFixedSize(true);
        mRvVerifyFragmentList.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));
        Data();
        mRvVerifyFragmentList.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {

        });

    }

    @Override
    protected void setListener() {

    }


    //虚拟数据
    private void Data() {
        for (int i = 0; i < 10; i++) {
            ActivityInfoBean bean = new ActivityInfoBean();
            bean.setActivityName("研发中心招新宣讲会开始啦啦啦  " + i);
            bean.setActivityTime("2017-5-8 19:00");
            bean.setActivityLocation("广东工业大学工一学术报告厅   " + i);
            bean.setPublishTime("2017-5-7 12:00");
            bean.setPublisherName("广东工业大学团委");
            bean.setActivityType("讲座");
            bean.setActivityHost("研发中心工作室");
            bean.setActivityDetail("多久啊看来大家啊克里夫剪啊开房间里的咖啡机的刻录机福克斯的减肥快圣诞分" +
                    "公司感受感受的分公司人发过过生日果然果然果然够节咖啡馆的精神科更加深刻搭公交可视对讲");
            List<String> picList = new ArrayList<>();
            picList.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/10762c593798466a.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/233a5f70512befcc.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
            picList.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg");
//            picList.add("http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg");
            bean.setImgUrlList(picList);
            mBeanList.add(bean);
        }
        mAdapter.updataData(mBeanList);
        if (null == mLoadMoreAdapter) {
            mLoadMoreAdapter = new LoadMoreAdapterWrapper(mAdapter, this);
            mRvVerifyFragmentList.setAdapter(mLoadMoreAdapter);
        } else {
            mLoadMoreAdapter.notifyDataSetChanged();
        }
    }

    /**
     * RecyclerView的点击和长按事件
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mBaseActivity, DetailsVerifyActivity.class);
        intent.putExtra("DetailsVerifyActivity", (Parcelable) mBeanList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    /**
     * 加载更多触发事件
     */
    @Override
    public void loadMoreData() {

    }
}
