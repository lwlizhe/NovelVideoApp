package com.lwlizhe.novel.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.novel.di.module.NovelChapterModule;
import com.lwlizhe.novel.mvp.ui.activity.NovelChapterActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
@ActivityScope
@Component(modules = NovelChapterModule.class,dependencies = AppComponent.class)
public interface NovelChapterComponent {

    void inject(NovelChapterActivity activity);

}
