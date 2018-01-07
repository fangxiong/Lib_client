package com.fx.manage.ui.student.view.fragment;

import android.os.Bundle;
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
public class ReserveFragment extends BaseFragment<ReservePresenter, ReserveModel> implements ReserveContract.View {
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private BaseRecyclerAdapter<ReserveMessage> mAdapter;
    private List<ReserveMessage> reserveMessages;

    @Override
    public void onHiddenChanged(boolean hidd) {
        if (hidd) {
            //隐藏时所作的事情

        } else {
            //显示时所作的事情
            //数据为空才重新发起请求
            if(mAdapter.getCount() <= 0){
                mPresenter.getReserveMessagesInfoRequest(AppApplication.student_user.getUserid(), AppConstant.SEND_RESERVEMSG);//查询
            }
        }
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_reserve;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        ntb.setTvLeftVisiable(false);
        ntb.setTitleText(getString(R.string.reserve_title));
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
                        mPresenter.getReserveMessagesInfoRequest(AppApplication.student_user.getUserid(), AppConstant.SEND_RESERVEMSG);//查询
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
