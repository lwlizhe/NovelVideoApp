package com.lwlizhe.novel.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.novel.mvp.contract.fragment.NovelDetailChapterContract;
import com.lwlizhe.novel.mvp.model.fragment.NovelDetailChapterModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/4 0004.
 */
@Module
public class NovelDetailChapterModule {

    private NovelDetailChapterContract.View view;

    /**
     * 构建NovelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public NovelDetailChapterModule(NovelDetailChapterContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    NovelDetailChapterContract.View provideNovelView(){
        return this.view;
    }

    @FragmentScope
    @Provides
    NovelDetailChapterContract.Model provideNovelRecommendContract(NovelDetailChapterModel model){
        return model;
    }

}
