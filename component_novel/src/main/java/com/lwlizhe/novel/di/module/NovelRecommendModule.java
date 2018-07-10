package com.lwlizhe.novel.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novel.mvp.contract.fragment.NovelRecommendContract;
import com.lwlizhe.novel.mvp.model.fragment.NovelReCommendModel;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */
@Module
public class NovelRecommendModule {
    private NovelRecommendContract.View view;

    /**
     * 构建NovelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public NovelRecommendModule(NovelRecommendContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NovelRecommendContract.View provideNovelView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    NovelRecommendContract.Model provideNovelRecommendContract(NovelReCommendModel model){
        return model;
    }

}
