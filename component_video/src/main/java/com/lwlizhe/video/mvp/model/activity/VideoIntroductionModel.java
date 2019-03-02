package com.lwlizhe.video.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.video.api.VideoNetService;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.api.entity.DilidiliVideoResourceResponseEntity;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.video.mvp.contract.VideoIntroductionContract;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
@ActivityScope
public class VideoIntroductionModel extends BaseModel<ServiceManager,CacheManager> implements VideoIntroductionContract.Model{

    @Inject
    public VideoIntroductionModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<DilidiliAnimationDetailResponseEntity> getAnimationDetail(int resourceId) {
        return mServiceManager.getRetrofitService(VideoNetService.class).getAnimationDetail(resourceId);
    }

    @Override
    public Flowable<DilidiliVideoResourceResponseEntity> getAnimationVideoResource(String url) {
        return mServiceManager.getRetrofitService(VideoNetService.class).getVideoSource(url);
    }
}
