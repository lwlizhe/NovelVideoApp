package com.lwlizhe.novelvideoapp;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import static android.media.AudioManager.STREAM_MUSIC;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MainActivity2 extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView view = findViewById(R.id.test);

        final View decorView = MainActivity2.this.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);

        view.post(new Runnable() {
            @Override
            public void run() {
                view.setImageBitmap(decorView.getDrawingCache());
            }
        });

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }



}
