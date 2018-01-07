package com.fx.manage.ui.adminstrator.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fx.manage.R;
import com.fx.manage.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouyeActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView webview;
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
    public int getLayoutId() {
        return R.layout.activity_shouye;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
