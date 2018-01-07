package com.fx.manage.ui.student.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.bean.ICallBack;

import java.util.List;


/**
 * Created by fangxiong on 2017/12/10.
 */
public interface HistoryContract {
    interface Model extends BaseModel{
        //请求已借图书的信息
         List<BorrowMessage> getBorrowMessagesList(int id, String type,ICallBack callBack);
    }
    interface View extends BaseView {
        //显示已借图书
        void returnBorrowMessagesList(List<BorrowMessage> borrowMessages);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起查询借阅历史请求
        public abstract void getBorrowMessagesListRequest(int id,String type);

    }


}
