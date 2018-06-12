package com.lwlizhe.novelvideoapp.novel.di.component;


import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelMainModule;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelMainActivity;

import dagger.Component;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/3 0003.
 */
@ActivityScope
@Component(modules = {NovelMainModule.class}, dependencies = AppComponent.class)
public interface NovelMainComponent {

    void inject(NovelMainActivity activity);

}