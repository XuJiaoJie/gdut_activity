package com.rdc.gdut_activity.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.rdc.gdut_activity.R;

/**
 * Created by ThatNight on 2017.5.14.
 */

public class SelectClassPopWindow extends PopupWindow {
    private View mContentView;
    private LinearLayout mLayoutSearch, mLayoutMyClass;

    public SelectClassPopWindow(Context context, View.OnClickListener onClickListener, int width, int height) {
        mContentView = LayoutInflater.from(context).inflate(R.layout.dialog_selectclass_menu, null);
        mLayoutSearch = (LinearLayout) mContentView.findViewById(R.id.ll_dialog_selectclass_search);
        mLayoutMyClass = (LinearLayout) mContentView.findViewById(R.id.ll_dialog_selectclass_myclass);
        if (onClickListener != null) {
            mLayoutSearch.setOnClickListener(onClickListener);
            mLayoutMyClass.setOnClickListener(onClickListener);
        }
        setContentView(mContentView);
        setWidth(width);
        setHeight(height);
        setFocusable(true);
        mContentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    dismiss();
                }
            }
        });
    }


    public void showPopupWindow(View parent) {
        if (!isShowing()) {
            showAsDropDown(parent, parent.getLayoutParams().width, 24);
        } else {
            dismiss();
        }
    }
}
