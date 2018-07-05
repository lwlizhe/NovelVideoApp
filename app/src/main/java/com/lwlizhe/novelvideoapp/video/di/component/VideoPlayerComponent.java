package com.lwlizhe.novelvideoapp.video.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.video.di.module.VideoPlayerModule;
import com.lwlizhe.novelvideoapp.video.mvp.ui.activity.VideoPlayerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
@ActivityScope
@Component(modules = VideoPlayerModule.class,dependencies = AppComponent.class)
public interface VideoPlayerComponent {

    void inject(VideoPlayerActivity activity);

}
