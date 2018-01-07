package com.fx.manage.ui.student.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fx.manage.R;
import com.fx.manage.base.AppApplication;
import com.fx.manage.base.BaseFragment;
import com.fx.manage.ui.student.view.activity.CollectionActivity;
import com.fx.manage.utils.PhotoUtils;
import com.fx.manage.widget.CircleImageView;
import com.fx.manage.widget.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class PersonFragment extends BaseFragment {


    @BindView(R.id.wave_view)
    WaveView waveView;
    @BindView(R.id.shoucang)
    LinearLayout shoucang;
    @BindView(R.id.jifen)
    LinearLayout jifen;
    @BindView(R.id.ziliao)
    LinearLayout ziliao;
    @BindView(R.id.ll_daynight_about)
    LinearLayout llDaynightAbout;
    Unbinder unbinder;
    @BindView(R.id.img_logo)
    CircleImageView imgLogo;
    Unbinder unbinder1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_person;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (AppApplication.student_user.getImage() != null)
            imgLogo.setImageBitmap(PhotoUtils.getBitmap(AppApplication.student_user.getImage()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.shoucang, R.id.jifen, R.id.ziliao, R.id.ll_daynight_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shoucang:
                Intent intent = new Intent(getActivity(),CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.jifen:
                break;
            case R.id.ziliao:
                break;
            case R.id.ll_daynight_about:
                break;
        }
    }
}
