package com.rdc.gdut_activity.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.rdc.gdut_activity.R;

public class PreviewActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, PreviewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
    }
}
