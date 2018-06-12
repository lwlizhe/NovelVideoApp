package com.lwlizhe.novelvideoapp.novel.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelDetailChapterModule;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.fragment.NovelDetailChapterFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/4 0004.
 */
@ActivityScope
@Component(modules = NovelDetailChapterModule.class,dependencies = AppComponent.class)
public interface NovelDetailChapterComponent {

    void inject(NovelDetailChapterFragment fragment);

}
