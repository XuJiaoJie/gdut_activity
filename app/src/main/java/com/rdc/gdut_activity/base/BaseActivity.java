package com.rdc.gdut_activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        ButterKnife.inject(this);
        initData();
        initView();
        initListener();
    }

    protected abstract int setLayoutResourceID();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void initListener();

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
