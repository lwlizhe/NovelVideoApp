package com.lwlizhe.video.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.video.mvp.contract.VideoIntroductionContract;
import com.lwlizhe.video.mvp.model.activity.VideoIntroductionModel;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
@Module
public class VideoIntroductionModule {

    private VideoIntroductionContract.View view;

    public VideoIntroductionModule(VideoIntroductionContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public VideoIntroductionContract.View providerVideoView() {
        return view;
    }

    @ActivityScope
    @Provides
    public VideoIntroductionContract.Model providerVideoModel(VideoIntroductionModel model) {
        return model;
    }


}
