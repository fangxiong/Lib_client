package com.fx.manage.ui.adminstrator.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.fx.manage.R;
import com.fx.manage.adapter.BaseRecyclerAdapter;
import com.fx.manage.adapter.SmartViewHolder;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.Book;
import com.fx.manage.ui.adminstrator.contract.QueryBookContract;
import com.fx.manage.ui.adminstrator.model.QueryBookModel;
import com.fx.manage.ui.adminstrator.presenter.QueryBookPresenter;
import com.fx.manage.ui.student.view.activity.Stu_BookDetailActivity;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryBookActivity extends BaseActivity<QueryBookPresenter, QueryBookModel> implements QueryBookContract.View {


    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    private String searchTypeArray[] ={AppConstant.SEND_BOOKNAME,AppConstant.SEND_BOOKAUTHOR,AppConstant.SEND_BOOKPUBLISHHOUSE};
    private BaseRecyclerAdapter<Book> mAdapter;
    private List<Book> books;
    private String searchType;
    @Override
    public int getLayoutId() {
        return R.layout.activity_query_book;
    }

    @Override
    public void initPresenter() {
      mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        //建立数据源
        String[] mItems = getResources().getStringArray(R.array.searchtype);
        //建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(QueryBookActivity.this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定Adapter到控件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  searchType = searchTypeArray[position];//将选择类型复制给searchType
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          }
        );

        recyclerview.setLayoutManager(new LinearLayoutManager(QueryBookActivity.this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter = new BaseRecyclerAdapter<Book>(R.layout.layout_search_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Book model, int position) {
                holder.text(R.id.bookname, model.getBookname());
                holder.image(R.id.image, PhotoUtils.getBitmap(model.getImage()));
                holder.text(R.id.author, model.getAuthor());
                holder.text(R.id.publishhouse, model.getPublishhouse());
                holder.text(R.id.publishtime, model.getPublishtime());
                holder.text(R.id.isloan, model.getIsloan() == 1 ? "已借出" : "可借");
                holder.text(R.id.loancount, "借出" + model.getLoancount() + "次");
            }

        });

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        search();//查询
                        refreshlayout.finishRefresh(true);

                        // refreshlayout.setLoadmoreFinished(false);//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 1000);
            }
        });
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",books.get(position));
                Intent intent = new Intent(QueryBookActivity.this, Admin_BookDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
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

    @Override
    public void returnBookList(final List<Book> books) {

        this.books = books;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (books.size() == 0) {
                    showShortToast("抱歉，没有你查询的图书");
                    mAdapter.refresh(books);
                } else {
                    mAdapter.refresh(books);
                }
            }
        });
    }



    @OnClick(R.id.search)
    public void onViewClicked() {
        search();
    }

    /**
     * 查询图书
     */
    private void search() {
        String text= "";
        text = edittext.getText().toString().trim();
        //获取搜索类型
        if (text == "") {
            showShortToast("请输入搜索词");
        } else {
            mPresenter.getBookListRequest(text, searchType);

        }
    }
}
