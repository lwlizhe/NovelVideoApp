package com.lwlizhe.novelvideoapp.novel.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novelvideoapp.common.cache.manager.CacheManager;
import com.lwlizhe.novelvideoapp.common.service.manager.ServiceManager;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelReadContract;

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
