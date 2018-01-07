package com.fx.manage.ui.adminstrator.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.fx.manage.R;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseActivity;
import com.fx.manage.bean.Publish;
import com.fx.manage.ui.adminstrator.contract.QueryPublishContract;
import com.fx.manage.ui.adminstrator.model.QueryPublishModel;
import com.fx.manage.ui.adminstrator.presenter.QueryPublishPresenter;
import com.fx.manage.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryPublishHouseActivity extends BaseActivity<QueryPublishPresenter, QueryPublishModel> implements QueryPublishContract.View {

    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.search)
    Button search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_query_publish_house;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {

    }

    @Override
    public void returnPublishList(final Publish publish) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publish != null) {
                    LoadingDialog.showDialogForLoadingToText(QueryPublishHouseActivity.this, "联系方式:" + publish.getContact(), true);
                } else {
                    showLongToast("抱歉，未找到该书！");
                }

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

    @OnClick(R.id.search)
    public void onViewClicked() {
        String content = edittext.getText().toString().trim();
        if(content != null){
           mPresenter.getBookListRequest(content, AppConstant.SEND_QUERY_PUBLISH);
        }
        else{
            showShortToast("请输入搜索关键词");
        }
    }
}
