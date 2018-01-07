package com.fx.manage.ui.student.model;

import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.student.contract.QueryContract;
import com.fx.manage.useraction.QueryBooksAction;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class QueryModel implements QueryContract.Model {


    @Override
    public List<Book> getBookList(String message, String type, ICallBack callBack) {

       return QueryBooksAction.query(message,type,callBack);
    }
}
