package com.rdc.gdut_activity.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rdc.gdut_activity.MainActivity;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.bean.MessageBean;
import com.rdc.gdut_activity.utils.GsonUtil;

import cn.bmob.push.PushConstants;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class PushMessageReceiver extends BroadcastReceiver {
    private IOnReceiverListener mListener;
    private Context mContext;

    public PushMessageReceiver(Context context, IOnReceiverListener listener) {
        super();
        mContext = context;
        mListener = listener;
    }

    public PushMessageReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (PushConstants.ACTION_MESSAGE.equals(intent.getAction())) {
            // TODO: 2017.5.16 获取通知

            Log.d("receiver", "onReceive: " + intent.getStringExtra("msg"));
            MessageBean msg = GsonUtil.gsonToBean(intent.getStringExtra("msg"), MessageBean.class);
            setNotification(msg);
            if (mListener != null) {
                mListener.refresh(msg);
            }
        }
    }

    private void setNotification(MessageBean msg) {
        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(mContext);
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, 0);

        builder.setAutoCancel(true)
                .setTicker("活动有新的推送哦!")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(msg.getName())
                .setContentText(msg.getMessage())
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi);
        Notification notification = builder.build();
        // TODO: 2017.5.17 兼容4.0.3
        manager.notify(msg.getName(), 1, notification);
    }

    public interface IOnReceiverListener {
        void refresh(MessageBean messageBean);
    }
}
