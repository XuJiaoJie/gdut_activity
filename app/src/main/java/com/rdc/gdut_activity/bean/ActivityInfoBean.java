package com.rdc.gdut_activity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityInfoBean implements Parcelable {
    private String mActivityName;
    private String mActivityType;
    private String mActivityHost;
    private String mActivityLocation;
    private String mActivityTime;
    private String mActivityDetail;
    private String mFormData;
    private String mCheckStatus;

    public ActivityInfoBean() {
    }

    public ActivityInfoBean(String activityName, String activityType,
                            String activityHost, String activityLocation,
                            String activityTime, String activityDetail,
                            String formData, String checkStatus) {
        mActivityName = activityName;
        mActivityType = activityType;
        mActivityHost = activityHost;
        mActivityLocation = activityLocation;
        mActivityTime = activityTime;
        mActivityDetail = activityDetail;
        mFormData = formData;
        mCheckStatus = checkStatus;
    }

    protected ActivityInfoBean(Parcel in) {
        mActivityName = in.readString();
        mActivityType = in.readString();
        mActivityHost = in.readString();
        mActivityLocation = in.readString();
        mActivityTime = in.readString();
        mActivityDetail = in.readString();
        mFormData = in.readString();
        mCheckStatus = in.readString();
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

    public String getFormData() {
        return mFormData;
    }

    public void setFormData(String formData) {
        mFormData = formData;
    }

    public String getCheckStatus() {
        return mCheckStatus;
    }

    public void setCheckStatus(String checkStatus) {
        mCheckStatus = checkStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mActivityName);
        dest.writeString(mActivityType);
        dest.writeString(mActivityHost);
        dest.writeString(mActivityLocation);
        dest.writeString(mActivityTime);
        dest.writeString(mActivityDetail);
        dest.writeString(mFormData);
        dest.writeString(mCheckStatus);
    }
}
