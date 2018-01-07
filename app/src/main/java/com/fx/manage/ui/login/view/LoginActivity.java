package com.fx.manage.ui.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fx.manage.R;
import com.fx.manage.base.AppApplication;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.Administrator;
import com.fx.manage.bean.Student;
import com.fx.manage.ui.adminstrator.view.activity.AdminActivity;
import com.fx.manage.ui.login.contract.LoginContract;
import com.fx.manage.ui.login.model.LoginModel;
import com.fx.manage.ui.login.presenter.LoginPresenter;
import com.fx.manage.ui.student.view.activity.StudentActivity;
import com.fx.manage.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContract.View {


    @BindView(R.id.login_picture)
    ImageView loginPicture;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.login_admin)
    Button loginAdmin;
    @BindView(R.id.login_stu)
    Button loginStu;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.password)
    EditText password;

    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, title, true);
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.login_admin, R.id.login_stu})
    public void onViewClicked(View view) {
        String acc = account.getText().toString();
        String pd = password.getText().toString();
        switch (view.getId()) {
            case R.id.login_admin:
              mPresenter.getUserInfoRequest(acc, pd, AppConstant.SEND_ADMIN);
                break;
            case R.id.login_stu:
                mPresenter.getUserInfoRequest(acc, pd, AppConstant.SEND_STU);
                break;
        }
    }

    @Override
    public void returnUserInfo(final Object user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(user != null) {
                    showShortToast("登录成功！");
                    if (user instanceof Student) {
                        AppApplication.student_user = (Student)user;
                        Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        AppApplication.admin_user = (Administrator) user;
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    showShortToast("账号密码错误！");
                }
            }
        });

    }
}
