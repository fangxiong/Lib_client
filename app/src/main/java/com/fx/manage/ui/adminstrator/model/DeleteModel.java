package com.fx.manage.ui.adminstrator.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.adminstrator.contract.DeleteContract;
import com.fx.manage.useraction.DeleteAction;

/**
 * Created by 15934 on 2017/12/25.
 */
public class DeleteModel implements DeleteContract.Model {
    @Override
    public Object getInfo(int id, String type, ICallBack callBack) {
        return DeleteAction.delete(String.valueOf(id),type,callBack);
    }
}
