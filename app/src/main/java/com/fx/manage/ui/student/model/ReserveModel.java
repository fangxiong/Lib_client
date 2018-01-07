package com.fx.manage.ui.student.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.ReserveMessage;
import com.fx.manage.ui.student.contract.ReserveContract;
import com.fx.manage.useraction.QueryReserveMessageAction;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class ReserveModel implements ReserveContract.Model {

    @Override
    public List<ReserveMessage> getReserveMessagesInfo(int userid, String type, ICallBack callBack) {
        return QueryReserveMessageAction.query(String.valueOf(userid),type,callBack);
    }
}
