package com.fx.manage.ui.student.presenter;


import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.student.contract.QueryContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class QueryPresenter extends QueryContract.Presenter{


    @Override
    public void getBookListRequest(final String message,final String type) {
        mView.showLoading("正在查询中...");
        final ICallBack<Book> callBack = new ICallBack<Book>() {
            @Override
            public void resultForObject(Book object) {

            }

            @Override
            public void resultForList(List<Book> list) {
                mView.stopLoading();
                mView.returnBookList(list);
            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getBookList(message, type, callBack);
            }
        });

    }
}

