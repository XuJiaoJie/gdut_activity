package com.rdc.gdut_activity.contract;

/**
 * Created by PC on 2017/5/17.
 */

public interface ToolContract {

    interface View{

        void loginSystemSuccess();

        void loginSystemFailure(String msg);

        void getError(String s);
    }

    interface Presenter{
        //view

        void loginSystem(String studentId,String pwd);

        //model

        void loginSystemSuccess();

        void loginSystemFailure(String msg);

        void getError(String s);
    }

}
