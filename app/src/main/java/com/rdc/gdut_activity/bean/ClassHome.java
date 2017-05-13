package com.rdc.gdut_activity.bean;

import java.util.List;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class ClassHome {

    /**
     * total : 3
     * rows : [{"kcrwdm":"1052972","pkrs":"1387","jxbdm":"1065721","kcptdm":"101230539","xmmc":"英语免修","kcdm":"00931","kcmc":"大学英语(4)","rwdm":"1012707","xbyqdm":"","rs1":"","rs2":"","wyfjdm":"01","kkxqdm":"1","zxs":"64","xf":"4","kcflmc":"","teaxm":"","jxbrs":"1078"},{"kcrwdm":"1057954","pkrs":"1387","jxbdm":"1070953","kcptdm":"101230539","xmmc":"综合技能提高","kcdm":"00931","kcmc":"大学英语(4)","rwdm":"1012707","xbyqdm":"","rs1":"","rs2":"","wyfjdm":"01","kkxqdm":"1","zxs":"64","xf":"4","kcflmc":"","teaxm":"","jxbrs":"7"},{"kcrwdm":"1057955","pkrs":"1387","jxbdm":"1070954","kcptdm":"101230539","xmmc":"拓展学术英语","kcdm":"00931","kcmc":"大学英语(4)","rwdm":"1012707","xbyqdm":"","rs1":"","rs2":"","wyfjdm":"01","kkxqdm":"1","zxs":"64","xf":"4","kcflmc":"","teaxm":"","jxbrs":"5"}]
     */

    private int total;
    private List<RowsBean> rows;

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


}
