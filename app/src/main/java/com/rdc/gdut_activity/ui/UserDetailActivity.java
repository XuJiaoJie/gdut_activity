package com.rdc.gdut_activity.ui;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.bean.User;
import com.rdc.gdut_activity.utils.DBCopyUtil;
import com.rdc.gdut_activity.view.CircleImageView;
import com.rdc.gdut_activity.view.TopBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class UserDetailActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TopBar.topbarClickListner {

    @InjectView(R.id.civ_user_icon)
    CircleImageView civUserIcon;
    @InjectView(R.id.ll_user_icon)
    LinearLayout llUserIcon;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.ll_user_name)
    LinearLayout llUserName;
    @InjectView(R.id.tv_user_sex)
    TextView tvUserSex;
    @InjectView(R.id.ll_user_sex)
    LinearLayout llUserSex;
    @InjectView(R.id.tv_user_age)
    TextView tvUserAge;
    @InjectView(R.id.ll_user_age)
    LinearLayout llUserAge;
    @InjectView(R.id.tv_user_area)
    TextView tvUserArea;
    @InjectView(R.id.ll_user_area)
    LinearLayout llUserArea;
    @InjectView(R.id.tv_user_phone_number)
    TextView tvUserPhoneNumber;
    @InjectView(R.id.ll_user_phone_number)
    LinearLayout llUserPhoneNumber;
    @InjectView(R.id.tv_user_password)
    TextView tvUserPassword;
    @InjectView(R.id.ll_user_password)
    LinearLayout llUserPassword;
    @InjectView(R.id.tb_user_details)
    TopBar tbUserDetails;
