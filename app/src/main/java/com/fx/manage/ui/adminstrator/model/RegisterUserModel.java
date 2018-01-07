package com.fx.manage.ui.adminstrator.model;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Student;
import com.fx.manage.ui.adminstrator.contract.RegisterUserContract;
import com.fx.manage.ui.student.contract.HistoryContract;
import com.fx.manage.useraction.InsertUserAction;
/**
 * Created by fax on 2017/12/22 0022.
 */

public class RegisterUserModel implements RegisterUserContract.Model {
    @Override
    public Student getStudentInfo(Student student, String type,ICallBack callBack) {
        return InsertUserAction.insert(student,type,callBack);
    }
}
