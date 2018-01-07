package com.fx.manage.ui.login.presenter;



import com.fx.manage.bean.ICallBack;
import com.fx.manage.ui.login.contract.LoginContract;
import com.fx.manage.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by fangxiong on 2017/12/10.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void getUserInfoRequest(final String account, final String password,final String type) {
        mView.showLoading("加载中");
        final ICallBack<Object> callBack = new ICallBack<Object>() {
            @Override
            public void resultForObject(Object object) {
                mView.stopLoading();
                mView.returnUserInfo(object);
            }

            @Override
            public void resultForList(List<Object> list) {

            }
        };
        ThreadPoolManager.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                mModel.getUserInfo(account, password, type, callBack);
            }
        });



        /*new AsyncTask<Void,Void,Object>(){
            @Override
            protected Object doInBackground(Void... params) {
                System.out.println("doInBackground方法："+"打开进度加载框");
               try {
                   Thread.sleep(2000);
               }catch(Exception e){
                   e.printStackTrace();
               }
                Object user = mModel.getUserInfo(account,password,type);
                return  user;

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mView.showLoading("正在登录...");
                System.out.println("onPreExecute方法："+"打开进度加载框");
            }

            @Override
            protected void onPostExecute(Object object) {
                super.onPostExecute(object);
                System.out.println("onPostExecute方法：关闭进度加载框");
                mView.stopLoading();
                mView.returnUserInfo(object);//根据用户类型跳转到对应的Activity
            }
        }.execute();*/
    }
}
