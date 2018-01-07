package com.fx.manage.ui.student.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.fx.manage.R;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.TabEntity;
import com.fx.manage.ui.student.view.fragment.HistoryFragment;
import com.fx.manage.ui.student.view.fragment.PersonFragment;
import com.fx.manage.ui.student.view.fragment.QueryFragment;
import com.fx.manage.ui.student.view.fragment.ReserveFragment;
import com.fx.manage.ui.student.view.fragment.ShouyeFragment;
import com.fx.manage.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class StudentActivity extends BaseActivity {


    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles = {"查询", "预订", "首页", "借阅历史", "个人中心"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_query_normal, R.mipmap.ic_reserve_normal, R.mipmap.ic_shouye_normal, R.mipmap.ic_history_normal, R.mipmap.ic_person_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_query_selected, R.mipmap.ic_reserve_selected, R.mipmap.ic_shouye_selected, R.mipmap.ic_history_selected, R.mipmap.ic_person_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private QueryFragment queryFragment;
    private ReserveFragment reserveFragment;
    private ShouyeFragment shouyeFragment;
    private HistoryFragment historyFragment;
    private PersonFragment personFragment;
    private long mExitTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        //tabLayoutHeight=tabLayout.getMeasuredHeight();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_student;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initTab();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //查询
            case 0:
                transaction.hide(reserveFragment);
                transaction.hide(shouyeFragment);
                transaction.hide(historyFragment);
                transaction.hide(personFragment);
                transaction.show(queryFragment);
                transaction.commitAllowingStateLoss();
                break;
            //预订
            case 1:
                transaction.hide(queryFragment);
                transaction.hide(shouyeFragment);
                transaction.hide(historyFragment);
                transaction.hide(personFragment);
                transaction.show(reserveFragment);
                transaction.commitAllowingStateLoss();
                break;
            //首页
            case 2:
                transaction.hide(queryFragment);
                transaction.hide(historyFragment);
                transaction.hide(personFragment);
                transaction.hide(reserveFragment);
                transaction.show(shouyeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //借阅历史
            case 3:
                transaction.hide(queryFragment);
                transaction.hide(personFragment);
                transaction.hide(reserveFragment);
                transaction.hide(shouyeFragment);
                transaction.show(historyFragment);
                transaction.commitAllowingStateLoss();
                break;
            //个人中心
            case 4:
                transaction.hide(queryFragment);
                transaction.hide(reserveFragment);
                transaction.hide(shouyeFragment);
                transaction.hide(historyFragment);
                transaction.show(personFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 2;
        if (savedInstanceState != null) {
            queryFragment = (QueryFragment) getSupportFragmentManager().findFragmentByTag("queryFragment");
            reserveFragment = (ReserveFragment) getSupportFragmentManager().findFragmentByTag("reserveFragment");
            shouyeFragment = (ShouyeFragment) getSupportFragmentManager().findFragmentByTag("shouyeFragment");
            historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag("historyFragment");
            personFragment = (PersonFragment) getSupportFragmentManager().findFragmentByTag("personFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            queryFragment = new QueryFragment();
            reserveFragment = new ReserveFragment();
            shouyeFragment = new ShouyeFragment();
            historyFragment = new HistoryFragment();
            personFragment = new PersonFragment();
            transaction.add(R.id.fl_body, queryFragment, "queryFragment");
            transaction.add(R.id.fl_body, reserveFragment, "reserveFragment");
            transaction.add(R.id.fl_body, shouyeFragment, "shouyeFragment");
            transaction.add(R.id.fl_body, historyFragment, "historyFragment");
            transaction.add(R.id.fl_body, personFragment, "personFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        LogUtils.loge("onSaveInstanceState进来了1");
        if (tabLayout != null) {
            LogUtils.loge("onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }
}

