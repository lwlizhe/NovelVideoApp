package com.lwlizhe.novelvideoapp.video.mvp.model.fragment;

import android.text.TextUtils;

import com.fcannizzaro.jsoup.annotations.JP;
import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BaseModel;
import com.lwlizhe.novelvideoapp.GlobeConstance;
import com.lwlizhe.novelvideoapp.common.cache.manager.CacheManager;
import com.lwlizhe.novelvideoapp.common.service.manager.ServiceManager;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoMainContract.Model;

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
@ActivityScope
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
                    Iterator<DilidiliInfo.ScheudleBanner> scheudleBannerIterator = dilidiliInfo.getScheudleBanners()
                            .iterator();
                    while (scheudleBannerIterator.hasNext()) {
                        DilidiliInfo.ScheudleBanner scheudleBanner = scheudleBannerIterator.next();
                        if (TextUtils.isEmpty(scheudleBanner.getName()) |
                                TextUtils.isEmpty(scheudleBanner.getImgUrl()) |
                                TextUtils.isEmpty(scheudleBanner.getAnimeLink())) {
                            scheudleBannerIterator.remove();
                        }
                    }
                    e.onNext(dilidiliInfo);
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
