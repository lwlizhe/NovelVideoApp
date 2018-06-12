package com.lwlizhe.novelvideoapp.video.api;



import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiAppIndexInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiDetailsInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiDetailsRecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiIndexInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiRecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiScheduleInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.SeasonNewBangumiInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;


/**
 * Created by hcc on 2016/11/1 10:43
 * 100332338@qq.com
 * <p>
 * 番剧相关api
 */

public interface BangumiService {

    /**
     * 首页番剧
     */
    @GET("api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios")
    Flowable<BangumiAppIndexInfo> getBangumiAppIndex();

    /**
     * 番剧详情番剧推荐
     */
    @GET("api/season/recommend/5070.json?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&season_id=5070&sign=744e3a3f52829e4344c33908f7a0c1ef&ts=1477898527")
    Flowable<BangumiDetailsRecommendInfo> getBangumiDetailsRecommend();

    /**
     * 番剧详情
     */
    @GET("api/season_v4?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&season_id=5070&sign=3e5d4d7460961d9bab5da2341fd98dc1&ts=1477898526&type=bangumi")
    Flowable<BangumiDetailsInfo> getBangumiDetails();

    /**
     * 首页番剧推荐
     */
    @GET("api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558")
    Flowable<BangumiRecommendInfo> getBangumiRecommended();

    /**
     * 分季新番
     */
    @GET("api/season_group.json?build=3940&device=phone&mobi_app=iphone&platform=ios")
    Flowable<SeasonNewBangumiInfo> getSeasonNewBangumiList();

    /**
     * 番剧时间表
     */
    @GET("api/timeline_v4?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&area_id=2&build=3940&device=phone&mobi_app=iphone&platform=ios&see_mine=0&sign=d8cbbacab2e5fd0196b4883201e2103e&ts=1477981526")
    Flowable<BangumiScheduleInfo> getBangumiSchedules();

    /**
     * 番剧索引
     */
    @GET("api/bangumi_index_cond?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=cfc6903a13ba89e81c904b4c589e5369&ts=1477974966&type=0")
    Flowable<BangumiIndexInfo> getBangumiIndex();
}
