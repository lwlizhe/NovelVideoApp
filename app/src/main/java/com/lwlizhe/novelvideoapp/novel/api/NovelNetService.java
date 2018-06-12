package com.lwlizhe.novelvideoapp.novel.api;


import com.lwlizhe.novelvideoapp.GlobeConstance;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelCategoryEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelCommentEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelDetailEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelFilterEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelListEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelReCommendEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelRecentUpdataEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelSearchResultEntity;


import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public interface NovelNetService {

    @GET("http://www.wanandroid.com"+"/article/list/1/json")
    Flowable<String> getTest();

    @GET(GlobeConstance.NOVEL_BASE_URL+"recommend.json")
    Flowable<List<NovelReCommendEntity>> getNovelReCommend();

    @GET(GlobeConstance.NOVEL_BASE_URL+"recentUpdate/{page}.json ")
    Flowable<List<NovelRecentUpdataEntity>> getNovelRecentUpdata(@Path("page") long page);

    @GET(GlobeConstance.NOVEL_BASE_URL+"{id}.json")
    Flowable<NovelDetailEntity> getNovelDetail(@Path("id") long novelId);

    @GET(GlobeConstance.NOVEL_BASE_URL+"chapter/{id}.json")
    Flowable<List<NovelChapterEntity>> getNovelChapter(@Path("id") long novelId);

    @GET(GlobeConstance.NOVEL_BASE_URL+"download/{id}_{volume_id}_{chapter_id}.txt")
    Flowable<String> getNovel(@Path("id") long novelId, @Path("volume_id") long volumeId, @Path("chapter_id") long chapterId);

    @GET("http://v2api.dmzj.com/1/category.json")
    Flowable<List<NovelCategoryEntity>> getNovelCategory();

    @GET(GlobeConstance.NOVEL_BASE_URL+"filter.json")
    Flowable<NovelFilterEntity> getNovelFilter();

    @GET(GlobeConstance.NOVEL_BASE_URL+"{cat_id}/{status_id}/{order_id}/{page}.json ")
    Flowable<List<NovelListEntity>> getNovelList(@Path("cat_id") long catId, @Path("status_id") long statusId, @Path("order_id") long orderId, @Path("page") long page);

    @GET("http://v2.api.dmzj.com/search/show/{big_cat_id}/{keywords}/{page}.json ")
    Flowable<List<NovelSearchResultEntity>> getNovelSearchResult(@Path("big_cat_id") long bigCatId, @Path("keywords") String keyWords, @Path("page") long page);

    @GET("http://v2.api.dmzj.com/comment/1/{comment_type}/{novel_id}/{page}.json ")
    Flowable<List<NovelCommentEntity>> getNovelComment(@Path("comment_type") long commentType, @Path("novel_id") long novelId, @Path("page") long page);

}
