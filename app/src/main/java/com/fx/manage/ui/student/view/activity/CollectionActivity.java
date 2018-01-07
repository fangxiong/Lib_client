package com.fx.manage.ui.student.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.fx.manage.R;
import com.fx.manage.adapter.BaseRecyclerAdapter;
import com.fx.manage.adapter.SmartViewHolder;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.Book;
import com.fx.manage.bean.Collection;
import com.fx.manage.db.DBManager;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.NormalTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CollectionActivity extends BaseActivity {

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private BaseRecyclerAdapter<Collection> mAdapter;
    private List<Collection> collectionList;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                mAdapter.refresh(collectionList);
            }
            stopProgressDialog();
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public void initView(){
        ntb.setBackVisibility(true);
        ntb.setTitleText("我的收藏");
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        startProgressDialog("加载中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                collectionList =  DBManager.getInstance(CollectionActivity.this).query();
                mHandler.sendEmptyMessage(0);
            }
        }).start();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter = new BaseRecyclerAdapter<Collection>(R.layout.layout_collection_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Collection model, int position) {
                holder.text(R.id.bookname, model.getBookname());
                holder.image(R.id.image, PhotoUtils.getBitmap(model.getImage()));
                holder.text(R.id.author,model.getAuthor());
                holder.text(R.id.description,model.getDescription());

            }

        });
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CollectionActivity.this).setTitle("系统提示")
                        .setMessage("确认删除"+collectionList.get(position).getBookname()+"?")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBManager.getInstance(CollectionActivity.this).delete(collectionList.get(position).getBookname());
                                collectionList.remove(position);
                                mAdapter.refresh(collectionList);

                            }//添加确定按钮  


                        })
                        .setNegativeButton("返回",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }//添加返回按钮

                        }).show();
            }
        });

    }
}