package com.lwlizhe.common.di.module.service;


import com.lwlizhe.common.api.novel.NovelNetService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhiyicx on 2016/3/30.
 */
@Module
public class ServiceModule {

//    @Singleton
//    @Provides
//    CommonService provideCommonService(Retrofit retrofit) {
//        return retrofit.create(CommonService.class);
//    }

    @Singleton
    @Provides
    NovelNetService provideNovelNetService(Retrofit retrofit) {
        return retrofit.create(NovelNetService.class);
    }

//    @Singleton
//    @Provides
//    BilibiliNetService provideBilibiliNetService(Retrofit retrofit){
//        return retrofit.create(BilibiliNetService.class);
//    }

}
