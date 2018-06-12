package com.lwlizhe.novelvideoapp.video.api;


import com.lwlizhe.novelvideoapp.video.entity.live.LiveAppIndexInfo;
import com.lwlizhe.novelvideoapp.video.entity.user.UserLiveRoomStatusInfo;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hcc on 16/8/4 12:03
 * 100332338@qq.com
 * <p>
 * 直播相关api
 */
public interface LiveService {

    /**
     * 首页直播
     */
    @GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Flowable<LiveAppIndexInfo> getLiveAppIndex();

    /**
     * 直播url
     */
    @GET("api/playurl?player=1&quality=0")
    Flowable<ResponseBody> getLiveUrl(@Query("cid") int cid);

    /**
     * 获取直播状态
     */
    @GET("AppRoom/getRoomInfo")
    Flowable<UserLiveRoomStatusInfo> getUserLiveRoomStatus(@Query("mid") int mid);
}
