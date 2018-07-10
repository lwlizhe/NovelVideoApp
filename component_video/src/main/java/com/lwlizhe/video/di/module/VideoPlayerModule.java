package com.lwlizhe.video.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.video.mvp.contract.VideoPlayerContract;
import com.lwlizhe.video.mvp.model.activity.VideoPlayerModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
@Module
public class VideoPlayerModule {

    private VideoPlayerContract.View view;

    public VideoPlayerModule(VideoPlayerContract.View view) {

        this.view = view;

    }

    @ActivityScope
    @Provides
    public VideoPlayerContract.View providerVideoView() {
        return view;
    }

    @ActivityScope
    @Provides
    public VideoPlayerContract.Model providerVideoModel(VideoPlayerModel model) {
        return model;
    }


}
