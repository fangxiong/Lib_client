package com.fx.manage.ui.student.presenter;


import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.student.contract.HistoryContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class HistoryPresenter extends HistoryContract.Presenter {
    @Override
    public void getBorrowMessagesListRequest(final int id,final String type) {
        mView.showLoading("正在查询中...");
        final ICallBack<BorrowMessage> callBack = new ICallBack<BorrowMessage>() {
            @Override
            public void resultForObject(BorrowMessage object) {

            }

            @Override
            public void resultForList(List<BorrowMessage> list) {
                mView.stopLoading();
                mView.returnBorrowMessagesList(list);
            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getBorrowMessagesList(id, type, callBack);
            }
        });

    }
    }


