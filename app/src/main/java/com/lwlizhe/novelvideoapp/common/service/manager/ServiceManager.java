package com.lwlizhe.novelvideoapp.common.service.manager;

import com.lwlizhe.basemodule.http.BaseServiceManager;
import com.lwlizhe.novelvideoapp.novel.api.NovelNetService;
import com.lwlizhe.novelvideoapp.video.api.BilibiliNetService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {

    private NovelNetService mNovelService;
    private BilibiliNetService mBilibiliService;

    @Inject
    public ServiceManager(NovelNetService novelNetService, BilibiliNetService bilibiliNetService) {

        this.mNovelService=novelNetService;
        mBilibiliService=bilibiliNetService;

    }

    public NovelNetService getNovelService() {
        return mNovelService;
    }

    public BilibiliNetService getBilibiliService() {
        return mBilibiliService;
    }

    @Override
    public void onDestroy() {

    }
}
