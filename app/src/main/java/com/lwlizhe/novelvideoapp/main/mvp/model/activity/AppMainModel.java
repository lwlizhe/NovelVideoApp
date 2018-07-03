package com.lwlizhe.novelvideoapp.main.mvp.model.activity;


import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novelvideoapp.common.cache.manager.CacheManager;
import com.lwlizhe.novelvideoapp.common.service.manager.ServiceManager;
import com.lwlizhe.novelvideoapp.main.mvp.contract.AppMainContract;

import javax.inject.Inject;




/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */
@ActivityScope
public class AppMainModel extends BaseModel<ServiceManager,CacheManager> implements AppMainContract.Model {
    @Inject
    public AppMainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager,cacheManager);
    }

    @Override
    public void onDestroy() {

    }


//
//    @Override
//    public Flowable<List<NovelSearchResultEntity>> getNovelSearchResult(long bigCatId, String keyWords, long pages) {
//
//        Flowable<List<NovelSearchResultEntity>> searchResult;
//        searchResult = mServiceManager.getNovelNetService()
//                .getNovelSearchResult(bigCatId, keyWords, pages);
//
//        return searchResult;
//    }


}


