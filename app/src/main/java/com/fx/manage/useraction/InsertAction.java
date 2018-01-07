package com.fx.manage.useraction;

import android.widget.Toast;

import com.fx.manage.base.AppConstant;
import com.fx.manage.bean.Administrator;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.Student;
import com.fx.manage.utils.GsonUtil;
import com.fx.manage.utils.ToastUitl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

/**
 * Created by fangxiong on 2017/12/18.
 */
public class InsertAction {

    private static String url = "http://192.168.43.251:8080/lib_server/insert.action";
    public static Reserve reserve = null;
    /**
     * 获取Struts2 Http 预约的请求信息
     *
     * @param userid
     * @param bookid
     */
    public static Reserve insert(final String userid,final String bookid,final String type ,final ICallBack callBack) {

        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid", userid)
                .addParams("bookid", bookid)
                .addParams("type", type)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        String res = response.body().string();
                        reserve = GsonUtil.GsonToBean(res, Reserve.class);
                        callBack.resultForObject(reserve);
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
        return reserve;

    }
}
