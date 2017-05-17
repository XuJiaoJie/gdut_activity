package com.rdc.gdut_activity.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtil {
    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context, CharSequence msg) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(msg);

        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void setMsg(CharSequence msg) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(msg);
        }
    }

    public static void dismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}