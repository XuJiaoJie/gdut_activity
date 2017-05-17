package com.rdc.gdut_activity.ui;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseActivity;
import com.rdc.gdut_activity.bean.ScoreBean;
import com.rdc.gdut_activity.constant.Constant;
import com.rdc.gdut_activity.utils.OkHttpResultCallback;
import com.rdc.gdut_activity.utils.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import okhttp3.Call;


public class LoginTeachSystemActivity extends BaseActivity {
    private static final String TAG = "LoginTeachSystemActivit";
    @InjectView(R.id.et_login_username)
    EditText mEtLoginUsername;
    @InjectView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @InjectView(R.id.btn_login_login)
    Button mBtnLoginLogin;

    private Map<String,String> mHeadersMap;
    private Map<String,String> mBodyMap;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_login_teach_system;
    }

    @Override
    protected void initData() {
        mHeadersMap = new HashMap<>();
        mBodyMap = new HashMap<>();
//        mHeadersMap.put(Constant.HEADER_NAME_HOST, Constant.HEADER_VALUE_HOST);
//        mHeadersMap.put(Constant.HEADER_NAME_REFERER, Constant.HEADER_VALUE_REFERER);
//        mHeadersMap.put(Constant.HEADER_NAME_AGENT, Constant.HEADER_VALUE_AGENT);

        mBodyMap.put(Constant.LOGIN_BODY_NAME_ACCOUNT,"3115005289");
        mBodyMap.put(Constant.LOGIN_BODY_NAME_PWD,"15626227808aa");
        mBodyMap.put(Constant.LOGIN_BODY_NAME_VERIFYCODE,Constant.LOGIN_BODY_VALUE_VERIFYCODE);

        OkHttpUtil.getInstance().postAsync(Constant.EDUCATION_SYSTEM_LOGIN_URL, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onResponse(byte[] bytes) {
                String s = new String(bytes);
                Log.e(TAG, "onResponse: " + s);

                mBodyMap.put("xnxqdm","201601");
                mBodyMap.put("jhlxdm","");
                mBodyMap.put("page","1");
                mBodyMap.put("rows","50");
                mBodyMap.put("order","asc");


                OkHttpUtil.getInstance().postAsync("http://222.200.98.147/xskccjxx!getDataList.action", new OkHttpResultCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(byte[] bytes) {
                        String s = new String(bytes);
                        Log.e(TAG, "onResponse: " + s);
                        ScoreBean scoreBean = ScoreBean.objectFromData(s);
                        Log.e(TAG, "onResponse: scoreBean"+ scoreBean.getRows().get(1).getKcmc());
                    }
                },mBodyMap,mHeadersMap);


            }
        },mBodyMap,mHeadersMap);



    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }


}
