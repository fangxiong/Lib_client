package com.fx.manage.ui.student.presenter;

import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.ReserveMessage;
import com.fx.manage.ui.student.contract.ReserveContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class ReservePresenter extends ReserveContract.Presenter {

    @Override
    public void getReserveMessagesInfoRequest(final int userid, final String type) {
        mView.showLoading("正在查询中...");
        final ICallBack<ReserveMessage> callBack = new ICallBack<ReserveMessage>() {
            @Override
            public void resultForObject(ReserveMessage object) {

            }

            @Override
            public void resultForList(List<ReserveMessage> list) {
                mView.stopLoading();
                mView.returnReserveMessagesInfo(list);
            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getReserveMessagesInfo(userid,type,callBack);
            }
        });

    }
}
