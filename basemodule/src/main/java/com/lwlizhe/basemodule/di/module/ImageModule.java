package com.lwlizhe.basemodule.di.module;



import com.lwlizhe.basemodule.imageloader.BaseImageLoaderStrategy;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 8/5/16 16:10
 * contact with jess.yan.effort@gmail.com
 */
@Module
public class ImageModule {

//    @Singleton
//    @Provides
//    public BaseImageLoaderStrategy provideGlideStrategy(GlideImageLoaderStrategy glideImageLoaderStrategy) {
//        return glideImageLoaderStrategy;
//    }

    @Singleton
    @Provides
    public GlideImageLoaderStrategy provideGlideImageLoader() {
        return new GlideImageLoaderStrategy();
    }

}
