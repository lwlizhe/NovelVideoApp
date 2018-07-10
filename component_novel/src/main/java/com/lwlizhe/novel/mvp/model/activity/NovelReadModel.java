package com.lwlizhe.novel.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.novel.mvp.contract.activity.NovelReadContract;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@ActivityScope
public class NovelReadModel extends BaseModel<ServiceManager,CacheManager> implements NovelReadContract.Model{

    @Inject
    public NovelReadModel(ServiceManager serviceManager,CacheManager cacheManager) {
        super(serviceManager,cacheManager);
    }


    @Override
    public Flowable<String> getNovelContent(long novelId,long volumeId,long chapterId) {

        Flowable<String> novelFlowable = mServiceManager.getNovelService().getNovel(novelId, volumeId, chapterId);

        return novelFlowable;
    }
}
