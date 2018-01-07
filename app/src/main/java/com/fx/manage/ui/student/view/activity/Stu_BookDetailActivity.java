package com.fx.manage.ui.student.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fx.manage.R;
import com.fx.manage.base.AppApplication;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.Collection;
import com.fx.manage.bean.Reserve;
import com.fx.manage.db.DBManager;
import com.fx.manage.ui.student.contract.InsertReserveContract;
import com.fx.manage.ui.student.model.InsertReserveModel;
import com.fx.manage.ui.student.presenter.InsertReservePresenter;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.LoadingDialog;

import java.util.List;

import butterknife.BindView;

public class Stu_BookDetailActivity extends BaseActivity<InsertReservePresenter, InsertReserveModel> implements InsertReserveContract.View {

    @BindView(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @BindView(R.id.mask_view)
    View maskView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.news_detail_from_tv)
    TextView newsDetailFromTv;
    @BindView(R.id.news_detail_body_tv)
    TextView newsDetailBodyTv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Book mBook = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mBook = (Book)bundle.getSerializable("item");
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    public void initView() {
        SetStatusBarColor(R.color.colorPrimaryDark);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        toolbar.inflateMenu(R.menu.stu_news_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.collection:
                        DBManager.getInstance(Stu_BookDetailActivity.this).insert(new Collection(mBook.getBookname(), mBook.getAuthor(), mBook.getImage(),mBook.getDescription()));
                       showShortToast("收藏成功");
                        break;
                    case R.id.reserve:
                        if (mBook.getIsloan() == 1) {
                            //插入表单
                            mPresenter.getReserveInfoRequest(AppApplication.student_user.getUserid(),mBook.getBookid(), AppConstant.SEND_INSERT_RESERVE);
                        } else {
                            Toast.makeText(Stu_BookDetailActivity.this, "无需预定，请直接借阅！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });
        newsDetailPhotoIv.setImageBitmap(PhotoUtils.getBitmap(mBook.getImage()));//设置图片
        newsDetailFromTv.setText("江苏大学图书馆");
        newsDetailBodyTv.setText(mBook.getDescription());
        //分享
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_contents, mBook.getBookname(),mBook.getAuthor(), mBook.getDescription()));
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
        toolbarLayout.setTitle(mBook.getBookname());
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }

    @Override
    public void returnReserveInfo(final Reserve reserve) {

            runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (reserve != null) {
                    showLongToast("预订成功");
                } else {
                    showShortToast("预订失败");
                } }
        });

        }


    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, title, true);
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }
}
