package com.lwlizhe.novelvideoapp.novel.mvp.model.fragment;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novelvideoapp.common.cache.manager.CacheManager;
import com.lwlizhe.novelvideoapp.common.service.manager.ServiceManager;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelDetailEntity;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.fragment.NovelDetailChapterContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/6/4 0004.
 */
@ActivityScope
public class NovelDetailChapterModel extends BaseModel<ServiceManager, CacheManager> implements NovelDetailChapterContract.Model {
    @Inject
    public NovelDetailChapterModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<List<NovelChapterEntity>> getNovelChapter(long novelId) {
        return mServiceManager.getNovelService().getNovelChapter(novelId);
    }
}
