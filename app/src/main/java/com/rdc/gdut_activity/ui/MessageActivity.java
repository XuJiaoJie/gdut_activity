package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.MessageMainAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.MessageBean;
import com.rdc.gdut_activity.view.TopBar;

import java.io.Serializable;
import java.util.List;

import butterknife.InjectView;

public class MessageActivity extends BaseActivity implements TopBar.topbarClickListner {
    @InjectView(R.id.tb_activity_message)
    TopBar mTbActivityMessage;
    @InjectView(R.id.rv_activity_message)
    RecyclerView mRvActivityMessage;
    private MessageMainAdapter mMainAdapter;
    private List<MessageBean> mBeanList;
    private MessageBean mBean;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        mTbActivityMessage.setButtonBackground(R.drawable.iv_back, 0);
        mMainAdapter = new MessageMainAdapter();
        mBeanList = (List<MessageBean>) getIntent().getSerializableExtra("msg");
    }

    @Override
    protected void initView() {
        mRvActivityMessage.setLayoutManager(new LinearLayoutManager(this));
        mRvActivityMessage.setAdapter(mMainAdapter);
        mMainAdapter.updataData(mBeanList);
    }

    @Override
    protected void initListener() {
        mTbActivityMessage.setOnTopbarClickListener(this);
    }

    public static Intent newIntent(Context context, List<MessageBean> messageBean) {
        Intent intent = new Intent(context, MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("msg", (Serializable) messageBean);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * TopBar点击
     */
    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
