package com.rdc.gdut_activity.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.UserActivitiesListAdapter;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.SignUpBean;
import com.rdc.gdut_activity.bean.Student;
import com.rdc.gdut_activity.view.TopBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UserActivitiesActivity extends BaseActivity implements TopBar.topbarClickListner {

    @InjectView(R.id.lv_user_activities)
    ListView lvUserActivities;
    @InjectView(R.id.tb_user_activities)
    TopBar tbUserActivities;

    private List<ActivityInfoBean> mActivityInfoList;
    private UserActivitiesListAdapter mUserActivitiesListAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_user_activities;
    }

    @Override
    protected void initData() {
        mActivityInfoList = new ArrayList<>();
        // TODO: 2017/5/13 云端获取数据
        final BmobQuery<SignUpBean> signUpBeanBmobQuery = new BmobQuery<>();
        Student student = new Student();
        student.setObjectId(BmobUser.getCurrentUser().getObjectId());
        signUpBeanBmobQuery.addWhereEqualTo("mStudent", new BmobPointer(student));
        signUpBeanBmobQuery.include("mActivityBean.mPublisher");
        signUpBeanBmobQuery.findObjects(new FindListener<SignUpBean>() {
            @Override
            public void done(List<SignUpBean> list, BmobException e) {
                if (e == null) {
                    if (list != null) {
                        for (SignUpBean signUpBean : list) {
                            mActivityInfoList.add(signUpBean.getActivityBean());
                            Log.e("error", signUpBean.getActivityBean().getActivityDetail());
                        }
                        mUserActivitiesListAdapter.notifyDataSetChanged();
                        Log.e("error", "query signUpBean success");
                    }
                } else {
                    Log.e("error", "query signUpBean failed");
                }
            }
        });

//        BmobQuery<ActivityInfoBean> activityInfoBeanBmobQuery = new BmobQuery<>();
//        Student student = new Student();
//        student.setObjectId(BmobUser.getCurrentUser().getObjectId());
//        activityInfoBeanBmobQuery.addWhereRelatedTo("mStudent", new BmobPointer(student));
//        activityInfoBeanBmobQuery.findObjects(new FindListener<ActivityInfoBean>() {
//            @Override
//            public void done(List<ActivityInfoBean> list, BmobException e) {
//                if (e == null) {
//                    for (ActivityInfoBean activityInfoBean : list) {
//                        activityInfoBean.setPublisherIconUrl(activityInfoBean.getPublisherIconUrl());
//                        activityInfoBean.setPublisherName(activityInfoBean.getPublisherName());
//                        activityInfoBean.setPublishTime(activityInfoBean.getPublishTime());
//                        activityInfoBean.setImgUrlList(activityInfoBean.getImgUrlList());
//                        activityInfoBean.setActivityName(activityInfoBean.getActivityName());
//                        activityInfoBean.setActivityTime(activityInfoBean.getPublishTime());
//                        activityInfoBean.setActivityLocation(activityInfoBean.getActivityLocation());
//                        mActivityInfoList.add(activityInfoBean);
//                    }
//                    mUserActivitiesListAdapter.notifyDataSetChanged();
//                    Log.e("error", "findObjects success" + list.size());
//                } else {
//                    Log.e("error", "findObjects failed");
//                }
//            }
//        });


//        BmobQuery<ActivityInfoBean> activityInfoBeanBmobQuery = new BmobQuery<>();
//        activityInfoBeanBmobQuery.addWhereEqualTo("mCheckStatus", "审核通过");
//        activityInfoBeanBmobQuery.setLimit(10);
//        activityInfoBeanBmobQuery.findObjects(new FindListener<ActivityInfoBean>() {
//            @Override
//            public void done(List<ActivityInfoBean> list, BmobException e) {
//                if (e == null) {
//                    for (ActivityInfoBean activityInfoBean : list) {
//                        activityInfoBean.setPublisherIconUrl(activityInfoBean.getPublisherIconUrl());
//                        activityInfoBean.setPublisherName(activityInfoBean.getPublisherName());
//                        activityInfoBean.setPublishTime(activityInfoBean.getPublishTime());
//                        activityInfoBean.setImgUrlList(activityInfoBean.getImgUrlList());
//                        activityInfoBean.setActivityName(activityInfoBean.getActivityName());
//                        activityInfoBean.setActivityTime(activityInfoBean.getPublishTime());
//                        activityInfoBean.setActivityLocation(activityInfoBean.getActivityLocation());
//                        mActivityInfoList.add(activityInfoBean);
//                    }
//                    mUserActivitiesListAdapter.notifyDataSetChanged();
//                    Log.e("error", "findObjects success" + list.size());
//                } else {
//                    Log.e("error", "findObjects failed");
//                }
//            }
//        });

        mUserActivitiesListAdapter = new UserActivitiesListAdapter(this, mActivityInfoList);
        lvUserActivities.setAdapter(mUserActivitiesListAdapter);
    }

    @Override
    protected void initView() {
        tbUserActivities.setButtonBackground(R.drawable.icon_back, 0);
        tbUserActivities.setOnTopbarClickListener(this);
        lvUserActivities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivitiesActivity.this);
                final AlertDialog dialog = builder.create();
                View v = View.inflate(UserActivitiesActivity.this, R.layout.layout_dialog_deal_activity, null);
                LinearLayout share = (LinearLayout) v.findViewById(R.id.ll_share);
                LinearLayout clear = (LinearLayout) v.findViewById(R.id.ll_clear);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = mActivityInfoList.get(position).getActivityHost() + "\n" +
                                mActivityInfoList.get(position).getActivityName() + "\n" +
                                mActivityInfoList.get(position).getActivityTime() + "\n" +
                                mActivityInfoList.get(position).getActivityDetail();
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboardManager.setText(content);
                        Toast.makeText(UserActivitiesActivity.this, "分享内容已经复制到剪切板，可以使用粘贴", Toast.LENGTH_LONG).show();
                        new Thread() {
                            @Override
                            public void run() {
                                String url = "";
                                if (mActivityInfoList.get(position).getImgUrlList() != null && mActivityInfoList.get(position).getImgUrlList().size() > 0) {
                                    url = mActivityInfoList.get(position).getImgUrlList().get(0);

                                }
                                prepareShare(url);
                            }
                        }.start();
                        dialog.dismiss();
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivityInfoList.remove(position);
                        mUserActivitiesListAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.setView(v);
                dialog.show();
                return true;
            }
        });
        lvUserActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserActivitiesActivity.this, DetailsVerifyActivity.class);
                intent.putExtra("DetailsVerifyActivity", (Parcelable) mActivityInfoList.get(position));
                intent.putExtra("ActivityTitle", "活动详情");
                startActivity(intent);
            }
        });
    }

    private void prepareShare(String url) {
        Bitmap bitmap = getHttpBitmap(url);
        String path = savePicture(bitmap);
        share(path);
    }

    private void share(String path) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (path == null || path.equals("")) {
            intent.setType("type/plain");
        } else {
            File file = new File(path);
            if (file != null && file.exists() && file.isFile()) {
                intent.setType("image/*");
                Uri uri = Uri.fromFile(file);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
            }
        }

        startActivity(Intent.createChooser(intent, "分享到"));
    }

    private String savePicture(Bitmap bitmap) {
        String pictureName = "/mnt/sdcard/" + "share.jpg";
        File file = new File(pictureName);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pictureName;
    }

    private Bitmap getHttpBitmap(String url) {
        Bitmap bitmap = null;
        try {
            URL pictureUrl = new URL(url);
            InputStream inputStream = pictureUrl.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
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

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
