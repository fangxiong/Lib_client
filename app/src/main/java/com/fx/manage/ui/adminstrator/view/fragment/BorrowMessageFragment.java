package com.fx.manage.ui.adminstrator.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fx.manage.R;
import com.fx.manage.adapter.BaseRecyclerAdapter;
import com.fx.manage.adapter.SmartViewHolder;
import com.fx.manage.base.AppApplication;
import com.fx.manage.base.AppConstant;
import com.fx.manage.base.BaseFragment;
import com.fx.manage.bean.BorrowMessage;
import com.fx.manage.ui.student.contract.HistoryContract;
import com.fx.manage.ui.student.model.HistoryModel;
import com.fx.manage.ui.student.presenter.HistoryPresenter;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class BorrowMessageFragment extends BaseFragment<HistoryPresenter, HistoryModel> implements HistoryContract.View {

    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<BorrowMessage> mAdapter;
    private List<BorrowMessage> borrowMessages;
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    private  int searchAccount;
    public  Handler bHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            searchAccount = msg.what;
            if(isVisible == true){
                mPresenter.getBorrowMessagesListRequest(searchAccount, AppConstant.SEND_BORROW);//查询
            }
        }
    };
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidd) {
        if (hidd) {
            //隐藏时所作的事情

        } else {
            //显示时所作的事情
            //数据为空才重新发起请求
            if (mAdapter.getCount() <= 0) {
                mPresenter.getBorrowMessagesListRequest(1, AppConstant.SEND_BORROW);//查询
            }
        }
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_historymessage;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter = new BaseRecyclerAdapter<BorrowMessage>(R.layout.layout_history_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, BorrowMessage model, int position) {
                holder.text(R.id.bookname, model.getBookname());
                holder.image(R.id.image, PhotoUtils.getBitmap(model.getImage()));
                holder.text(R.id.loantime, "借书:"+model.getLoantime());
                holder.text(R.id.returntime,"还书:"+ model.getReturntime());
                holder.text(R.id.expiretime, "到期:"+model.getExpiretime());
                holder.text(R.id.isoverdue, model.getIsoverdue() == 1 ? "已超期" : "未超期");
                holder.text(R.id.fine, "罚金" + model.getFine() + "元");
                holder.textColorId(R.id.isoverdue,R.color.red);
                holder.textColorId(R.id.fine,R.color.red);
            }

        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().post(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getBorrowMessagesListRequest(1, AppConstant.SEND_BORROW);//查询
                        refreshlayout.finishRefresh(true);

                    }
                });
            }
        });
    }


    @Override
    public void returnBorrowMessagesList(List<BorrowMessage> borrowmessages) {
        if (borrowmessages.size() == 0) {
            showShortToast("抱歉，没有你查询的图书");
        } else {
            this.borrowMessages = borrowmessages;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.refresh(borrowMessages);
                }
            });
        }
    }

    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(getActivity(), title, true);
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
