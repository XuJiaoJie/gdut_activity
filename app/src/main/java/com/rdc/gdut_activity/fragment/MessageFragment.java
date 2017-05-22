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
import java.util.Date;
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
    public static final String mSystemIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495466296507&di=332e23e59ea9a6a9e5aa21f49aebc70a&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F8ad4b31c8701a18bd51ee7919c2f07082938fe73.jpg";


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
        initDefaultData();
        mMessageAdapter = new MessageAdapter();
    }

    /**
     * 初始化系统消息
     */
    private void initDefaultData() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        MessageBean messageBean = new MessageBean();
        messageBean.setName("系统消息");
        messageBean.setObjectid("1");
        messageBean.setIcon(mSystemIcon);
        messageBean.setTime(format.format(new Date()));
        messageBean.setMessage("感谢你使用活在广工!");
        mMessageBean.add(messageBean);
        ArrayList<MessageBean> mapList = new ArrayList<>();
        mapList.add(messageBean);
        mMapList.put(messageBean.getObjectid(), mapList);
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
