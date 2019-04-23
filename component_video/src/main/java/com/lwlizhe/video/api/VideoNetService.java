package com.lwlizhe.video.api;

import com.lwlizhe.GlobeConstance;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.api.entity.DilidiliVideoResourceResponseEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by lwlizhe on 2019/2/14.
 */

public interface VideoNetService {

    @GET(GlobeConstance.CONSTANCE_URL.DILIDILI_BASE_URL+"/api/v1/getIndex")
    Flowable<DilidiliIndexEntity> getVideoContent();

    @GET(GlobeConstance.CONSTANCE_URL.DILIDILI_BASE_URL+"/api/v1/getAnimeDetail/{id}")
    Flowable<DilidiliAnimationDetailResponseEntity> getAnimationDetail(@Path("id") int paramInt);

    @PUT
    Flowable<DilidiliVideoResourceResponseEntity> getVideoSource(@Url String paramString);
}
