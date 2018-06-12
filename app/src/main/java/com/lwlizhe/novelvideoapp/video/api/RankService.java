package com.lwlizhe.novelvideoapp.video.api;



import com.lwlizhe.novelvideoapp.video.entity.discover.AllareasRankInfo;
import com.lwlizhe.novelvideoapp.video.entity.discover.OriginalRankInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by hcc on 2016/9/22 18:40
 * 100332338@qq.com
 * <p>
 * 排行榜相关api
 */

public interface RankService {

    /**
     * 原创排行榜请求
     */
    @GET("index/rank/{type}")
    Flowable<OriginalRankInfo> getOriginalRanks(@Path("type") String type);

    /**
     * 全区排行榜数据请求
     */
    @GET("index/rank/{type}")
    Flowable<AllareasRankInfo> getAllareasRanks(@Path("type") String type);
}
