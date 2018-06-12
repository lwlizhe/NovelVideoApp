package com.lwlizhe.novelvideoapp.video.api;



import com.lwlizhe.novelvideoapp.video.entity.attention.AttentionDynamicInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiDetailsCommentInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.SpecialTopic;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.SpecialTopicIResult;
import com.lwlizhe.novelvideoapp.video.entity.discover.ActivityCenterInfo;
import com.lwlizhe.novelvideoapp.video.entity.discover.TopicCenterInfo;
import com.lwlizhe.novelvideoapp.video.entity.user.UserFavoritesInfo;
import com.lwlizhe.novelvideoapp.video.entity.video.VideoCommentInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by hcc on 16/8/8 21:08
 * 100332338@qq.com
 */
public interface BiliApiService {

    /**
     * 视频评论
     */
    @GET("feedback")
    Flowable<VideoCommentInfo> getVideoComment(@Query("aid") int aid, @Query("page") int page, @Query("pagesize") int pageSize, @Query("ver") int ver);

    /**
     * 专题详情
     */
    @GET("sp")
    Flowable<SpecialTopic> getSpInfo(@Query("spid") int spid, @Query("title") String title);

    /**
     * 专题视频
     */
    @GET("spview")
    Flowable<SpecialTopicIResult> getSpItemList(@Query("spid") int spid, @Query("season_id") int season_id, @Query("bangumi") int bangumi);

    /**
     * 话题中心
     */
    @GET("topic/getlist?device=phone&mobi_app=iphone&page=1&pagesize=137")
    Flowable<TopicCenterInfo> getTopicCenterList();

    /**
     * 话题中心
     */
    @GET("event/getlist?device=phone&mobi_app=iphone")
    Flowable<ActivityCenterInfo> getActivityCenterList(@Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 用户收藏夹
     */
    @GET("x/app/favourite/folder?")
    Flowable<UserFavoritesInfo> getUserFavorites(@Query("mid") int mid);

    /**
     * 首页关注
     */
    @GET("x/feed/pull?access_key=9afd8a2836e5948e84e037ca5b33309c&actionKey=appkey&appkey=27eb53fc9058f8c3&build=4000&device=phone&mobi_app=iphone&platform=ios&pn=1&ps=30&sign=8d5f090c70b3743a6a7d899d885061f0&ts=1480131936&type=0")
    Flowable<AttentionDynamicInfo> getAttentionDynamic();

    /**
     * 番剧详情评论
     */
    @GET("x/v2/reply?_device=iphone&_hwid=c84c067f8d99f9d3&_ulv=10000&access_key=19946e1ef3b5cad1a756c475a67185bb&appkey=27eb53fc9058f8c3&appver=3940&build=3940&nohot=0&oid=5189987&platform=ios&pn=1&ps=20&sign=c3b059e907f5c1d3416daa9fcc6396bf&sort=0&type=1")
    Flowable<BangumiDetailsCommentInfo> getBangumiDetailsComments();
}
