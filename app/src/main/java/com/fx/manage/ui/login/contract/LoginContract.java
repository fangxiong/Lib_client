package com.fx.manage.ui.login.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;


/**
 * Created by fangxiong on 2017/12/10.
 */
public interface LoginContract {
    interface Model extends BaseModel{
        //请求登录的用户信息
         Object getUserInfo(String account,String password,String type,ICallBack callBack);
    }
    interface View extends BaseView {
        //显示登录状态
        void returnUserInfo(Object user);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起登录请求
        public abstract void getUserInfoRequest(String accout, String password, String type);

    }


}
