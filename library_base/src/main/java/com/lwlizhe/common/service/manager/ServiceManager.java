package com.lwlizhe.common.service.manager;

import com.lwlizhe.basemodule.http.BaseServiceManager;
import com.lwlizhe.common.api.novel.NovelNetService;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {

    private NovelNetService mNovelService;

    @Inject
    public ServiceManager(NovelNetService novelNetService) {

        this.mNovelService=novelNetService;

    }

    public NovelNetService getNovelService() {
        return mNovelService;
    }

    @Override
    public void onDestroy() {

    }
}
