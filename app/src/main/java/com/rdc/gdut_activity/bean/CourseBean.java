package com.rdc.gdut_activity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseBean implements Parcelable{

    /**
     * dgksdm : 1431382
     * jxbmc : 物联网15(3),物联网15(4)
     * pkrs : 86
     * kcmc : 计算机网络
     * teaxms : 刘洪涛
     * xq : 4
     * jcdm : 0607
     * jxcdmc : 教1-310
     * zc : 9
     * kxh : 9
     * jxhjmc : 理论教学
     * flfzmc :
     * sknrjj :
     * pkrq : 2017-04-20
     * rownum_ : 1
     */

    private String kxh;
    private String jxhjmc;
    private String flfzmc;
    private String pkrq;
    private String rownum_;
    private String dgksdm;
    private String kcmc;//课程名称
    private String teaxms;//老师名字
    private String jxbmc;//教学班级
    private String zc;//周次
    private String jcdm;//上课时间 "0304"
    private String jcdm2;//上课时间 "03,04"
    private String xq;// 星期
    private String jxcdmc;//课室
    private String sknrjj;
    private String pkrs;//总人数

    protected CourseBean(Parcel in) {
        kxh = in.readString();
        jxhjmc = in.readString();
        flfzmc = in.readString();
        pkrq = in.readString();
        rownum_ = in.readString();
        dgksdm = in.readString();
        kcmc = in.readString();
        teaxms = in.readString();
        jxbmc = in.readString();
        zc = in.readString();
        jcdm = in.readString();
        jcdm2 = in.readString();
        xq = in.readString();
        jxcdmc = in.readString();
        sknrjj = in.readString();
        pkrs = in.readString();
    }

    public static final Creator<CourseBean> CREATOR = new Creator<CourseBean>() {
        @Override
        public CourseBean createFromParcel(Parcel in) {
            return new CourseBean(in);
        }

        @Override
        public CourseBean[] newArray(int size) {
            return new CourseBean[size];
        }
    };

    public String getDgksdm() {
        return dgksdm;
    }

    public void setDgksdm(String dgksdm) {
        this.dgksdm = dgksdm;
    }

    public String getJxbmc() {
        return jxbmc;
    }

    public void setJxbmc(String jxbmc) {
        this.jxbmc = jxbmc;
    }

    public String getPkrs() {
        return pkrs;
    }

    public void setPkrs(String pkrs) {
        this.pkrs = pkrs;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getTeaxms() {
        return teaxms;
    }

    public void setTeaxms(String teaxms) {
        this.teaxms = teaxms;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getJcdm() {
        return jcdm;
    }

    public void setJcdm(String jcdm) {
        this.jcdm = jcdm;
    }

    public String getJxcdmc() {
        return jxcdmc;
    }

    public void setJxcdmc(String jxcdmc) {
        this.jxcdmc = jxcdmc;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getKxh() {
        return kxh;
    }

    public void setKxh(String kxh) {
        this.kxh = kxh;
    }

    public String getJxhjmc() {
        return jxhjmc;
    }

    public void setJxhjmc(String jxhjmc) {
        this.jxhjmc = jxhjmc;
    }

    public String getFlfzmc() {
        return flfzmc;
    }

    public void setFlfzmc(String flfzmc) {
        this.flfzmc = flfzmc;
    }

    public String getSknrjj() {
        return sknrjj;
    }

    public void setSknrjj(String sknrjj) {
        this.sknrjj = sknrjj;
    }

    public String getPkrq() {
        return pkrq;
    }

    public void setPkrq(String pkrq) {
        this.pkrq = pkrq;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    @Override
    public String toString() {
        return kcmc + " 老师：" + teaxms + " 课室: " + jxcdmc + "  节数 " + jcdm + " 周次： " + zc + " 星期： " + xq + " 上课日期 " + pkrq;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kxh);
        dest.writeString(jxhjmc);
        dest.writeString(flfzmc);
        dest.writeString(pkrq);
        dest.writeString(rownum_);
        dest.writeString(dgksdm);
        dest.writeString(kcmc);
        dest.writeString(teaxms);
        dest.writeString(jxbmc);
        dest.writeString(zc);
        dest.writeString(jcdm);
        dest.writeString(jcdm2);
        dest.writeString(xq);
        dest.writeString(jxcdmc);
        dest.writeString(sknrjj);
        dest.writeString(pkrs);
    }
}
