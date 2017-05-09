package com.rdc.gdut_activity.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

public class ActivityInfoBean implements Parcelable {
    private String mActivityName;
    private String mActivityType;
    private String mActivityHost;
    private String mActivityLocation;
    private String mActivityTime;
    private String mActivityDetail;
    private String mCheckStatus;
    private String mPublishTime;
    private String mPublisherName;
    private String mPublisherIconUrl;
    private List<String> mImgUrlList;
    private HashMap<String, String> mFormDataMap;

    private static final String KEY_FORM_DATA_MAP = "FORM_DATA_MAP";


    public ActivityInfoBean() {
    }

    protected ActivityInfoBean(Parcel in) {
        mActivityName = in.readString();
        mActivityType = in.readString();
        mActivityHost = in.readString();
        mActivityLocation = in.readString();
        mActivityTime = in.readString();
        mActivityDetail = in.readString();
        mCheckStatus = in.readString();
        mPublishTime = in.readString();
        mPublisherName = in.readString();
        mPublisherIconUrl = in.readString();
        mImgUrlList = in.createStringArrayList();
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        mFormDataMap = (HashMap<String, String>) bundle.getSerializable(KEY_FORM_DATA_MAP);
    }

    public String getActivityName() {
        return mActivityName;
    }

    public void setActivityName(String activityName) {
        mActivityName = activityName;
    }

    public String getActivityType() {
        return mActivityType;
    }

    public void setActivityType(String activityType) {
        mActivityType = activityType;
    }

    public String getActivityHost() {
        return mActivityHost;
    }

    public void setActivityHost(String activityHost) {
        mActivityHost = activityHost;
    }

    public String getActivityLocation() {
        return mActivityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        mActivityLocation = activityLocation;
    }

    public String getActivityTime() {
        return mActivityTime;
    }

    public void setActivityTime(String activityTime) {
        mActivityTime = activityTime;
    }

    public String getActivityDetail() {
        return mActivityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        mActivityDetail = activityDetail;
    }

    public String getCheckStatus() {
        return mCheckStatus;
    }

    public void setCheckStatus(String checkStatus) {
        mCheckStatus = checkStatus;
    }

    public String getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(String publishTime) {
        mPublishTime = publishTime;
    }

    public String getPublisherName() {
        return mPublisherName;
    }

    public void setPublisherName(String publisherName) {
        mPublisherName = publisherName;
    }

    public String getPublisherIconUrl() {
        return mPublisherIconUrl;
    }

    public void setPublisherIconUrl(String publisherIconUrl) {
        mPublisherIconUrl = publisherIconUrl;
    }

    public List<String> getImgUrlList() {
        return mImgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        mImgUrlList = imgUrlList;
    }

    public HashMap<String, String> getFormDataMap() {
        return mFormDataMap;
    }

    public void setFormDataMap(HashMap<String, String> formDataMap) {
        mFormDataMap = formDataMap;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mActivityName);
        dest.writeString(mActivityType);
        dest.writeString(mActivityHost);
        dest.writeString(mActivityLocation);
        dest.writeString(mActivityTime);
        dest.writeString(mActivityDetail);
        dest.writeString(mCheckStatus);
        dest.writeString(mPublishTime);
        dest.writeString(mPublisherName);
        dest.writeString(mPublisherIconUrl);
        dest.writeStringList(mImgUrlList);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FORM_DATA_MAP, mFormDataMap);
        dest.writeBundle(bundle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActivityInfoBean> CREATOR = new Creator<ActivityInfoBean>() {
        @Override
        public ActivityInfoBean createFromParcel(Parcel in) {
            return new ActivityInfoBean(in);
        }

        @Override
        public ActivityInfoBean[] newArray(int size) {
            return new ActivityInfoBean[size];
        }
    };
}