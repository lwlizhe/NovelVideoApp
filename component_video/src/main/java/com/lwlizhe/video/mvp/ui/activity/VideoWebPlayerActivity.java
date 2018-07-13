package com.lwlizhe.video.mvp.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.base.CommonActivity;
import com.lwlizhe.video.di.component.DaggerVideoWebPlayerComponent;
import com.lwlizhe.video.di.module.VideoWebPlayerModule;
import com.lwlizhe.video.mvp.contract.VideoWebPlayerContract;
import com.lwlizhe.video.mvp.presenter.activity.VideoWebPlayerPresenter;



/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class VideoWebPlayerActivity extends CommonActivity<VideoWebPlayerPresenter> implements VideoWebPlayerContract.View {

    public static final String SCHEDULE_VIDEO_CSS = "<link rel=\"stylesheet\" href=\"http://m.static.jfrft.com/templets/default/css/index.css\">";

    public static final String INTENT_VIDEO_PAGE_URL = "intent_video_page_url";

    private WebView mWebView;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerVideoWebPlayerComponent.builder().appComponent(appComponent).videoWebPlayerModule(new VideoWebPlayerModule(VideoWebPlayerActivity.this)).build().inject(this);
    }

    @Override
    protected View initRootView() {
        return LayoutInflater.from(VideoWebPlayerActivity.this).inflate(R.layout.activity_web_player, null, false);
    }

    @Override
    protected void initView() {

        mWebView = findViewById(R.id.webView);

        initWebView();

    }

    private void initWebView() {

//        WebSettings webSettings = mWebView.getSettings();
//
//        //支持缩放，默认为true。
//        webSettings .setSupportZoom(false);
//
//        //调整图片至适合webview的大小
//        webSettings .setUseWideViewPort(true);
//
//        // 缩放至屏幕的大小
//        webSettings .setLoadWithOverviewMode(true);
//
//        //设置默认编码
//        webSettings .setDefaultTextEncodingName("utf-8");
//
//        ////设置自动加载图片
//        webSettings .setLoadsImagesAutomatically(true);
//
//
//
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
//        QbSdk.initX5Environment(this,null);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra(INTENT_VIDEO_PAGE_URL);

        //http://jx.yylep.com/?url=http://www.iqiyi.com/v_19rrcg9eq4.html

//        mWebView.loadUrl("http://jx.yylep.com/?url=http://www.iqiyi.com/v_19rrcg9eq4.html");
//        TbsPlus.openUrl(this, "http://jx.yylep.com/?url=http://www.iqiyi.com/v_19rrcg9eq4.html", TbsPlus.eTBSPLUS_SCREENDIR.eTBSPLUS_SCREENDIR_LANDSCAPE);
//        mWebView.loadUrl("http://www.baidu.com");
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
