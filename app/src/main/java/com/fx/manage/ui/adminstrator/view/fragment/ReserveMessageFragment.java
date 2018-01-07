package com.fx.manage.ui.adminstrator.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.fx.manage.bean.Reserve;
import com.fx.manage.bean.ReserveMessage;
import com.fx.manage.ui.adminstrator.view.activity.QueryUserActivity;
import com.fx.manage.ui.student.contract.ReserveContract;
import com.fx.manage.ui.student.model.ReserveModel;
import com.fx.manage.ui.student.presenter.ReservePresenter;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.LoadingDialog;
import com.fx.manage.widget.NormalTitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class ReserveMessageFragment extends BaseFragment<ReservePresenter, ReserveModel> implements ReserveContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private BaseRecyclerAdapter<ReserveMessage> mAdapter;
    private List<ReserveMessage> reserveMessages;
    private static boolean isVisible;
    private  int searchAccount;
    public  Handler rHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            searchAccount = msg.what;
            if(isVisible == true ){
                mPresenter.getReserveMessagesInfoRequest(searchAccount, AppConstant.SEND_RESERVEMSG);//查询
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


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_reservemessage;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter = new BaseRecyclerAdapter<ReserveMessage>(R.layout.layout_reserve_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, ReserveMessage model, int position) {
                holder.text(R.id.bookname, model.getBookname());
                holder.image(R.id.image, PhotoUtils.getBitmap(model.getImage()));
                holder.text(R.id.reservetime, "预约时间:" + model.getReservetime());
                holder.text(R.id.status, model.getStatus() == 1 ? "书到待取" : "等待");
                holder.textColorId(R.id.status,R.color.red);
            }

        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().post(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getReserveMessagesInfoRequest(searchAccount, AppConstant.SEND_RESERVEMSG);//查询
                        refreshlayout.finishRefresh(true);

                    }
                });
            }
        });

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void returnReserveMessagesInfo(List<ReserveMessage> reservemessage) {
        if (reservemessage.size() == 0) {
            showShortToast("抱歉，没有你查询的图书");
        } else {
            this.reserveMessages = reservemessage;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.refresh(reserveMessages);
                }
            });
        }
    }
}
