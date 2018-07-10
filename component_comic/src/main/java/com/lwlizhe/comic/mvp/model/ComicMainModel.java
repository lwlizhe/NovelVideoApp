package com.lwlizhe.comic.mvp.model;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@ActivityScope
public class ComicMainModel extends BaseModel<ServiceManager,CacheManager> implements ComicMainContract.Model{

    @Inject
    public ComicMainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }
}
