package com.lwlizhe.novel.mvp.model.fragment;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.common.api.novel.entity.NovelReCommendEntity;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.novel.mvp.contract.fragment.NovelRecommendContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */
@ActivityScope
public class NovelReCommendModel  extends BaseModel<ServiceManager,CacheManager> implements NovelRecommendContract.Model {

    @Inject
    public NovelReCommendModel(ServiceManager serviceManager,CacheManager cacheManager) {
        super(serviceManager,cacheManager);
    }

    @Override
    public Flowable<List<NovelReCommendEntity>> getNovelReCommend(boolean upData) {
        //        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
//        return mCacheManager.getCommonCache()
//                .getNovelReCommend(commends
//                        , new DynamicKey(GlobeConstance.NOVEL_COMMEND_DYNAMIC_KEY)
//                        , new EvictDynamicKey(upData))
//                .flatMap(new Function<Reply<List<NovelReCommendEntity>>, Publisher<List<NovelReCommendEntity>>>() {
//                    @Override
//                    public Publisher<List<NovelReCommendEntity>> apply(@NonNull Reply<List<NovelReCommendEntity>> novelCommendEntityReply) throws Exception {
//                        return Flowable.just(novelCommendEntityReply.getData());
//                    }
//                });

//        Logger.d(mServiceManager.getNovelService().getTest());

        return mServiceManager.getNovelService()
                .getNovelReCommend();
    }
}
