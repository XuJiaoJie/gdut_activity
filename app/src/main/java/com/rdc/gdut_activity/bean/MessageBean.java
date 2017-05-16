package com.rdc.gdut_activity.bean;

import java.io.Serializable;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class MessageBean implements Serializable {

    private String icon;
    private String message;
    private String name;
    private String objectid;
    private String time;

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MessageBean) {
            MessageBean msg = (MessageBean) obj;
            return this.objectid.equals(msg.objectid);
        }
        return super.equals(obj);
    }
}
