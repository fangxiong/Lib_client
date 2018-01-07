package com.fx.manage.ui.student.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;

import java.util.List;


/**
 * Created by fangxiong on 2017/12/10.
 */
public interface QueryContract {
    interface Model extends BaseModel{
        //请求查询的图书信息
         List<Book> getBookList(String message,String type, ICallBack callBack);
    }
    interface View extends BaseView {
        //显示图书信息
        void returnBookList( List<Book> books);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起登录请求
        public abstract void getBookListRequest(String message,String type);

    }


}
