package com.lwlizhe.novelvideoapp;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.media.AudioManager.STREAM_MUSIC;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MainActivity2 extends AppCompatActivity{

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = findViewById(R.id.webView);

//        web.setWebViewClient(new MyWebViewClient());
//        web.loadUrl("https://www.baidu.com/");
        web.loadUrl("https://jx.618g.com/?url=http://vd3.bdstatic.com/mda-ifvqu9yp3eaqueep/mda-ifvqu9yp3eaqueep.mp4");

    }

    public class MyWebViewClient extends WebViewClient {

        public boolean shouldOverviewUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }


}
