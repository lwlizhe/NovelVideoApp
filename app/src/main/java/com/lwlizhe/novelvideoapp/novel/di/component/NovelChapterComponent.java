package com.lwlizhe.novelvideoapp.novel.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelChapterModule;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelChapterActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
@ActivityScope
@Component(modules = NovelChapterModule.class,dependencies = AppComponent.class)
public interface NovelChapterComponent {

    void inject(NovelChapterActivity activity);

}
