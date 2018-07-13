package com.lwlizhe.video.mvp.presenter.activity;

import android.app.Application;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.video.mvp.contract.VideoWebPlayerContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class VideoWebPlayerPresenter extends BasePresenter<VideoWebPlayerContract.Model,VideoWebPlayerContract.View> {

    @Inject
    public VideoWebPlayerPresenter(VideoWebPlayerContract.Model model, VideoWebPlayerContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);
    }
}
