package com.rdc.gdut_activity.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.adapter.DetailImgUriAdapter;
import com.rdc.gdut_activity.base.BaseFragment;
import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.bean.Publisher;
import com.rdc.gdut_activity.contract.PublishContract;
import com.rdc.gdut_activity.presenter.PublishPresenterImpl;
import com.rdc.gdut_activity.ui.DetailsVerifyActivity;
import com.rdc.gdut_activity.utils.CapturePhotoHelper;
import com.rdc.gdut_activity.utils.FolderManager;
import com.rdc.gdut_activity.utils.PictureUtil;
import com.rdc.gdut_activity.utils.ProgressDialogUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;

import static android.app.Activity.RESULT_OK;

public class PublishFragment extends BaseFragment implements PublishContract.View {

    @InjectView(R.id.et_activity_name)
    EditText mEtActivityName;
    @InjectView(R.id.sp_type)
    Spinner mSpType;
    @InjectView(R.id.et_activity_host)
    EditText mEtActivityHost;
    @InjectView(R.id.et_activity_location)
    EditText mEtActivityLocation;
    @InjectView(R.id.tv_start_time)
    TextView mTvStartTime;
    @InjectView(R.id.iv_import_photo)
    ImageView mIvImportPhoto;
    @InjectView(R.id.et_activity_detail)
    EditText mEtActivityDetail;
    @InjectView(R.id.btn_set_form_data)
    Button mBtnSetFormData;
    @InjectView(R.id.btn_preview)
    Button mBtnPreview;
    @InjectView(R.id.btn_publish)
    Button mBtnPublish;
    @InjectView(R.id.iv_time_icon)
    ImageView mIvTimeIcon;
    @InjectView(R.id.ll_set_time)
    LinearLayout mLlSetTime;
    @InjectView(R.id.nine_grid_img_view)
    NineGridImageView mNineGridImgView;
    @InjectView(R.id.cb_name)
    CheckBox mCbName;
    @InjectView(R.id.cb_college)
    CheckBox mCbCollege;
    @InjectView(R.id.cb_phone)
    CheckBox mCbPhone;
    @InjectView(R.id.cb_profession)
    CheckBox mCbProfession;
    @InjectView(R.id.cb_email)
    CheckBox mCbEmail;
    @InjectView(R.id.cb_class)
    CheckBox mCbClass;
    @InjectView(R.id.tv_end_time)
    TextView mTvEndTime;

    private static final String TAG = "PublishFragment";
    private static final int CODE_CHOOSE_PHOTO = 1;
    private static final int CODE_SHOW_PHOTO = 2;
    private PublishContract.Presenter mPresenter;
    private DetailImgUriAdapter mDetailImgUriAdapter;
    private HashMap<String, String> mFormData;
    private List<Uri> mSelected; // 选择图片的 uri
    private CapturePhotoHelper mCapturePhotoHelper;
    private Date mStartDate;
    private Date mEndDate;

    public static PublishFragment newInstance() {
        Bundle args = new Bundle();
        PublishFragment fragment = new PublishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFormData = new HashMap<>();
        mPresenter = new PublishPresenterImpl(this);
    }

