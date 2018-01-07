package com.fx.manage.useraction;

import android.widget.Toast;

import com.fx.manage.bean.Administrator;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;
import com.fx.manage.utils.GsonUtil;
import com.fx.manage.utils.ToastUitl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

/**
 * 用户的联网查询操作
 * Created by fangxiong on 2017/12/12.
 */
public class QueryUserAction {
    private static String url = "http://192.168.43.251:8080/lib_server/query.action";
    public static Student student= null;
    /**
     * 获取Struts2 Http 查询的请求信息
     *
     */
    public static Student query(final String content,final String type ,final ICallBack callBack) {

        OkHttpUtils
                .get()
                .url(url)
                .addParams("searchCharacter",content)
                .addParams("type",type)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {

                        String res = response.body().string();
                        student = GsonUtil.GsonToBean(res, Student.class);
                        callBack.resultForObject(student);
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

        return student;

    }
}
