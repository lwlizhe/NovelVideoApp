package com.lwlizhe.novelvideoapp.novel.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelMainContract;
import com.lwlizhe.novelvideoapp.novel.mvp.model.activity.NovelMainModel;

import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;

/**
 * Created by Administrator on 2018/5/3 0003.
 */
@Module
public class NovelMainModule {

    private NovelMainContract.View view;

    /**
     * 构建NovelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public NovelMainModule(NovelMainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NovelMainContract.View provideNovelView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    NovelMainContract.Model provideNovelMainModel(NovelMainModel model){
        return model;
    }


}
