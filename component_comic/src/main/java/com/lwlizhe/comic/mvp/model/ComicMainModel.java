package com.lwlizhe.comic.mvp.model;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.common.api.comic.entity.ComicRecommendResponse;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@FragmentScope
public class ComicMainModel extends BaseModel<ServiceManager,CacheManager> implements ComicMainContract.Model{

    @Inject
    public ComicMainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<List<ComicRecommendResponse>> getComicRecommend() {
        return mServiceManager.getComicService().getRecommend();
    }
}
