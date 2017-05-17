package com.rdc.gdut_activity.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class ActivityInfoBean extends BmobObject implements Parcelable {
    private String mActivityName;
    private String mActivityType;
    private String mActivityHost;
    private String mActivityLocation;
    private String mActivityTime;
    private String mActivityDetail;
    private String mPublishTime;//可以删除
    private String mPublisherName;
    private String mPublisherIconUrl;
    private String mCheckStatus;//审核成功，不通过，审核中
    private String mCheckReason;
    private List<String> mImgUrlList;
    private HashMap<String, String> mFormDataMap;

    private BmobRelation mParticipant;//参与者
    private BmobRelation mVerifier;// 审核者
    private Publisher mPublisher;//发布者
    private static final String KEY_FORM_DATA_MAP = "FORM_DATA_MAP";

    public ActivityInfoBean() {
    }

    protected ActivityInfoBean(Parcel in) {
        setObjectId(in.readString());
        setCreatedAt(in.readString());
        mActivityName = in.readString();
        mActivityType = in.readString();
        mActivityHost = in.readString();
        mActivityLocation = in.readString();
        mActivityTime = in.readString();
        mActivityDetail = in.readString();
        mPublishTime = in.readString();
        mPublisherName = in.readString();
        mPublisherIconUrl = in.readString();
        mCheckStatus = in.readString();
        mCheckReason = in.readString();
        mImgUrlList = in.createStringArrayList();
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        mFormDataMap = (HashMap<String, String>) bundle.getSerializable(KEY_FORM_DATA_MAP);
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

    public Publisher getPublisher() {
        return mPublisher;
    }

    public void setPublisher(Publisher publisher) {
        mPublisher = publisher;
    }

    public BmobRelation getParticipant() {
        return mParticipant;
    }

    public void setParticipant(BmobRelation participant) {
        mParticipant = participant;
    }

    public BmobRelation getVerifier() {
        return mVerifier;
    }

    public void setVerifier(BmobRelation verifier) {
        mVerifier = verifier;
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

    public String getCheckStatus() {
        return mCheckStatus;
    }

    public void setCheckStatus(String checkStatus) {
        mCheckStatus = checkStatus;
    }

    public String getCheckReason() {
        return mCheckReason;
    }

    public void setCheckReason(String checkReason) {
        mCheckReason = checkReason;
    }

    public HashMap<String, String> getFormDataMap() {
        return mFormDataMap;
    }

    public void setFormDataMap(HashMap<String, String> formDataMap) {
        mFormDataMap = formDataMap;
    }

    public List<String> getImgUrlList() {
        return mImgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        mImgUrlList = imgUrlList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getObjectId());
        dest.writeString(this.getCreatedAt());
        dest.writeString(mActivityName);
        dest.writeString(mActivityType);
        dest.writeString(mActivityHost);
        dest.writeString(mActivityLocation);
        dest.writeString(mActivityTime);
        dest.writeString(mActivityDetail);
        dest.writeString(mPublishTime);
        dest.writeString(mPublisherName);
        dest.writeString(mPublisherIconUrl);
        dest.writeString(mCheckStatus);
        dest.writeString(mCheckReason);
        dest.writeStringList(mImgUrlList);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FORM_DATA_MAP, mFormDataMap);
        dest.writeBundle(bundle);
    }

    @Override
    public String toString() {
        return getActivityName() + getActivityDetail();
    }
}