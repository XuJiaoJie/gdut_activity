package com.rdc.gdut_activity.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.MessageAdapter;
import com.rdc.gdut_activity.adapter.adapterInterface.OnClickRecyclerViewListener;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.MessageBean;
import com.rdc.gdut_activity.ui.MessageActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by zjz on 2017/5/5.
 */

public class MessageFragment extends BaseFragment implements OnClickRecyclerViewListener, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.rv_message)
    RecyclerView mRvMessage;
    @InjectView(R.id.srl_message)
    SwipeRefreshLayout mSrlMessage;
    private int mTitle;
    private String mMessage;
    private MessageAdapter mMessageAdapter;
    private List<MessageBean> mMessageBean;
    private Map<String, List<MessageBean>> mMapList;

    /**
     * 用来获取fragment实例的方法，这里可以让Activity给fragment设置参数,参数可以在下面的initData方法中的bundle中取出
     */
    public static MessageFragment newInstance(int title, String message) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("title", title);
        bundle.putString("message", message);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mMessageBean = new ArrayList<>();
        mMapList = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");

        mMessageAdapter = new MessageAdapter();
    }

    @Override
    protected void initView() {
        mSrlMessage.setColorSchemeResources(R.color.colorPrimary);
        mSrlMessage.setEnabled(false);
        mRvMessage.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        mRvMessage.addItemDecoration(new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        mRvMessage.setItemAnimator(new DefaultItemAnimator());
        mRvMessage.setAdapter(mMessageAdapter);
        mMessageAdapter.updataData(mMessageBean);
    }

    @Override
    protected void setListener() {
        mSrlMessage.setOnRefreshListener(this);
        mMessageAdapter.setOnRecyclerViewListener(this);
    }

    private void setParams(Bundle bundle) {
        mTitle = bundle.getInt("title");
        mMessage = bundle.getString("message");
    }

    /**
     * RecyclerView Item
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        startActivity(MessageActivity.newIntent(mBaseActivity, mMapList.get(mMessageBean.get(position).getObjectid())));
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    public void onUpdatePush(MessageBean messageBean) {
        setMessgeList(messageBean);
        setMapList(messageBean);
        mMessageAdapter.updataData(mMessageBean);
    }

    private void setMapList(MessageBean messageBean) {
        List<MessageBean> msg = null;
        if (mMapList.get(messageBean.getObjectid()) == null) {
            msg = new ArrayList<>();
            msg.add(messageBean);
            mMapList.put(messageBean.getObjectid(), msg);
        } else {
            msg = mMapList.get(messageBean.getObjectid());
            msg.add(messageBean);
            mMapList.put(messageBean.getObjectid(), msg);
        }
    }

    private void setMessgeList(MessageBean messageBean) {
        if (mMessageBean.contains(messageBean)) {
            mMessageBean.remove(messageBean);
        }
        mMessageBean.add(0, messageBean);
    }

}
