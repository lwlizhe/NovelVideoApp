package com.lwlizhe.video.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.video.di.module.VideoIntroductionModule;
import com.lwlizhe.video.di.module.VideoMainModule;
import com.lwlizhe.video.mvp.ui.activity.VideoIntroductionActivity;
import com.lwlizhe.video.mvp.ui.fragment.VideoMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@ActivityScope
@Component(modules = VideoIntroductionModule.class, dependencies = AppComponent.class)
public interface VideoIntroductionComponent {
    void inject(VideoIntroductionActivity activity);
}
