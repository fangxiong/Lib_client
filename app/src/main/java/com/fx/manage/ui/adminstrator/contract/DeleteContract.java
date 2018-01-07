package com.fx.manage.ui.adminstrator.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;


/**
 * Created by 15934 on 2017/12/25.
 */
public interface DeleteContract {
    interface Model extends BaseModel {
        //请求删除后查询的信息(为空则为删除成功)
        Object getInfo(int id, String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示信息
        void returnInfo(Object result);
    }
    abstract static class Presenter extends BasePresenter<View,Model> {
        //发起登录请求
        public abstract void deleteInfoRequest(int id,String type);

    }
}
