package com.lwlizhe.basemodule.di.module;

import com.lwlizhe.basemodule.event.RxEventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/8 0008.
 */
@Module
public class EventModule {


    public EventModule() {
    }

    /**
     * 提供一个Rxbus
     */
    @Singleton
    @Provides
    RxEventBus provideRxBus(){
        return new RxEventBus();
    }

}
