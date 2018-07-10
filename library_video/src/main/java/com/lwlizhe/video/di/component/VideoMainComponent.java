package com.lwlizhe.video.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.video.di.module.VideoMainModule;
import com.lwlizhe.video.mvp.ui.fragment.VideoMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@ActivityScope
@Component(modules = VideoMainModule.class, dependencies = AppComponent.class)
public interface VideoMainComponent {
    void inject(VideoMainFragment fragment);
}
