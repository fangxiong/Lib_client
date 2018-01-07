package com.fx.manage.ui.adminstrator.presenter;

import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.adminstrator.contract.DeleteContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by 15934 on 2017/12/25.
 */
public class DeletePresenter extends DeleteContract.Presenter {
    @Override
    public void deleteInfoRequest(final int id, final String type) {
        mView.showLoading("数据删除中...");
        final ICallBack<Object> callBack = new ICallBack<Object>() {
            @Override
            public void resultForObject(Object object) {
                mView.stopLoading();
                mView.returnInfo(object);
            }

            @Override
            public void resultForList(List<Object> object) {

            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getInfo(id, type, callBack);
            }
        });

    }
}
