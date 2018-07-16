package com.lwlizhe.comic.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.comic.mvp.contract.activity.ComicPageContract;
import com.lwlizhe.comic.mvp.model.ComicPageModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/16 0016.
 */
@Module
public class ComicPageModule {

    private ComicPageContract.View view;

    @ActivityScope
    @Provides
    public ComicPageContract.View providerComicPageView(){
        return view;
    }

    @ActivityScope
    @Provides
    public ComicPageContract.Model providerComicPageModel(ComicPageModel model){
        return model;
    }

}
