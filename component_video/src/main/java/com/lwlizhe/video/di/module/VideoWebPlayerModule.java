package com.lwlizhe.video.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.video.mvp.contract.VideoWebPlayerContract;
import com.lwlizhe.video.mvp.model.activity.VideoWebPlayerModel;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/13 0013.
 */
@Module
public class VideoWebPlayerModule {

    private VideoWebPlayerContract.View view;

    public VideoWebPlayerModule(VideoWebPlayerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public VideoWebPlayerContract.View providerWebPlayerView(){
        return view;
    }

    @ActivityScope
    @Provides
    public VideoWebPlayerContract.Model providerWebPlayerModel(VideoWebPlayerModel model){
        return model;
    }


}
