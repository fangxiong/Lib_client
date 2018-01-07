package com.fx.manage.ui.adminstrator.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;
import com.fx.manage.ui.adminstrator.contract.QueryUserContract;
import com.fx.manage.useraction.QueryUserAction;

/**
 * Created by 15934 on 2017/12/26.
 */
public class QueryUserModel implements QueryUserContract.Model {
    @Override
    public Student getUserInfo(String message, String type, ICallBack callBack) {
        return QueryUserAction.query(message,type,callBack);
    }
}
