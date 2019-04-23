package com.lwlizhe.video.mvp.model.fragment;

import android.text.TextUtils;

import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.video.api.VideoNetService;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.mvp.contract.VideoMainContract.Model;
import com.lwlizhe.video.mvp.model.activity.VideoIntroductionModel;
import javax.inject.Inject;
import io.reactivex.Flowable;


/**
 * Created by Administrator on 2018/7/2 0002.
 */
@FragmentScope
public class VideoMainModel extends VideoIntroductionModel implements Model {

    @Inject
    public VideoMainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<DilidiliIndexEntity> getDilidiliAppInfo() {
        return mServiceManager.getRetrofitService(VideoNetService.class).getVideoContent();
    }
}
