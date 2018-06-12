package com.lwlizhe.novelvideoapp;


import com.lwlizhe.novelvideoapp.novel.api.entity.NovelReCommendEntity;
import java.util.List;
import io.reactivex.Flowable;
import retrofit2.http.GET;



/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public interface TestService {

//    @GET(GlobeConstance.NOVEL_BASE_URL+"/recommend.json")
    @GET("/recommend.json")
    Flowable<String> getNovelReCommend();

}
