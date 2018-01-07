package com.fx.manage.base;



import com.fx.manage.bean.Administrator;
import com.fx.manage.bean.Student;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.http.params.HttpParams;

/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {
    public static Student student_user =  null;//学生用户
    public static Administrator admin_user =  null;//管理员用户
    @Override
    public void onCreate() {
        super.onCreate();


        //初始化logger
       // LogUtils.logInit(BuildConfig.LOG_DEBUG);
    }
}
