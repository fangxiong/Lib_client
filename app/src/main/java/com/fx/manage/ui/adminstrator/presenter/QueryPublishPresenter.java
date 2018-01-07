package com.fx.manage.ui.adminstrator.presenter;

import com.fx.manage.bean.Book;
import com.fx.manage.bean.ICallBack;
import com.fx.manage.bean.Publish;
import com.fx.manage.ui.adminstrator.contract.QueryPublishContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by 15934 on 2017/12/25.
 */
public class QueryPublishPresenter extends QueryPublishContract.Presenter{

    @Override
    public void getBookListRequest(final String message, final String type) {
        mView.showLoading("正在查询中...");
        final ICallBack<Publish> callBack = new ICallBack<Publish>() {
            @Override
            public void resultForObject(Publish publish) {
                mView.stopLoading();
                mView.returnPublishList(publish);
            }

            @Override
            public void resultForList(List<Publish> list) {
            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getPublishList(message, type, callBack);
            }
        });

    }
}
