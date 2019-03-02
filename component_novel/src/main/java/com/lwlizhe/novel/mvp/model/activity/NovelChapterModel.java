package com.lwlizhe.novel.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novel.api.NovelNetService;
import com.lwlizhe.novel.api.entity.NovelDetailEntity;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import com.lwlizhe.novel.mvp.contract.activity.NovelChapterContract;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
@ActivityScope
public class NovelChapterModel extends BaseModel<ServiceManager,CacheManager> implements NovelChapterContract.Model{
    @Inject
    public NovelChapterModel(ServiceManager serviceManager,CacheManager cacheManager) {
        super(serviceManager,cacheManager);
    }

    @Override
    public Flowable<NovelDetailEntity> getNovelChapter(long id) {
        return mServiceManager.getRetrofitService(NovelNetService.class).getNovelDetail(id);
    }
}
