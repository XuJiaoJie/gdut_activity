package com.rdc.gdut_activity.contract;

import com.rdc.gdut_activity.bean.ActivityInfoBean;
import com.rdc.gdut_activity.listener.IHttpCallBack;

import java.util.Map;

/**
 * Created by ThatNight on 2017.5.16.
 */

public interface SignUpContract {
    public interface View {
        void signUpSuccess();

        void signUpFailed(String error);

        void showProgress(boolean isShow);

        ActivityInfoBean getActivityBean();
    }

    public interface Presenter {
        void signUp( Map<String, String> map);
    }

    public interface Model {
        void signUp(ActivityInfoBean activityInfoBean, Map<String, String> map, IHttpCallBack httpCallBack);
    }

}
