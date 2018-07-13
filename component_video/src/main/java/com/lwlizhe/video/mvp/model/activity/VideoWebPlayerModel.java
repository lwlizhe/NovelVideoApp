package com.lwlizhe.video.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.video.mvp.contract.VideoWebPlayerContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/13 0013.
 */
@ActivityScope
public class VideoWebPlayerModel extends BaseModel<ServiceManager,CacheManager> implements VideoWebPlayerContract.Model{

    @Inject
    public VideoWebPlayerModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }
}
