package com.lwlizhe.common.api.comic;

import com.lwlizhe.GlobeConstance;
import com.lwlizhe.common.api.comic.entity.ComicRecommendResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public interface ComicNetService {

    @GET(GlobeConstance.COMIC_BASE_URL+"v3/recommend.json")
    Flowable<List<ComicRecommendResponse>> getRecommend();

    @GET(GlobeConstance.COMIC_BASE_URL+"comic/{id}.json")
    Flowable<Object> getComicDetail(@Path("id") long novelId);

}
