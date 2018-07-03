package com.lwlizhe.novelvideoapp.main.di.module;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.main.mvp.contract.AppMainContract;
import com.lwlizhe.novelvideoapp.main.mvp.model.activity.AppMainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/3 0003.
 */
@Module
public class AppMainModule {

    private AppMainContract.View view;

    /**
     * 构建NovelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public AppMainModule(AppMainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AppMainContract.View provideNovelView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    AppMainContract.Model provideNovelMainModel(AppMainModel model){
        return model;
    }


}
