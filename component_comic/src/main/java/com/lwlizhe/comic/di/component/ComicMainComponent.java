package com.lwlizhe.comic.di.component;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.comic.di.module.ComicModule;
import com.lwlizhe.comic.mvp.ui.fragment.ComicMainFragment;
import com.lwlizhe.common.di.component.AppComponent;

import dagger.Component;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@ActivityScope
@Component(modules = ComicModule.class,dependencies = AppComponent.class)
public interface ComicMainComponent {

    void inject(ComicMainFragment fragment);

}
