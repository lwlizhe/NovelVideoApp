package com.lwlizhe.video.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.video.di.module.VideoWebPlayerModule;
import com.lwlizhe.video.mvp.ui.activity.VideoWebPlayerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/13 0013.
 */
@ActivityScope
@Component(modules = VideoWebPlayerModule.class,dependencies = AppComponent.class)
public interface VideoWebPlayerComponent {

    void inject(VideoWebPlayerActivity activity);

}
