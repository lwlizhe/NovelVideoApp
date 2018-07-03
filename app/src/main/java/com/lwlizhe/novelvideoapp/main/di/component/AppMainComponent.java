package com.lwlizhe.novelvideoapp.main.di.component;


import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.main.di.module.AppMainModule;
import com.lwlizhe.novelvideoapp.main.mvp.ui.activity.AppMainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/3 0003.
 */
@ActivityScope
@Component(modules = {AppMainModule.class}, dependencies = AppComponent.class)
public interface AppMainComponent {

    void inject(AppMainActivity activity);

}