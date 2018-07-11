package com.lwlizhe.comic.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.comic.mvp.model.ComicMainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Module
public class ComicModule {

    private ComicMainContract.View view;

    public ComicModule(ComicMainContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public ComicMainContract.View providerComicView(){
        return view;
    }

    @FragmentScope
    @Provides
    public ComicMainContract.Model providerComicModel(ComicMainModel model){
        return model;
    }
}
