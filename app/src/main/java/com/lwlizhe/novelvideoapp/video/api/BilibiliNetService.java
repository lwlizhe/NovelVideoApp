package com.lwlizhe.novelvideoapp.video.api;

import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiAppIndexInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiDetailsInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiDetailsRecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiIndexInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiRecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.BangumiScheduleInfo;
import com.lwlizhe.novelvideoapp.video.entity.bangumi.SeasonNewBangumiInfo;
import com.lwlizhe.novelvideoapp.video.entity.recommend.RecommendBannerInfo;
import com.lwlizhe.novelvideoapp.video.entity.recommend.RecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.region.RegionDetailsInfo;
import com.lwlizhe.novelvideoapp.video.entity.region.RegionRecommendInfo;
import com.lwlizhe.novelvideoapp.video.entity.search.SearchArchiveInfo;
import com.lwlizhe.novelvideoapp.video.entity.search.SearchBangumiInfo;
import com.lwlizhe.novelvideoapp.video.entity.search.SearchMovieInfo;
import com.lwlizhe.novelvideoapp.video.entity.search.SearchSpInfo;
import com.lwlizhe.novelvideoapp.video.entity.search.SearchUpperInfo;
import com.lwlizhe.novelvideoapp.video.entity.video.VideoDetailsInfo;
import com.lwlizhe.novelvideoapp.video.entity.video.VideoPlayUrlEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.lwlizhe.novelvideoapp.GlobeConstance.APP_BASE_URL;
import static com.lwlizhe.novelvideoapp.GlobeConstance.APP_PLAY_BASE_URL;


/**
 * Created by lwlizhe on 2017/6/30.
 * 邮箱：624456662@qq.com
 */

public interface BilibiliNetService {

/***************************************************************************************************
 * banggumi
 ***************************************************************************************************/

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

/***************************************************************************************************
 * appHome
 ***************************************************************************************************/

    /**
     * 首页推荐数据
     */
    @GET(APP_BASE_URL+"x/show/old?platform=android&device=&build=412001")
    Flowable<RecommendInfo> getRecommendedInfo();

    /**
     * 首页推荐banner
     */
    @GET(APP_BASE_URL+"x/banner?plat=4&build=411007&channel=bilih5")
    Flowable<RecommendBannerInfo> getRecommendedBannerInfo();

    /**
     * 视频详情数据
     */
    @GET(APP_BASE_URL+"x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Flowable<VideoDetailsInfo> getVideoDetails(@Query("aid") int aid);

    /**
     * 综合搜索
     */
    @GET("x/v2/search?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&duration=0&mobi_app=iphone&order=default&platform=ios&rid=0")
    Flowable<SearchArchiveInfo> searchArchive(
            @Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);

    /**
     * 番剧搜索
     */
    @GET("x/v2/search/type?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&mobi_app=iphone&platform=ios&type=1")
    Flowable<SearchBangumiInfo> searchBangumi(
            @Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);

    /**
     * up主搜索
     */
    @GET("x/v2/search/type?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&mobi_app=iphone&platform=ios&type=2")
    Flowable<SearchUpperInfo> searchUpper(
            @Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);

    /**
     * 影视搜索
     */
    @GET("x/v2/search/type?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&mobi_app=iphone&platform=ios&type=3")
    Flowable<SearchMovieInfo> searchMovie(
            @Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);

    /**
     * 专题搜索
     */
    @GET("x/v2/search/type?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&mobi_app=iphone&platform=ios&type=4")
    Flowable<SearchSpInfo> searchSp(
            @Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);

    /**
     * 分区推荐
     */
    @GET("x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694")
    Flowable<RegionRecommendInfo> getRegionRecommends(@Query("rid") int rid);

    /**
     * 分区类型详情
     */
    @GET("x/v2/region/show/child?build=3600")
    Flowable<RegionDetailsInfo> getRegionDetails(@Query("rid") int rid);

    /***************************************************************************************************
     * video
     ***************************************************************************************************/

    /**
     * b站高清视频
     * quailty:清晰度(1~2，根据视频有不同)
     * type: 格式(mp4/flv)
     */
//    @GET(APP_BANGUMI_PLAY_BASE_URL+"/playurl?appkey=f3bb208b3d081dc8&cid={cid}&from=local&otype=json&player=1&quality={quality}&type={type}&sign={sign}")
    @GET(APP_PLAY_BASE_URL+"/playurl?appkey=f3bb208b3d081dc8&from=local&otype=json&player=1")
    Flowable<VideoPlayUrlEntity> getVideoUrl(@Query("cid") int cid, @Query("quality") int quality, @Query("type") String type, @Query("sign") String sign);



}
