package com.fx.manage.ui.student.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.ui.student.contract.InsertReserveContract;
import com.fx.manage.useraction.InsertAction;

/**
 * Created by fangxiong on 2017/12/18.
 */
public class InsertReserveModel implements InsertReserveContract.Model{


    @Override
    public Reserve getReserveInfo(int userid, int bookid, String type, ICallBack callBack) {
        return InsertAction.insert(String.valueOf(userid),String.valueOf(bookid),type,callBack);
    }
}
