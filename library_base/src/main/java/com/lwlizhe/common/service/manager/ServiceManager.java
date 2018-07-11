package com.lwlizhe.common.service.manager;

import com.lwlizhe.basemodule.http.BaseServiceManager;
import com.lwlizhe.common.api.comic.ComicNetService;
import com.lwlizhe.common.api.novel.NovelNetService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {

    private NovelNetService mNovelService;
    private ComicNetService mComicService;

    @Inject
    public ServiceManager(NovelNetService novelNetService,ComicNetService comicNetService) {

        this.mNovelService = novelNetService;
        this.mComicService = comicNetService;

    }

    public NovelNetService getNovelService() {
        return mNovelService;
    }

    public ComicNetService getComicService() {
        return mComicService;
    }

    @Override
    public void onDestroy() {

    }
}
