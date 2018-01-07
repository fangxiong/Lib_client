package com.fx.manage.ui.student.presenter;

import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Reserve;
import com.fx.manage.ui.student.contract.InsertReserveContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/18.
 */
public class InsertReservePresenter extends InsertReserveContract.Presenter{


    @Override
    public void getReserveInfoRequest(final int userid,final int bookid,final String type) {
        mView.showLoading("正在预约中...");
        final ICallBack<Reserve> callBack = new ICallBack<Reserve>() {
            @Override
            public void resultForObject(Reserve reserve) {
                mView.stopLoading();
                mView.returnReserveInfo(reserve);
            }

            @Override
            public void resultForList(List<Reserve> list) {

            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getReserveInfo(userid,bookid, type, callBack);
            }
        });
    }
}
