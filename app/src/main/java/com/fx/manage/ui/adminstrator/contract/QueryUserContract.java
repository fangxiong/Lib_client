package com.fx.manage.ui.adminstrator.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;



/**
 * Created by 15934 on 2017/12/26.
 */
public interface QueryUserContract {
    interface Model extends BaseModel {
        //请求查询的用户信息
        Student getUserInfo(String message, String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示用户信息
        void returnUserInfo(Student student);
    }
    abstract static class Presenter extends BasePresenter<View,Model> {
        //发起请求
        public abstract void getUserInfoRequest(String message,String type);

    }
}
