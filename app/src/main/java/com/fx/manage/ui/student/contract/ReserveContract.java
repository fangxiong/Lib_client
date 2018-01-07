package com.fx.manage.ui.student.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.ReserveMessage;

import java.util.List;


/**
 * Created by fangxiong on 2017/12/10.
 */
public interface ReserveContract {
    interface Model extends BaseModel{
        //请求预订后返回预订订单
         List<ReserveMessage> getReserveMessagesInfo(int userid, String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示预订情况
        void returnReserveMessagesInfo(List<ReserveMessage> reserve);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起预订请求
        public abstract void getReserveMessagesInfoRequest(int userid, String type);

    }


}
