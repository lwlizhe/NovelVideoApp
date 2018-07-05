package com.lwlizhe.novelvideoapp.video.mvp.presenter.activity;

import android.app.Application;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoPlayerContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class VideoPlayerPresenter extends BasePresenter<VideoPlayerContract.Model,VideoPlayerContract.View> {

    @Inject
    public VideoPlayerPresenter(VideoPlayerContract.Model model, VideoPlayerContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);
    }
}
