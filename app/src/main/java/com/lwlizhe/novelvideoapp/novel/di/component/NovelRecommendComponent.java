package com.lwlizhe.novelvideoapp.novel.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelRecommendModule;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.fragment.NovelRecommendFragment;

import dagger.Component;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */
@ActivityScope
@Component(modules = NovelRecommendModule.class, dependencies = AppComponent.class)
public interface NovelRecommendComponent {
    void inject(NovelRecommendFragment fragment);
}