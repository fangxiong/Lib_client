package com.fx.manage.ui.adminstrator.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Publish;
import com.fx.manage.ui.adminstrator.contract.QueryPublishContract;
import com.fx.manage.useraction.QueryPublishAction;

/**
 * Created by 15934 on 2017/12/25.
 */
public class QueryPublishModel implements QueryPublishContract.Model {
    @Override
    public Publish getPublishList(String message, String type, ICallBack callBack) {
        return QueryPublishAction.query(message,type,callBack);
    }
}
