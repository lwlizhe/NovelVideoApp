package com.lwlizhe.common.service.manager;

import android.util.LruCache;

import com.bumptech.glide.util.Preconditions;
import com.lwlizhe.basemodule.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

@Singleton
public class ServiceManager implements BaseServiceManager {

    @Inject
    public Lazy<Retrofit> mRetrofit;
    private LruCache<String, Object> mRetrofitServiceCache;


    @Inject
    public ServiceManager() {

    }

    public <T> T getRetrofitService(Class<T> serviceClass) {
        if (mRetrofitServiceCache == null) {

            long maxMemory = Runtime.getRuntime().maxMemory();
            int cacheSize = (int) (maxMemory / 8);
            mRetrofitServiceCache = new LruCache<>(cacheSize);
        }
        Preconditions.checkNotNull(mRetrofitServiceCache,
                "mRetrofitServiceCache is null");
        T retrofitService = (T) mRetrofitServiceCache.get(serviceClass.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = mRetrofit.get().create(serviceClass);
            mRetrofitServiceCache.put(serviceClass.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }

    @Override
    public void onDestroy() {

    }
}
