package com.fx.manage.ui.adminstrator.view.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fx.manage.R;
import com.fx.manage.ui.adminstrator.view.fragment.BorrowMessageFragment;
import com.fx.manage.ui.adminstrator.view.fragment.ReserveMessageFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QueryUserActivity extends AppCompatActivity {

    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<String> mTitles =  new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    BorrowMessageFragment bmFragment = new BorrowMessageFragment();
    ReserveMessageFragment rmFragment = new ReserveMessageFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_user);
        ButterKnife.bind(this);
        initView();
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mTitles, mFragment);
        viewPager.setAdapter(adapter);
        //为TabLayout设置ViewPager
        tabs.setupWithViewPager(viewPager);

    }

    public  void initView(){
        toolbar.setTitle("借阅人");
        mTitles.add("借阅记录");
        mTitles.add("预约记录");
        mFragment.add(bmFragment);
        mFragment.add(rmFragment);
    }
    @OnClick(R.id.search)
    public void onViewClicked() {
        String content = edittext.getText().toString().trim();
        if(content.equals("")){
            Toast.makeText(QueryUserActivity.this,"请输入查询关键词！",Toast.LENGTH_SHORT).show();
        }
        bmFragment.bHandler.sendEmptyMessage(Integer.parseInt(content));
        rmFragment.rHandler.sendEmptyMessage(Integer.parseInt(content));

    }


    public class MyAdapter extends FragmentPagerAdapter {

        private List<String> title;
        private List<Fragment> views;

        public MyAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
            super(fm);
            this.title = title;
            this.views = views;
        }

        @Override
        public Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }


        //配置标题的方法
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
