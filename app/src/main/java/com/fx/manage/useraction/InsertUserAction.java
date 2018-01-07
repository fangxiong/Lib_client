package com.fx.manage.useraction;



import android.widget.Toast;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.Student;
import com.fx.manage.utils.GsonUtil;
import com.fx.manage.utils.ToastUitl;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

/**
 * Created by fax on 2017/12/22 0022.
 */

public class InsertUserAction {
    private static String url = "http://192.168.43.251:8080/lib_server/insert.action";
    public static Student mStudent = null;

    public InsertUserAction() {
        super();
    }

    /**
     * 获取Struts2 Http 预约的请求信息
     *
     * @param student
     * @param type
     */
    public static Student insert(final Student student, final String type, final ICallBack callBack) {
        OkHttpUtils.postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(student))
                .build()
                .execute(new com.zhy.http.okhttp.callback.Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        String res = response.body().string();
                        mStudent = GsonUtil.GsonToBean(res, Student.class);
                        callBack.resultForObject(mStudent);
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
        return mStudent;
    }
}

