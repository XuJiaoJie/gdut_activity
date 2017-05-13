package com.rdc.gdut_activity.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

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


    private File mTakePhotoFile;
    private PublishContract.Presenter mPresenter;
    private ActivityInfoBean mActivityInfoBean;
    private DetailImgUriAdapter mDetailImgUriAdapter;


    private String mObjId = "e511f55062"; //临时测试
    private static final int CODE_TAKE_PHOTO = 0;
    private static final int CODE_CHOOSE_PHOTO = 1;
    private static final String TAG = "PublishFragment";

    private Date mStartDate;
    private Date mEndDate;
    private List<Uri> mSelected; // 选择图片的 uri


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
        tmpLogin();
        mPresenter = new PublishPresenterImpl(this);
    }

    @Override
    protected void initView() {
        if (mDetailImgUriAdapter == null) {
            mDetailImgUriAdapter = new DetailImgUriAdapter();
        }
        mNineGridImgView.setAdapter(mDetailImgUriAdapter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mBaseActivity,
                R.array.activity_type_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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
//                                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.photo_grid_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .theme(R.style.CustomMatisseTheme)
                .forResult(CODE_CHOOSE_PHOTO);

//                                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
//                                startActivityForResult(pickPhotoIntent, CODE_CHOOSE_PHOTO);
    }

    private void takePhoto() {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mTakePhotoFile = getPhotoFile();
        boolean canTakePhoto = mTakePhotoFile != null
                && takePhotoIntent.resolveActivity(mBaseActivity.getPackageManager()) != null;
//        mIvImportPhoto.setEnabled(canTakePhoto);
        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mTakePhotoFile);
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(takePhotoIntent, CODE_TAKE_PHOTO);
        } else {
            showToast("您的手机无法拍照，请检查权限");
        }
    }

    private File getPhotoFile() {
        File externalFileDir = mBaseActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFileDir == null) {
            return null;
        }
        return new File(externalFileDir, getPhotoFilename());
    }

    private String getPhotoFilename() {
        return "IMG_" + System.currentTimeMillis() + ".jpg";
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CODE_TAKE_PHOTO:

                break;
            case CODE_CHOOSE_PHOTO:
                mSelected = Matisse.obtainResult(data);
                updateNineGridView();
                Log.d(TAG, "onActivityResult mSelected: " + mSelected);
                break;
        }
    }

    private void updateNineGridView() {
        mNineGridImgView.setImagesData(mSelected);
    }

    @OnClick({R.id.tv_start_time, R.id.iv_import_photo, R.id.btn_set_form_data, R.id.btn_preview, R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                // TODO: 2017/5/13 0013 时间选择器
                onSetTimeClicked();
                break;
            case R.id.iv_import_photo:
                onImportPhotoClicked();
                break;
            case R.id.btn_set_form_data:
                // TODO: 2017/5/13 0013 表单信息

                break;
            case R.id.btn_preview:
                testFetchData(mObjId);
                break;
            case R.id.btn_publish:
                mPresenter.uploadData(saveSampleData());
                break;
        }
    }

    private void onSetTimeClicked() {
        new AlertDialog.Builder(mBaseActivity)
                .setItems(new String[]{"设置开始时间", "设置结束时间"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                showTimePickerView(mTvStartTime);
                                break;
                            case 1:
                                showTimePickerView(mTvEndTime);
                                break;
                        }
                    }
                }).show();

    }

    private void showTimePickerView(final TextView textView) {
        // TODO: 2017/5/13 0013 选中日期判断 后面的日期不能大于当前日期
        TimePickerView pickerView = new TimePickerView.Builder(mBaseActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                textView.setText(simpleDateFormat.format(date));
                mIvTimeIcon.setImageResource(R.drawable.ic_has_set_time);
                textView.setTextColor(getResources().getColor(R.color.greenyellow));
//                mStartDate = textView.getText().toString();
//                if (mStartDate != null && mEndDate != null && mStartDate.after(mEndDate)) {
//                    showToast("开始时间不能晚于结束时间喔");
//                }


            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .setDate(Calendar.getInstance())
                .build();
        pickerView.show();
    }

    private void testFetchData(String objId) {
        BmobQuery<ActivityInfoBean> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(objId, new QueryListener<ActivityInfoBean>() {
            @Override
            public void done(ActivityInfoBean bean, BmobException e) {
                if (e == null) {
                    mActivityInfoBean = bean;
                    showToast("获取对象成功 mActivityInfoBean.getActivityDetail() " + mActivityInfoBean.getActivityDetail());
                } else {
                    showToast("获取对象失败");
                }
            }
        });

    }


    private ActivityInfoBean saveData() {
        ActivityInfoBean bean = new ActivityInfoBean();
        bean.setActivityDetail(mEtActivityDetail.getText().toString());
        bean.setActivityHost(mEtActivityHost.getText().toString());
        bean.setActivityLocation(mEtActivityLocation.getText().toString());
        bean.setActivityTime(mTvStartTime.getText().toString());
        bean.setActivityName(mEtActivityName.getText().toString());
        bean.setCheckStatus("未审核");
        bean.setCheckReason("学生会的人很忙");

        mCbClass.isChecked();
        return bean;
    }


    private ActivityInfoBean saveSampleData() {
        Publisher publisher = BmobUser.getCurrentUser(Publisher.class);

        ActivityInfoBean bean = new ActivityInfoBean();

        bean.setActivityName("研发中心招新宣讲会开始啦啦啦  ");
        bean.setActivityTime("2017-5-8 19:00");
        bean.setActivityLocation("广东工业大学工一学术报告厅   ");
        bean.setPublishTime("2017-5-7 12:00");
        bean.setPublisherName("广东工业大学团委");
        bean.setActivityType("讲座");
        bean.setActivityHost("研发中心工作室");
        bean.setActivityDetail("多久啊看来大家啊克里夫剪啊开房间里的咖啡机的刻录机福克斯的减肥快圣诞分" +
                "公司感受感受的分公司人发过过生日果然果然果然够节咖啡馆的精神科更加深刻搭公交可视对讲");
        bean.setCheckStatus("未审核");
        bean.setCheckReason("没空");
        bean.setPublisher(publisher);

        List<String> picList = new ArrayList<>();
        picList.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/10762c593798466a.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/233a5f70512befcc.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
        picList.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("手机", "18897873232");
//        hashMap.put("姓名", "你猜");
//        hashMap.put("地址", "广工庙");
//        bean.setFormDataMap(hashMap);
        bean.setImgUrlList(picList);
        return bean;
    }

    private void tmpLogin() {
        BmobUser.loginByAccount("2", "2", new LogInListener<Publisher>() {
            @Override
            public void done(Publisher publisher, BmobException e) {
                if (e == null) {
                    showToast("登录成功");
                } else {
                    showToast("登录失败");
                }
            }
        });
    }

    @Override
    public void onSuccess(String objId) {
        showToast("发布成功");
        mObjId = objId;
    }

    @Override
    public void onFailed(String errorMsg) {
        showToast("发布失败，" + errorMsg);
    }
}
