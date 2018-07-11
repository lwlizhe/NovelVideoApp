package com.lwlizhe.video.mvp.model.fragment;

import android.text.TextUtils;

import com.fcannizzaro.jsoup.annotations.JP;
import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.di.scope.FragmentScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.video.mvp.contract.VideoMainContract.Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Iterator;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/7/2 0002.
 */
@FragmentScope
public class VideoMainModel extends BaseModel<ServiceManager,CacheManager> implements Model {

    @Inject
    public VideoMainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Flowable<DilidiliInfo> getDilidiliInfo() {
        return Flowable.create(new FlowableOnSubscribe<DilidiliInfo>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<DilidiliInfo> e) throws Exception {
                Element html = Jsoup.connect(GlobeConstance.DILIDILI_URL).get();
                if (html == null) {
                    e.onError(new Throwable("element html is null"));
                } else {
                    DilidiliInfo dilidiliInfo = JP.from(html, DilidiliInfo.class);
                    Iterator<DilidiliInfo.ScheudleBanner> scheduleBannerIterator = dilidiliInfo.getScheduleBanners()
                            .iterator();
                    while (scheduleBannerIterator.hasNext()) {
                        DilidiliInfo.ScheudleBanner scheduleBanner = scheduleBannerIterator.next();
                        if (TextUtils.isEmpty(scheduleBanner.getName()) |
                                TextUtils.isEmpty(scheduleBanner.getImgUrl()) |
                                TextUtils.isEmpty(scheduleBanner.getAnimeLink())) {
                            scheduleBannerIterator.remove();
                        }
                    }
                    e.onNext(dilidiliInfo);
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
