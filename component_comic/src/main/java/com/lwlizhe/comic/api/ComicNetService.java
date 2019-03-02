package com.lwlizhe.comic.api;

import com.lwlizhe.GlobeConstance;
import com.lwlizhe.comic.api.entity.ComicRecommendResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public interface ComicNetService {

    // 推荐位
    @GET(GlobeConstance.COMIC_BASE_URL+"v3/recommend.json")
    Flowable<List<ComicRecommendResponse>> getRecommend();

    // 漫画详情页
    @GET(GlobeConstance.COMIC_BASE_URL+"comic/{id}.json")
    Flowable<Object> getComicInstruction(@Path("id") long novelId);

    // 漫画内容
    @GET(GlobeConstance.COMIC_BASE_URL+"chapter/{comicId}/{chapterId}.json")
    Flowable<Object> getComic(@Path("comicId") long comicId,@Path("chapterId") long chapterId);

    // 漫画吐槽
    @GET(GlobeConstance.COMIC_BASE_URL+"chapter/{comicId}/{chapterId}.json")
    Flowable<Object> getComicRoast(@Path("comicId") long comicId,@Path("chapterId") long chapterId);

    // 漫画相关
    @GET(GlobeConstance.COMIC_BASE_URL+"v3/comic/related/{id}.json")
    Flowable<Object> getComicRelated(@Path("id") long comicId);

    // 漫画评论
    @GET(GlobeConstance.COMIC_BASE_URL+"comment2/4/0/{comicId}/1/{page}.json")
    Flowable<Object> getComicComment(@Path("comicId") long comicId,@Path("page") long page);
}
