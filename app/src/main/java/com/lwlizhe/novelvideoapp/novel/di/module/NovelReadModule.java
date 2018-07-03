package com.lwlizhe.novelvideoapp.novel.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelReadContract;
import com.lwlizhe.novelvideoapp.novel.mvp.model.activity.NovelReadModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@Module
public class NovelReadModule {

    private NovelReadContract.View view;

    public NovelReadModule(NovelReadContract.View view) {
        this.view=view;
    }

    @ActivityScope
    @Provides
    NovelReadContract.View provideNovelView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    NovelReadContract.Model provideNovelModel(NovelReadModel model){
        return model;
    }



}
