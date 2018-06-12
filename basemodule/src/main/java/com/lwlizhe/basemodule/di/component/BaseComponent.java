package com.lwlizhe.basemodule.di.component;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.BaseApplication;
import com.lwlizhe.basemodule.di.module.AppModule;
import com.lwlizhe.basemodule.di.module.ClientModule;
import com.lwlizhe.basemodule.di.module.ImageModule;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import javax.inject.Singleton;
import dagger.Component;


/**
 * Created by jess on 14/12/2016 13:58
 * Contact with jess.yan.effort@gmail.com
 */
@Singleton
@Component(modules = {AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication application);
}
