package com.fx.manage.ui.adminstrator.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Publish;

import java.util.List;

/**
 * Created by 15934 on 2017/12/25.
 */
public interface QueryPublishContract {
    interface Model extends BaseModel {
        //请求查询的出版社信息
        Publish getPublishList(String message, String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示信息
        void returnPublishList(Publish publish);
    }
    abstract static class Presenter extends BasePresenter<View,Model> {
        //发起查询请求
        public abstract void getBookListRequest(String message,String type);

    }
}
