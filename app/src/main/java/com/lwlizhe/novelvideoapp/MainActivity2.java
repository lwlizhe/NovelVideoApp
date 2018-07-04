package com.lwlizhe.novelvideoapp;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(STREAM_MUSIC, 6, 0);

    }

}
