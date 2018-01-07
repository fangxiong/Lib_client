package com.fx.manage.ui.adminstrator.presenter;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.Student;
import com.fx.manage.ui.adminstrator.contract.RegisterUserContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fax on 2017/12/22 0022.
 */

public class RegisterUserPresenter extends RegisterUserContract.Presenter {
    @Override
    public void getStudentInfoRequest(final Student student,final String type) {
        mView.showLoading("正在提交中...");
        final ICallBack<Student> callBack = new ICallBack<Student>() {
            @Override
            public void resultForObject(Student student) {
                mView.stopLoading();
                mView.returnStudentInfo(student);
            }

            @Override
            public void resultForList(List<Student> list) {

            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getStudentInfo(student,type, callBack);
            }
        });
    }
}
