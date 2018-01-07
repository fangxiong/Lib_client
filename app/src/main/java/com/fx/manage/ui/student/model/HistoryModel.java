package com.fx.manage.ui.student.model;

import com.fx.manage.bean.Book;
import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.student.contract.HistoryContract;
import com.fx.manage.useraction.QueryBorrowMessageAction;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class HistoryModel implements HistoryContract.Model {

    @Override
    public List<BorrowMessage> getBorrowMessagesList(int id,String type, ICallBack callBack) {
        return QueryBorrowMessageAction.query(String.valueOf(id),type,callBack);
    }
}
