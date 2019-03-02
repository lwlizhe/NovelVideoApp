package com.lwlizhe.novel.mvp.model.fragment;

import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novel.api.NovelNetService;
import com.lwlizhe.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import com.lwlizhe.novel.mvp.contract.fragment.NovelDetailChapterContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/6/4 0004.
 */
@FragmentScope
public class NovelDetailChapterModel extends BaseModel<ServiceManager, CacheManager> implements NovelDetailChapterContract.Model {
    @Inject
    public NovelDetailChapterModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<List<NovelChapterEntity>> getNovelChapter(long novelId) {
        return mServiceManager.getRetrofitService(NovelNetService.class).getNovelChapter(novelId);
    }
}
