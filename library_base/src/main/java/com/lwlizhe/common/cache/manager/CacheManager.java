package com.lwlizhe.common.cache.manager;

import com.lwlizhe.basemodule.http.BaseCacheManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/5/10 0010.
 */
@Singleton
public class CacheManager implements BaseCacheManager {


    @Inject
    public CacheManager() {

    }

    @Override
    public void onDestroy() {

    }
}
