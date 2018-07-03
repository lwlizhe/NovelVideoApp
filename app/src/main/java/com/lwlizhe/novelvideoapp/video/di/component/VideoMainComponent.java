package com.lwlizhe.novelvideoapp.video.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.video.di.module.VideoMainModule;
import com.lwlizhe.novelvideoapp.video.mvp.ui.fragment.VideoMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@ActivityScope
@Component(modules = VideoMainModule.class, dependencies = AppComponent.class)
public interface VideoMainComponent {
    void inject(VideoMainFragment fragment);
}
