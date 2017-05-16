package com.rdc.gdut_activity.constant;

import com.rdc.gdut_activity.R;

/**
 * Created by ThatNight on 2017.4.26.
 */

public class Constant {
    public static final String APPLICATION_ID = "a16f9e1ce59b535cdb4ab68044234871";    //bmob_id
    public static final String photo = "http://pic38.nipic.com/20140228/8821914_204428973000_2.jpg";

    /*User*/
    public static final int USER_STUDENT = 311500;    //学生
    public static final int USER_PUBLISHER = 322222;  //发布者
    public static final int USER_ADMIN = 399999;      //审核

    /*Details*/
    public static final int RETURN_INDEX_CODE = 90;     //返回当前显示的页面

    /*SelectClass*/
    public static String URL_SELECTCLASS_LOGIN="http://222.200.98.147/login!doLogin.action";
    public static String URL_SELECTCLASS_GETCLASS_LIST="http://222.200.98.147/xsxklist!getDataList.action";
    public static String URL_SELECTCLASS_SELECTCLASS="http://222.200.98.147/xsxklist!getAdd.action";
    public static String URL_SELECTCLASS_GETOWN_CLASS="http://222.200.98.147/xsxklist!getXzkcList.action";
    /*教务系统参数*/
    public static final String EDUCATION_SYSTEM_LOGIN_URL ="http://222.200.98.147/login!doLogin.action";
    public static final String EDUCATION_SYSTEM_SCORE_URL = "http://222.200.98.147/xskccjxx!getDataList.action";

    //登录时请求参数
    public static final String LOGIN_BODY_NAME_ACCOUNT = "account";
    public static final String LOGIN_BODY_NAME_PWD = "pwd";
    public static final String LOGIN_BODY_NAME_VERIFYCODE = "verifycode";
    public static final String LOGIN_BODY_VALUE_VERIFYCODE = "";

    //获取成绩时请求参数
    public static final String SCORE_BODY_NAME_XNXQDM = "xnxqdm";
    public static final String SCORE_BODY_NAME_JHLXDM = "jhlxdm";
    public static final String SCORE_BODY_NAME_PAGE = "page";
    public static final String SCORE_BODY_VALUE_PAGE = "1";
    public static final String SCORE_BODY_NAME_ROWS = "rows";
    public static final String SCORE_BODY_VALUE_ROWS = "50";
    public static final String SCORE_BODY_NAME_SORT = "sort";
    public static final String SCORE_BODY_VALUE_SORT = "xnxqdm";
    public static final String SCORE_BODY_NAME_ORDER = "order";
    public static final String SCORE_BODY_VALUE_ORDER = "asc";

    public static final int MODE_NORMAL = 2017; //获取可选课程
    public static final int MODE_OWN = 1997;    //获取已选

    /*loadingDialog*/
    public static final int LOADING_STYLE = R.style.ActionProgressStyle;

}
