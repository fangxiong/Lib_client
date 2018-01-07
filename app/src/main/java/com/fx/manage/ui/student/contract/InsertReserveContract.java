package com.fx.manage.ui.student.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;


/**
 * Created by fangxiong on 2017/12/18.
 */
public interface InsertReserveContract {
    interface Model extends BaseModel {
        //请求预订后返回预订订单
        Reserve getReserveInfo(int userid,int bookid, String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示预订情况
        void returnReserveInfo(Reserve reserve);
    }
    abstract static class Presenter extends BasePresenter<View,Model> {
        //发起预订请求
        public abstract void getReserveInfoRequest(int userid,int bookid, String type);

    }
}
