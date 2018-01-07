package com.fx.manage.ui.login.model;

import com.fx.manage.base.BaseApplication;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.login.contract.LoginContract;
import com.fx.manage.useraction.LoginAction;

/**
 * Created by fangxiong on 2017/12/10.
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public Object getUserInfo(String account, String password, String type, ICallBack callBack) {
        return LoginAction.login(account, password, type,callBack);
    }
}
