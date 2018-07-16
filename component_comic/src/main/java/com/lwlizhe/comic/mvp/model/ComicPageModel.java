package com.lwlizhe.comic.mvp.model;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.comic.mvp.contract.activity.ComicPageContract;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/16 0016.
 */
@ActivityScope
public class ComicPageModel extends BaseModel<ServiceManager,CacheManager> implements ComicPageContract.Model{

    @Inject
    public ComicPageModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


}
