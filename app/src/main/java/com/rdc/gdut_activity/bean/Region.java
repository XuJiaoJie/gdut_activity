package com.rdc.gdut_activity.bean;

public class Region {

    private Long mId;
    private Long mPid;
    private String mName;

    public Region(Long id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        this.mId = id;
    }

    public Long getPid() {
        return mPid;
    }

    public void setPid(Long pid) {
        this.mPid = pid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
