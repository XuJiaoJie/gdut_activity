package com.rdc.gdut_activity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rdc.gdut_activity.bean.MessageBean;
import com.rdc.gdut_activity.utils.GsonUtil;

import cn.bmob.push.PushConstants;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class PushMessageReceiver extends BroadcastReceiver {
    private IOnReceiverListener mListener;

    public PushMessageReceiver(IOnReceiverListener listener) {
        super();
        mListener = listener;
    }

    public PushMessageReceiver() {
        super();
    }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (PushConstants.ACTION_MESSAGE.equals(intent.getAction())) {
                // TODO: 2017.5.16 获取通知
                Log.d("receiver", "onReceive: "+intent.getStringExtra("msg"));
                MessageBean msg = GsonUtil.gsonToBean(intent.getStringExtra("msg"), MessageBean.class);
                if (mListener != null) {
                    mListener.refresh(msg);
                }
            }
    }

    public interface IOnReceiverListener {
        void refresh(MessageBean messageBean);
    }
}
