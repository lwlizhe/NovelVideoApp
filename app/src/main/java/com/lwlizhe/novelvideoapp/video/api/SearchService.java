package com.lwlizhe.novelvideoapp.video.api;


import com.lwlizhe.novelvideoapp.video.entity.discover.HotSearchTag;

import io.reactivex.Flowable;
import retrofit2.http.GET;


/**
 * Created by hcc on 2016/10/3 11:53
 * 100332338@qq.com
 * <p>
 * 搜索相关api
 */

public interface SearchService {

    /**
     * 首页发现热搜词
     */
    @GET("main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3")
    Flowable<HotSearchTag> getHotSearchTags();
}
