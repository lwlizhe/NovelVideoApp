package com.lwlizhe.novelvideoapp.video.mvp.model.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novelvideoapp.common.cache.manager.CacheManager;
import com.lwlizhe.novelvideoapp.common.service.manager.ServiceManager;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoPlayerContract;

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
