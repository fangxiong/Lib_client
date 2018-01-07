package com.fx.manage.ui.adminstrator.contract;

import com.fx.manage.base.BaseModel;
import com.fx.manage.base.BasePresenter;
import com.fx.manage.base.BaseView;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;

/**
 * Created by fax on 2017/12/22 0022.
 */

public interface RegisterUserContract {
    interface Model extends BaseModel {
        //请求注册学生的信息
        Student getStudentInfo(Student student, String type, ICallBack callBack);
    }

    interface View extends BaseView {
        //返回注册后学生信息
        void returnStudentInfo(Student student);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起查询注册学生请求
        public abstract void getStudentInfoRequest(Student student, String type);

    }
}
