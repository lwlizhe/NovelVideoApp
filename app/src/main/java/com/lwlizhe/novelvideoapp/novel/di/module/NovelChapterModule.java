package com.lwlizhe.novelvideoapp.novel.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelChapterContract;
import com.lwlizhe.novelvideoapp.novel.mvp.model.activity.NovelChapterModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
@Module
public class NovelChapterModule {

    private NovelChapterContract.View view;

    public NovelChapterModule(NovelChapterContract.View view) {
        this.view=view;
    }

    @ActivityScope
    @Provides
    NovelChapterContract.View provideNovelView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    NovelChapterContract.Model provideNovelMainModel(NovelChapterModel model){
        return model;
    }


}
