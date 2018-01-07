package com.fx.manage.ui.student.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fx.manage.R;
import com.fx.manage.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fangxiong on 2017/12/11.
 */
public class ShouyeFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;
    private String url = "http://lib.ujs.edu.cn/index.jsp";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    webview.goBack();
                }
                break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.fragmet_shouye;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        WebSettings webSettings = webview.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript
        webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webview.setWebViewClient(new WebViewClient(){
            //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。 
            @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //如果不需要其他对点击链接事件的处理返回true，否则返回false 
                return true;
            }
        });
        webview.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event){
                if ((keyCode == KeyEvent.KEYCODE_BACK)&&webview.canGoBack()){
                    handler.sendEmptyMessage(1);
                    return true;
                    }
                return false;
               }

        });
        webview.loadUrl(url);
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
}