    @Override
    protected void initView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mBaseActivity,
                R.array.activity_type_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(adapter);
        mSpType.setSelection(0);
    }

    @Override
    protected void setListener() {

    }

    private void onImportPhotoClicked() {
        new AlertDialog.Builder(mBaseActivity)
                .setItems(new String[]{"拍摄", "从相册中选择"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhoto();
                                break;
                            case 1:
                                choosePhoto();
                                break;
                        }
                    }
                }).show();
    }

    private void choosePhoto() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .theme(R.style.CustomMatisseTheme)
                .forResult(CODE_CHOOSE_PHOTO);
    }

    private void takePhoto() {
        if (mCapturePhotoHelper == null) {
            mCapturePhotoHelper = new CapturePhotoHelper(this, FolderManager.getPhotoFolder());
        }
        mCapturePhotoHelper.capture();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CapturePhotoHelper.CAPTURE_PHOTO_REQUEST_CODE:
                handleCaptureResult();
                break;
            case CODE_CHOOSE_PHOTO:
                List<Uri> list = new ArrayList<>();
                if (mSelected != null) {
                    list.addAll(mSelected);
                }
                List<Uri> uris = Matisse.obtainResult(data);
                for (Uri uri : uris) {
                    //避免重复
                    if (!list.contains(uri)) {
                        list.add(uri);
                    }
                }
                mSelected = list;
                updateNineGridView();
                break;
        }
    }

    private void handleCaptureResult() {
        Uri photoFileUri = mCapturePhotoHelper.getPhotoUri();
        if (photoFileUri != null) {
            File file = mCapturePhotoHelper.getPhotoFile();
//            PictureUtil.compressBitmap(file.getPath(), file.getPath(), 1080);
            PictureUtil.compressImage(file.getPath(), 600, 800, 700);
            List<Uri> tmpList = new ArrayList<>();
            if (mSelected != null) {
                tmpList.addAll(mSelected);
            }
            mSelected = tmpList;
            mSelected.add(photoFileUri);
            updateNineGridView();
        } else {
            showToast("返回数据有误");
        }
    }

    private void updateNineGridView() {
        if (mDetailImgUriAdapter == null) {
            mDetailImgUriAdapter = new DetailImgUriAdapter();
            mNineGridImgView.setAdapter(mDetailImgUriAdapter);
        }
        while (mSelected.size() > 9) {
            mSelected.remove(0);
        }
        mNineGridImgView.setImagesData(mSelected);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.iv_import_photo, R.id.btn_set_form_data, R.id.btn_preview, R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                showTimePickerView(mTvStartTime);
                break;
            case R.id.tv_end_time:
                showTimePickerView(mTvEndTime);
                break;
            case R.id.iv_import_photo:
                onImportPhotoClicked();
                break;
            case R.id.btn_set_form_data:
                break;
            case R.id.btn_preview:
                ActivityInfoBean previewBean = saveActivityData(null);
                Intent intent = DetailsVerifyActivity.newIntent(mBaseActivity, previewBean, (ArrayList<Uri>) mSelected, "预览", false);
                startActivity(intent);
                break;
            case R.id.btn_publish:
                if (!checkData()) {
                    return;
                }
                if (mSelected == null || mSelected.isEmpty()) {
                    ActivityInfoBean bean = saveActivityData(null);
                    mPresenter.uploadData(bean);
                    return;
                }
                //如果有图片，在图片完成上传之后，会调用 saveActivityData
                uploadImg();
                break;
        }
    }


    private void showTimePickerView(final TextView textView) {
        TimePickerView pickerView = new TimePickerView.Builder(mBaseActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                mIvTimeIcon.setImageResource(R.drawable.ic_has_set_time);
                textView.setTextColor(getResources().getColor(R.color.mediumseagreen));
                if (textView.getId() == R.id.tv_start_time) {
                    mStartDate = date;
                    textView.setText(simpleDateFormat.format(date));
                } else if (textView.getId() == R.id.tv_end_time) {
                    mEndDate = date;
                    String str = "至 " + simpleDateFormat.format(date);
                    textView.setText(str);
                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .setDate(Calendar.getInstance())
                .build();
        pickerView.show();
    }

    private boolean checkData() {
        if (mStartDate == null) {
            showToast("请设置活动开始时间");
            return false;
        }
        if (mEndDate == null) {
            showToast("请设置活动结束时间`");
            return false;
        }
        if (mStartDate.getTime() > mEndDate.getTime()) {
            showToast("起始时间不能晚于结束时间");
            return false;
        }
        if (TextUtils.isEmpty(mEtActivityName.getText())) {
            showToast("活动名称不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mEtActivityHost.getText())) {
            showToast("活动主办方不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mEtActivityLocation.getText())) {
            showToast("活动地点不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mEtActivityDetail.getText())) {
            showToast("详情不能为空");
            return false;
        }
        return true;
    }

    private ActivityInfoBean saveActivityData(List<String> imgUrl) {
        ActivityInfoBean bean = new ActivityInfoBean();
        Publisher publisher = BmobUser.getCurrentUser(Publisher.class);
        bean.setPublisher(publisher);
        bean.setActivityName(mEtActivityName.getText().toString());
        bean.setActivityType(mSpType.getSelectedItem().toString());
        bean.setActivityHost(mEtActivityHost.getText().toString());
        bean.setActivityLocation(mEtActivityLocation.getText().toString());
        bean.setActivityDetail(mEtActivityDetail.getText().toString());
        bean.setActivityTime(mTvStartTime.getText().toString() + mTvEndTime.getText().toString());
        saveCheckboxStatus();
        bean.setFormDataMap(mFormData);
        if (imgUrl != null) {
            bean.setImgUrlList(imgUrl);
        } else {
            bean.setImgUrlList(null);
        }
        bean.setCheckStatus("未审核");
        bean.setCheckReason("");
        return bean;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadImg() {
        final String[] paths = new String[mSelected.size()];
        for (int i = 0; i < mSelected.size(); i++) {
            paths[i] = PictureUtil.handleImageOnKitKat(mBaseActivity, mSelected.get(i));
            PictureUtil.compressImage(paths[i], 600, 800, 800);
        }
        doUpLoad(paths);
    }

    private void doUpLoad(final String[] paths) {
        ProgressDialogUtil.showProgressDialog(mBaseActivity, "上传图片");
        BmobFile.uploadBatch(paths, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                if (urls.size() == paths.length) {
                    ProgressDialogUtil.dismiss();
                    mPresenter.uploadData(saveActivityData(urls));
                }
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）
                String str = "总上传图片数:" + total + "\n第 " + curIndex + " 张图片正在上传";
                ProgressDialogUtil.setMsg(str);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                ProgressDialogUtil.dismiss();
                showToast("上传失败,状态码：" + statuscode + "错误信息:" + errormsg);
            }
        });
    }

    private void saveCheckboxStatus() {
        mFormData.clear();
        if (mCbClass.isChecked()) {
            mFormData.put("班级", "");
        }
        if (mCbCollege.isChecked()) {
            mFormData.put("学院", "");
        }
        if (mCbEmail.isChecked()) {
            mFormData.put("邮箱", "");
        }
        if (mCbName.isChecked()) {
            mFormData.put("姓名", "");
        }
        if (mCbPhone.isChecked()) {
            mFormData.put("手机号码", "");
        }

        if (mCbProfession.isChecked()) {
            mFormData.put("专业", "");
        }
    }

    @Override
    public void onSuccess(String objId) {
        showToast("发布成功");
    }

    @Override
    public void onFailed(String errorMsg) {
        showToast("发布失败，" + errorMsg);
    }
}
