package com.rdc.gdut_activity.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rdc.gdut_activity.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PublisherActivity extends AppCompatActivity {

    @InjectView(R.id.et_activity_name)
    EditText mEtActivityName;
    @InjectView(R.id.sp_type)
    Spinner mSpType;
    @InjectView(R.id.et_activity_host)
    EditText mEtActivityHost;
    @InjectView(R.id.et_activity_location)
    EditText mEtActivityLocation;
    @InjectView(R.id.tv_time)
    TextView mTvTime;
    @InjectView(R.id.iv_import_photo)
    ImageView mIvTakePhoto;
    @InjectView(R.id.et_activity_detail)
    EditText mEtActivityDetail;
    @InjectView(R.id.btn_set_form_data)
    Button mBtnSetFormData;
    @InjectView(R.id.btn_preview)
    Button mBtnPreview;
    @InjectView(R.id.btn_publish)
    Button mBtnPublish;

    private static final int CODE_TAKE_PHOTO = 0;
    private static final int CODE_PICK_PHOTO = 1;
    private File mTakePhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_publish);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.tv_time, R.id.iv_import_photo, R.id.btn_preview, R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:

                break;
            case R.id.iv_import_photo:
                onImportPhotoClicked();
                break;
            case R.id.btn_preview:

                break;
            case R.id.btn_publish:

                break;
        }
    }

    private void onImportPhotoClicked() {
        new AlertDialog.Builder(this)
                .setItems(new String[]{"拍摄", "从相册中选择"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhoto();
                            case 1:
                                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
                                startActivityForResult(pickPhotoIntent, CODE_PICK_PHOTO);
                        }
                    }
                }).show();
    }

    private void takePhoto() {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mTakePhotoFile = getPhotoFile();
        boolean canTakePhoto = mTakePhotoFile != null
                && takePhotoIntent.resolveActivity(getPackageManager()) != null;
        mIvTakePhoto.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mTakePhotoFile);
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(takePhotoIntent, CODE_TAKE_PHOTO);
        }
    }

    private File getPhotoFile() {
        File externalFileDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFileDir == null) {
            return null;
        }
        return new File(externalFileDir, getPhotoFilename());
    }

    private String getPhotoFilename() {
        return "IMG_" + System.currentTimeMillis() + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CODE_TAKE_PHOTO:

                break;
            case CODE_PICK_PHOTO:

                break;
        }
    }
}
