package com.lwlizhe.comic.di.component;

import com.lwlizhe.basemodule.di.module.AppModule;
import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.comic.di.module.ComicPageModule;
import com.lwlizhe.comic.mvp.ui.activity.ComicDetailActivity;
import com.lwlizhe.common.di.component.AppComponent;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/16 0016.
 */
@ActivityScope
@Component(modules = ComicPageModule.class,dependencies = AppComponent.class)
public interface ComicPageComponent {

    void inject(ComicDetailActivity activity);

}