//    @InjectView(R.id.ll_user_school_number)
//    LinearLayout llUserSchoolNumber;

    private Bitmap mUserHeadIcon;
    private String mPath = "/sdcard/myHeadIcon/";
    private static final int REQUEST_SELECT_PHOTO = 1;
    private static final int REQUEST_SELECT_CAMERA = 2;
    private static final int REQUEST_CROP_PHOTO = 3;
    private static final int REQUEST_CHANGE_NAME = 4;
    private static final int REQUEST_SELECT_REGION = 5;

    private Boolean isMale = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        tbUserDetails.setButtonBackground(R.drawable.icon_back, 0);
        tbUserDetails.setOnTopbarClickListener(this);
        Bitmap bitmap = BitmapFactory.decodeFile(mPath + "head.jpg");
        if (bitmap != null) {
            Drawable drawable = new BitmapDrawable(bitmap);
            civUserIcon.setImageDrawable(drawable);
        } else {
            // TODO: 2017/5/12 从云端下载
        }
        Intent intent = getIntent();
        tvUserName.setText(intent.getStringExtra("username"));
        tvUserSex.setText(intent.getBooleanExtra("sex", false) == true ? "男" : "女");
        tvUserAge.setText(intent.getStringExtra("age"));
        tvUserArea.setText(intent.getStringExtra("area"));
        tvUserPassword.setText(intent.getStringExtra("password"));
        tvUserPhoneNumber.setText(intent.getStringExtra("phone_number"));
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.ll_user_icon, R.id.ll_user_name, R.id.ll_user_sex, R.id.ll_user_age, R.id.ll_user_area, R.id.ll_user_phone_number, R.id.ll_user_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_icon:
                showIconSelectDialog();
                break;
            case R.id.ll_user_name:
                changUserName();
                break;
            case R.id.ll_user_sex:
                showSexSelectDialog();
                break;
            case R.id.ll_user_age:
                showAgeSelectDialog();
                break;
            case R.id.ll_user_area:
                startAreaSelectActivity();
                break;
            case R.id.ll_user_phone_number:
                showChangePhoneNumberDialog();
                break;
            case R.id.ll_user_password:
                showChangePasswordDialog();
                break;
        }
    }

    private void showChangePhoneNumberDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改手机号码");
        builder.setIcon(R.drawable.icon_phone);
        final View view = View.inflate(this, R.layout.layout_dialog_change_phone_number, null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phoneNumber = ((EditText) view.findViewById(R.id.et_phone_number)).getText().toString();
                if (phoneNumber.length() != 11) {
                    Toast.makeText(UserDetailActivity.this, "电话号码要求11位", Toast.LENGTH_LONG).show();
                } else {
                    dialog.dismiss();
                    tvUserPhoneNumber.setText(phoneNumber);
                    //Bmob后端云上传更新
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    User user = new User();
                    user.setMobilePhoneNumber(phoneNumber);
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(UserDetailActivity.this, "success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void startAreaSelectActivity() {
        DBCopyUtil.copyDataBaseFromAssets(this, "region.db");
        Intent selectRegionIntent = new Intent(this, SelectRegionActivity.class);
        startActivityForResult(selectRegionIntent, REQUEST_SELECT_REGION);
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改密码");
        builder.setIcon(R.drawable.icon_password);
        final View view = View.inflate(this, R.layout.layout_dialog_change_password, null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPassword = ((EditText) view.findViewById(R.id.et_old_password)).getText().toString();
                String newPassword = ((EditText) view.findViewById(R.id.et_new_password)).getText().toString();
                String confirmPassword = ((EditText) view.findViewById(R.id.et_confirm_password)).getText().toString();
                if (!oldPassword.equals(tvUserPassword.getText().toString())) {
                    Toast.makeText(UserDetailActivity.this, "旧密码输入错误", Toast.LENGTH_LONG).show();
                    return;
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(UserDetailActivity.this, "新密码与确认密码不一致", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(UserDetailActivity.this, "密码修改成功", Toast.LENGTH_LONG).show();
                    tvUserPassword.setText(newPassword);
                    dialog.dismiss();
                }
                // TODO: 2017/5/12 云端存储
                Student student = new Student();
                student.setPassword(newPassword);
                student.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.e("error", "修改密码成功");
                        } else {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void showAgeSelectDialog() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(simpleDateFormat.format(new Date())) - Integer.parseInt(tvUserAge.getText().toString().split("岁")[0]);
        new DatePickerDialog(this, this, year, 0, 1).show();
    }

    private void showSexSelectDialog() {
        final String items[] = {"男", "女"};
        final boolean item = isMale;
        isMale = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setIcon(R.drawable.icon_sex);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    isMale = true;
                } else {
                    isMale = false;
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (isMale) {
                    tvUserSex.setText("男");
                } else {
                    tvUserSex.setText("女");
                }
                // TODO: 2017/5/12 云端存储
                Student student = new Student();
                student.setSex(isMale);
                student.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(UserDetailActivity.this, "success", Toast.LENGTH_LONG).show();
                            Log.e("error", "change sex success");
                        } else {
                            Toast.makeText(UserDetailActivity.this, "failed", Toast.LENGTH_LONG).show();
                            Log.e("error", "change sex failed");
                        }
                    }
                });

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                isMale = item;
                if (isMale) {
                    tvUserSex.setText("男");
                } else {
                    tvUserSex.setText("女");
                }
            }
        });
        builder.create().show();
    }

    private void changUserName() {
        Intent changeNameIntent = new Intent(this, ChangeNameActivity.class);
        changeNameIntent.putExtra("name", tvUserName.getText().toString());
        startActivityForResult(changeNameIntent, REQUEST_CHANGE_NAME);
    }

    private void showIconSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.layout_dialog_select_photo, null);
        TextView selectPhoto = (TextView) view.findViewById(R.id.tv_from_photo);
        TextView selectCamera = (TextView) view.findViewById(R.id.tv_from_camera);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK, null);
                photoIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(photoIntent, REQUEST_SELECT_PHOTO);
                dialog.dismiss();
            }
        });
        selectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(cameraIntent, REQUEST_SELECT_CAMERA);
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());
                }
                break;
            case REQUEST_SELECT_CAMERA:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));
                }
                break;
            case REQUEST_CROP_PHOTO:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    mUserHeadIcon = extras.getParcelable("data");
                    if (mUserHeadIcon != null) {
                        savePictureToSDCard(mUserHeadIcon);
                        civUserIcon.setImageBitmap(mUserHeadIcon);
                        // TODO: 2017/5/12  云端存储
                        File file = new File(mPath + "head.jpg");
                        final BmobFile bmobFile = new BmobFile(file);
                        bmobFile.uploadblock(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(UserDetailActivity.this, "上传文件成功" + bmobFile.getFileUrl(), Toast.LENGTH_LONG).show();
                                    User user = new User();
                                    user.setIcon(bmobFile.getFileUrl());
                                    user.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Log.e("error", "success");
                                            } else {
                                                Log.e("error", "failed:" + e.getMessage());
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(UserDetailActivity.this, "上传文件失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onProgress(Integer value) {
                                Log.e("error", "progress=" + value);
                            }
                        });
                    }
                }
                break;
            case REQUEST_CHANGE_NAME:
                if (resultCode == 1) {
                    saveUsername2Bmob(data);
                }
                break;
            case REQUEST_SELECT_REGION:
                if (resultCode == 1) {
                    String province = data.getStringExtra(SelectRegionActivity.REGION_PROVINCE);
                    String city = data.getStringExtra(SelectRegionActivity.REGION_CITY);
                    String area = data.getStringExtra(SelectRegionActivity.REGION_AREA);
                    tvUserArea.setText(province + "," + city + "," + area);
                    saveArea2Bmob(province, city, area);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveUsername2Bmob(final Intent data) {
        Student student = new Student();
        student.setUsername(data.getStringExtra("username"));
        student.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.e("error", "success");
                    tvUserName.setText(data.getStringExtra("username"));
                } else {
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

    private void saveArea2Bmob(String province, String city, String area) {
        Student student = new Student();
        student.setArea(province + "," + city + "," + area);
        student.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(UserDetailActivity.this, "success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserDetailActivity.this, "failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void savePictureToSDCard(Bitmap userHeadIcon) {
        Log.e("error", "savePictureToSDCard");
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        File file = new File(mPath);
        file.mkdirs();
        String fileName = mPath + "head.jpg";
        try {
            fileOutputStream = new FileOutputStream(fileName);
            userHeadIcon.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cropPhoto(Uri uri) {
        Log.e("error", "cropPhoto");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        int nowYear = Integer.parseInt(simpleDateFormat.format(new Date()));
        tvUserAge.setText(nowYear - year + "岁");
        // TODO: 2017/5/12 云端存储
        Student student = new Student();
        student.setAge(nowYear - year + "岁");
        student.update(BmobUser.getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(UserDetailActivity.this, "success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserDetailActivity.this, "success", Toast.LENGTH_LONG).show();
                }
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
