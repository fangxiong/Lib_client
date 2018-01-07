package com.fx.manage.ui.adminstrator.model;

import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.adminstrator.contract.QueryBookContract;
import com.fx.manage.useraction.QueryBooksAction;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class QueryBookModel implements QueryBookContract.Model {


    @Override
    public List<Book> getBookList(String message, String type, ICallBack callBack) {

       return QueryBooksAction.query(message,type,callBack);
    }
}
