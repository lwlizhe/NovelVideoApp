package com.lwlizhe.video.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.video.mvp.contract.VideoPlayerContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
@ActivityScope
public class VideoPlayerModel extends BaseModel<ServiceManager,CacheManager> implements VideoPlayerContract.Model{

    @Inject
    public VideoPlayerModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


}
