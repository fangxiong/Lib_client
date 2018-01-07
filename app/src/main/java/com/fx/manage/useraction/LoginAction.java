package com.fx.manage.useraction;

import android.app.Application;
import android.widget.Toast;

import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseApplication;
import com.fx.manage.bean.Administrator;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;
import com.fx.manage.utils.GsonUtil;
import com.fx.manage.utils.ToastUitl;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

/**
 * 封装用户需要访问服务器的方法
 * Created by fangxiong on 2017/12/8.
 */
public class LoginAction {

    private static String url = "http://192.168.43.251:8080/lib_server/login.action";
    public static Object user= null;
    /**
     * 获取Struts2 Http 登录的请求信息
     *
     * @param userName
     * @param password
     */
    public static Object login(final String userName,final String password,final String type ,final ICallBack callBack) {

        OkHttpUtils
                .get()
                .url(url)
                .addParams("account", userName)
                .addParams("password", password)
                .addParams("type", type)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        String res = response.body().string();
                        if(type == AppConstant.SEND_ADMIN) {
                            user = GsonUtil.GsonToBean(res, Administrator.class);
                        }
                        else if(type == AppConstant.SEND_STU){
                            user = GsonUtil.GsonToBean(res, Student.class);
                        }
                        callBack.resultForObject(user);
                        return null;
                   }

                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUitl.show("网络出错了", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onResponse(Object response) {

                    }
                });
        return user;

    }
}


