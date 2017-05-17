package com.rdc.gdut_activity.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by PC on 2017/5/16.
 */

public class ScoreBean {

    /**
     * total : 15
     * rows : [{"xnxqmc":"2016秋季","wpjbz":"","ksxzdm":"0","rwdm":"","bz":"","xnxqdm":"201601","kcdm":"00920","kcdlmc":"公共基础课","rownum_":"1","cjfsmc":"百分制","isactive":"1","cjbzmc":"","zxs":"32","xf":"2","xdfsmc":"必修","kcflmc":"","xsbh":"3115005289","xsdm":"3115005289","cjjd":"4.4","ksxzmc":"正常考试","xsxm":"许教杰","kcbh":"TMP0920","cjdm":"0-7113061","wzcbz":"","zcj":"94","wzc":"0","kcmc":"大学物理(2)","wpj":"0"}]
     */

    private int total;
    private List<RowsBean> rows;

    public static ScoreBean objectFromData(String str) {

        return new Gson().fromJson(str, ScoreBean.class);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public class RowsBean {
        /**
         * xnxqmc : 2016秋季
         * wpjbz :
         * ksxzdm : 0
         * rwdm :
         * bz :
         * xnxqdm : 201601
         * kcdm : 00920
         * kcdlmc : 公共基础课
         * rownum_ : 1
         * cjfsmc : 百分制
         * isactive : 1
         * cjbzmc :
         * zxs : 32
         * xf : 2
         * xdfsmc : 必修
         * kcflmc :
         * xsbh : 3115005289
         * xsdm : 3115005289
         * cjjd : 4.4
         * ksxzmc : 正常考试
         * xsxm : 许教杰
         * kcbh : TMP0920
         * cjdm : 0-7113061
         * wzcbz :
         * zcj : 94
         * wzc : 0
         * kcmc : 大学物理(2)
         * wpj : 0
         */

        private String xnxqmc;
        private String wpjbz;
        private String ksxzdm;
        private String rwdm;
        private String bz;
        private String xnxqdm;
        private String kcdm;
        private String kcdlmc;
        private String rownum_;
        private String cjfsmc;
        private String isactive;
        private String cjbzmc;
        private String zxs;
        private String xf;
        private String xdfsmc;
        private String kcflmc;
        private String xsbh;
        private String xsdm;
        private String cjjd;
        private String ksxzmc;
        private String xsxm;
        private String kcbh;
        private String cjdm;
        private String wzcbz;
        private String zcj;
        private String wzc;
        private String kcmc;
        private String wpj;

        public RowsBean objectFromData(String str) {

            return new Gson().fromJson(str, RowsBean.class);
        }

        public String getXnxqmc() {
            return xnxqmc;
        }

        public void setXnxqmc(String xnxqmc) {
            this.xnxqmc = xnxqmc;
        }

        public String getWpjbz() {
            return wpjbz;
        }

        public void setWpjbz(String wpjbz) {
            this.wpjbz = wpjbz;
        }

        public String getKsxzdm() {
            return ksxzdm;
        }

        public void setKsxzdm(String ksxzdm) {
            this.ksxzdm = ksxzdm;
        }

        public String getRwdm() {
            return rwdm;
        }

        public void setRwdm(String rwdm) {
            this.rwdm = rwdm;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getXnxqdm() {
            return xnxqdm;
        }

        public void setXnxqdm(String xnxqdm) {
            this.xnxqdm = xnxqdm;
        }

        public String getKcdm() {
            return kcdm;
        }

        public void setKcdm(String kcdm) {
            this.kcdm = kcdm;
        }

        public String getKcdlmc() {
            return kcdlmc;
        }

        public void setKcdlmc(String kcdlmc) {
            this.kcdlmc = kcdlmc;
        }

        public String getRownum_() {
            return rownum_;
        }

        public void setRownum_(String rownum_) {
            this.rownum_ = rownum_;
        }

        public String getCjfsmc() {
            return cjfsmc;
        }

        public void setCjfsmc(String cjfsmc) {
            this.cjfsmc = cjfsmc;
        }

        public String getIsactive() {
            return isactive;
        }

        public void setIsactive(String isactive) {
            this.isactive = isactive;
        }

        public String getCjbzmc() {
            return cjbzmc;
        }

        public void setCjbzmc(String cjbzmc) {
            this.cjbzmc = cjbzmc;
        }

        public String getZxs() {
            return zxs;
        }

        public void setZxs(String zxs) {
            this.zxs = zxs;
        }

        public String getXf() {
            return xf;
        }

        public void setXf(String xf) {
            this.xf = xf;
        }

        public String getXdfsmc() {
            return xdfsmc;
        }

        public void setXdfsmc(String xdfsmc) {
            this.xdfsmc = xdfsmc;
        }

        public String getKcflmc() {
            return kcflmc;
        }

        public void setKcflmc(String kcflmc) {
            this.kcflmc = kcflmc;
        }

        public String getXsbh() {
            return xsbh;
        }

        public void setXsbh(String xsbh) {
            this.xsbh = xsbh;
        }

        public String getXsdm() {
            return xsdm;
        }

        public void setXsdm(String xsdm) {
            this.xsdm = xsdm;
        }

        public String getCjjd() {
            return cjjd;
        }

        public void setCjjd(String cjjd) {
            this.cjjd = cjjd;
        }

        public String getKsxzmc() {
            return ksxzmc;
        }

        public void setKsxzmc(String ksxzmc) {
            this.ksxzmc = ksxzmc;
        }

        public String getXsxm() {
            return xsxm;
        }

        public void setXsxm(String xsxm) {
            this.xsxm = xsxm;
        }

        public String getKcbh() {
            return kcbh;
        }

        public void setKcbh(String kcbh) {
            this.kcbh = kcbh;
        }

        public String getCjdm() {
            return cjdm;
        }

        public void setCjdm(String cjdm) {
            this.cjdm = cjdm;
        }

        public String getWzcbz() {
            return wzcbz;
        }

        public void setWzcbz(String wzcbz) {
            this.wzcbz = wzcbz;
        }

        public String getZcj() {
            return zcj;
        }

        public void setZcj(String zcj) {
            this.zcj = zcj;
        }

        public String getWzc() {
            return wzc;
        }

        public void setWzc(String wzc) {
            this.wzc = wzc;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getWpj() {
            return wpj;
        }

        public void setWpj(String wpj) {
            this.wpj = wpj;
        }
    }
}
