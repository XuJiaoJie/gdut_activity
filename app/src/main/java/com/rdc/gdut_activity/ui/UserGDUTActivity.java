package com.rdc.gdut_activity.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.view.TopBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UserGDUTActivity extends BaseActivity implements TopBar.topbarClickListner {

    @InjectView(R.id.tb_user_gdut)
    TopBar tbUserGdut;
    private AlertDialog.Builder mBuilder;

    @InjectView(R.id.tv_school_academic)
    TextView tvSchoolAcademic;
    @InjectView(R.id.ll_academic)
    LinearLayout llAcademic;
    @InjectView(R.id.tv_school_major)
    TextView tvSchoolMajor;
    @InjectView(R.id.ll_major)
    LinearLayout llMajor;
    @InjectView(R.id.tv_school_class)
    TextView tvSchoolClass;
    @InjectView(R.id.ll_class)
    LinearLayout llClass;
    @InjectView(R.id.tv_school_number)
    TextView tvSchoolNumber;
    @InjectView(R.id.ll_school_number)
    LinearLayout llSchoolNumber;
    @InjectView(R.id.tv_school_password)
    TextView tvSchoolPassword;
    @InjectView(R.id.ll_school_password)
    LinearLayout llSchoolPassword;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_user_gdut;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tbUserGdut.setButtonBackground(R.drawable.icon_back, 0);
        tbUserGdut.setOnTopbarClickListener(this);
        mBuilder = new AlertDialog.Builder(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.ll_academic, R.id.ll_major, R.id.ll_class, R.id.ll_school_number, R.id.ll_school_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_academic:
                showChangeAcademicDialog();
                break;
            case R.id.ll_major:
                showChangeMajorDialog();
                break;
            case R.id.ll_class:
                showChangeClassDialog();
                break;
            case R.id.ll_school_number:
                showChangeSchoolNumberDialog();
                break;
            case R.id.ll_school_password:
                showChangePasswordDialog();
                break;
        }
    }

    private void showChangeSchoolNumberDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("切换学号");
        builder.setMessage("您确定要切换另一个学号吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 2017/5/13 切换学号验证
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showChangePasswordDialog() {
        initBuilder("密码", -1);
        final View view = View.inflate(this, R.layout.layout_dialog_change_password, null);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPassword = ((EditText) view.findViewById(R.id.et_old_password)).getText().toString();
                String newPassword = ((EditText) view.findViewById(R.id.et_new_password)).getText().toString();
                String confirmPassword = ((EditText) view.findViewById(R.id.et_confirm_password)).getText().toString();
                if (!oldPassword.equals(tvSchoolPassword.getText().toString())) {
                    Toast.makeText(UserGDUTActivity.this, "旧密码输入错误", Toast.LENGTH_LONG).show();
                    return;
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(UserGDUTActivity.this, "新密码与确认密码不一致", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(UserGDUTActivity.this, "密码修改成功", Toast.LENGTH_LONG).show();
                    tvSchoolPassword.setText(newPassword);
                    dialog.dismiss();
                }
                // TODO: 2017/5/12 云端存储
            }
        });
        final AlertDialog dialog = mBuilder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void showChangeClassDialog() {
        initBuilder("班级", -1);
        final View view = View.inflate(this, R.layout.layout_dialog_change_phone_number, null);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String academic = ((EditText) view.findViewById(R.id.et_phone_number)).getText().toString();
                tvSchoolClass.setText(academic);
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = mBuilder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void showChangeMajorDialog() {
        initBuilder("专业", -1);
        final View view = View.inflate(this, R.layout.layout_dialog_change_phone_number, null);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String academic = ((EditText) view.findViewById(R.id.et_phone_number)).getText().toString();
                tvSchoolMajor.setText(academic);
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = mBuilder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void showChangeAcademicDialog() {
        initBuilder("学院", -1);
        final View view = View.inflate(this, R.layout.layout_dialog_change_phone_number, null);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String academic = ((EditText) view.findViewById(R.id.et_phone_number)).getText().toString();
                tvSchoolAcademic.setText(academic);
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = mBuilder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void initBuilder(String title, int iconId) {
        mBuilder.setTitle(title);
        if (iconId != -1) {
            mBuilder.setIcon(iconId);
        }
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
