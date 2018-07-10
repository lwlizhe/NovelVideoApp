package com.lwlizhe.video.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.model.fragment.VideoMainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@Module
public class VideoMainModule {

    private VideoMainContract.View view;

    public VideoMainModule(VideoMainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoMainContract.View provideVideoMainView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    VideoMainContract.Model provideVideoMainModel(VideoMainModel model){
        return model;
    }
}
