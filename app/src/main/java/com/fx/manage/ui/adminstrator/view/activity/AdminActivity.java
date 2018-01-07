package com.fx.manage.ui.adminstrator.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;

import com.fx.manage.R;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.widget.CircleImageView;
import com.fx.manage.widget.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminActivity extends BaseActivity {


    private long mExitTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_admin;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();//沉浸式状态栏
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_register, R.id.book_search, R.id.user_search, R.id.publishhouse_searh, R.id.book_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_register:
                Intent intent1 = new Intent(AdminActivity.this,ShouyeActivity.class);
                startActivity(intent1);
                break;
            case R.id.book_search:
                Intent intent2 = new Intent(AdminActivity.this,QueryBookActivity.class);
                startActivity(intent2);
                break;
            case R.id.user_search:
                Intent intent3 = new Intent(AdminActivity.this,QueryUserActivity.class);
                startActivity(intent3);
                break;
            case R.id.publishhouse_searh:
                Intent intent4 = new Intent(AdminActivity.this,QueryPublishHouseActivity.class);
                startActivity(intent4);
                break;
            case R.id.book_delete:
                Intent intent5 = new Intent(AdminActivity.this,QueryBookActivity.class);
                startActivity(intent5);
                break;
        }
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // moveTaskToBack(false);
            if(event.getRepeatCount() == 0){
                exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            showShortToast("再按一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
